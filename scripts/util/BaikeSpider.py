#!coding:utf-8

'''
百度百科搜索集成
'''
import os
import sys
import xlrd
from bs4 import BeautifulSoup as bs
import xlwt
from docx import Document
import logging

from ztool import Http
from ztool import ZQueue
from ztool import const

class Baike(object):
    def __init__(self):
        self.data = None

        # init the redis data queue
        self.redisConn = ZQueue.ZQueue()
        self.redisConn.setContainerName("BaikeSpider")

        self.importFunc = None

        self.exportFunc = None

        self.loggingPath = None



    def setLogging(self,filename):
        self.loggingPath = filename
        self.__setLogging()

    def importData(self,data):
        # void shutting down from unknown error
        if self.__isRedisEmpty():
            logging.info("[+]import data from outer")
            self.data = data
            self.__pushRedis()


    def exportChoice(self,exportFunc):
        self.exportFunc = exportFunc

    def __exportHandler(self,datalist):
        self.exportFunc.export(datalist)


    def start(self):
        errorCount = 0
        while self.__isRedisEmpty() is False:
            logging.info("[-]current queue number : "+str(self.__redisLen()))
            i = self.__popRedis()
            dataList = i.split(':')[:-1]
            s = spider(i)
            data = s.start()
            code, res = data
            if code < 0:
                if code == ERROR.PARSEERROR:
                    print res
                res = [ERROR.msg[code], ]
                logging.warn("[!]"+res[0])
                errorCount += 1
            else:
                keyword = res.keyword
                simpleInfo = res.simpleInfo
                complexInfoList = res.complexInfoList if res.complexInfoList != None else ["", ]
                complexParamDict = res.complexParamDict if res.complexParamDict != None else {"": "", }
                dataList.append(keyword)
                dataList.append(simpleInfo)
                dataList.append(complexInfoList)
                dataList.append(complexParamDict)
                self.__exportHandler(dataList)


    def __redisLen(self):
        return self.redisConn.len()


    def __isRedisEmpty(self):
        return self.redisConn.isEmpty()

    def __pushRedis(self):
        logging.info("[+]push data to redis")
        for d in self.data:
            self.redisConn.push(d)
    def __popRedis(self):
        return self.redisConn.pop()

    def __setLogging(self):
        logging.basicConfig(level=logging.DEBUG,
                            format='%(asctime)s %(filename)s[line:%(lineno)d] %(levelname)s %(message)s',
                            datefmt='%a, %d %b %Y %H:%M:%S',
                            filename=self.loggingPath,
                            filemode='w')
        console = logging.StreamHandler()
        console.setLevel(logging.INFO)
        formatter = logging.Formatter('%(name)-12s: %(levelname)-8s %(message)s')
        console.setFormatter(formatter)
        logging.getLogger('').addHandler(console)


    def test(self):
        currentFilePath = sys.path[0]
        print currentFilePath
        logDirPath = currentFilePath + '/../../log/'
        logging.debug('This is debug message')
        logging.info('This is info message')
        logging.warning('This is warning message')



class spider(object):
    def __init__(self, string):
        self.requestUrl = 'http://baike.baidu.com/search/word?word='
        self.http = Http.Http()
        self.secondUrl = 'http://baike.baidu.com'
        self.searchWord(string)

    def searchWord(self, string):
        splitList = string.split(':')
        self.keyword = splitList[-1]
        self.limitWord = splitList[1:-1]

    def start(self):
        code, msg, res = self.http.open(self.requestUrl + self.keyword)
        if code == 200:
            try:
                result = self.analyze(res,self.limitWord)
                return result
            except Exception:
                return ERROR.PARSEERROR,'parse error'
        else:
            return ERROR.NETWORK,'network failure'

    def analyze(self, res, limitWord):
        self.soup = bs(res, 'html.parser')
        existFlag = self.soup.find('div', class_='no-result')
        if existFlag != None:
            return ERROR.DATANONE, 'error'
        polysemant = self.soup.find('div', class_='polysemant-list')
        if polysemant == None:
            code,res = ERROR.LEVEL1, self.parserData(res)
            for i in limitWord:
                if i in res.simpleInfo:
                    return code, res
            return ERROR.DATANONE,'error'
        else:
            max = [0, '']
            for line in polysemant.findAll('li'):
                content = line.text
                weight = 0
                for oneLimit in limitWord:
                    if oneLimit in content:
                        weight += 1
                if max[0] < weight:
                    max[0] = weight
                    max[1] = line
            if max[0] == 0:
                return ERROR.DATANONE, 'error'

            info = max[1]
            if info.find('span', class_='selected') != None:
                logging.info("[+]"+self.keyword+"  choose : "+info.text)
                return ERROR.LEVEL2, self.parserData(res)

            logging.info("[+]"+self.keyword + "  prority choose : " + info.text)
            return ERROR.LEVEL3, self.parserData(self.polysemantAchive(info.a['href']))

    def polysemantAchive(self, url):
        code, msg, res = self.http.open(self.secondUrl + url)
        if code == 200:
            return res
        else:
            return 'network error'

    def parserData(self, res):
        keyword = self.keyword
        complexInfoList = []
        complexParamDict = {}
        simpleInfo = ''
        soup = bs(res, 'html.parser')

        infomation = soup.findAll('div', class_='para')
        if infomation != None:
            simpleInfo += infomation[0].text
            for info in infomation[1:]:
                complexInfoList.append(info.text)

        # 某些次要关键词
        complexParam = soup.find('div', class_='basic-info cmn-clearfix')
        if complexParam != None:
            for line in complexParam.findAll('dl', class_='basicInfo-block'):
                for one in line.findAll('dt', class_='name'):
                    complexParamDict[str(one.string).strip()] = str(one.find_next_sibling('dd', class_='value').string).strip()

        return self.instanceBean([keyword, simpleInfo, complexInfoList, complexParamDict])

    def instanceBean(self, list):
        oneBean = bean()
        oneBean.keyword = list[0]
        oneBean.simpleInfo = list[1]
        oneBean.complexInfoList = list[2]
        oneBean.complexParamDict = list[3]
        return oneBean


class bean(object):
    def __init__(self):
        self.keyword = ''
        self.simpleInfo = ''
        self.complexInfoList = None
        self.complexParamDict = None


class ERROR(object):
    const.NETWORK = -1
    const.DATANONE = -2
    const.PARSEERROR = -3
    const.LEVEL1 = 1
    const.LEVEL2 = 2
    const.LEVEL3 = 3

    # network error
    NETWORK = const.NETWORK
    # have none data
    DATANONE = const.DATANONE
    PARSEERROR = const.PARSEERROR
    # priority 1
    LEVEL1 = const.LEVEL1
    # priority 2
    LEVEL2 = const.LEVEL2
    # priority 3
    LEVEL3 = const.LEVEL3


    msg = {
        NETWORK:"network error",
        DATANONE:"have none data",
        LEVEL1:"confidence level-1",
        LEVEL2:"confidence level-2",
        LEVEL3:"confidence level-3",
        PARSEERROR:"parser error"
    }


if __name__ == '__main__':
    baike = Baike()
    baike.test()



