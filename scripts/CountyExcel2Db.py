#!coding:utf-8
import xlrd
import xlwt
import os
import logging
from util import Mongo as mg

mongoDb = mg.Mongo().db["county"]

class importExcel(object):
    def __init__(self):
        self.dataPath = '../data/officerExcel/'

    def load(self, fileName,flag):
        if os.path.exists(self.dataPath) and os.path.isfile(self.dataPath + fileName):
            excelName = self.dataPath + fileName
            tag_row = 1 if flag==0 else 1
            book = xlrd.open_workbook(excelName, encoding_override='utf-8')
            sh = book.sheet_by_index(0)

            countyDict = dict()
            for rx in range(tag_row, sh.nrows):
                province = unicode(sh.row(rx)[1].value)
                city = unicode(sh.row(rx)[3].value)
                area = unicode(sh.row(rx)[5].value)
                code = str(long(sh.row(rx)[4].value))
                year = str(long(sh.row(rx)[6].value))
                name = unicode(sh.row(rx)[7].value)

                if year == "" or name == "":
                    continue

                if flag==0:
                    if countyDict.has_key(code):
                        record = {
                            "year": year,
                            "name": name
                        }
                        countyDict[code][4].append(record)
                        continue

                    countyDict[code] = [province, city, area, code, [
                        {"year": year, "name": name}
                    ]]

                if flag==1:
                    age = self.filter(sh.row(rx)[8].value)
                    birth = str((sh.row(rx)[10].value))
                    tenure = self.filter(sh.row(rx)[12].value)
                    origin = unicode(sh.row(rx)[13].value)
                    promote = self.filter(sh.row(rx)[15].value)
                    before = unicode(sh.row(rx)[19].value)
                    after = unicode(sh.row(rx)[20].value)
                    record = {
                        "year": year,
                        "name": name,
                        "age": age,
                        "birth": birth,
                        "tenure": tenure,
                        "origin": origin,
                        "promote": promote,
                        "before": before,
                        "after": after
                    }
                    if countyDict.has_key(code):
                        countyDict[code][4].append(record)
                        continue

                    countyDict[code] = [province, city, area, code, [
                        record
                    ]]

            return countyDict
        else:
            logging.error("file path error")
            return None

    def filter(self,string):
        if string=="" or string==" ":
            return string
        return str(long(string))

class MongoDao(object):
    @staticmethod
    def save(data):
        return mongoDb.insert(data)


    # 将县委和县长这一类放在一个document里面，需要判断
    @staticmethod
    def isExist(code):
        res = mongoDb.find_one({"code":code})
        if res is None:
            return False
        return True

    @staticmethod
    def madd(code,dict,keyName):
        res = mongoDb.find_one({"code":code})
        res[keyName] = dict
        return mongoDb.update({"_id":res["_id"]},res)




class County2Db(object):
    def __init__(self):
        pass

    def start(self,data,flag):
        dataList = data
        for k,oneLine in dataList.items():
            code = oneLine[-2]
            if self.filterMongo(code) is True:
                logging.info("[+]insert to exist document\t"+oneLine[2])
                self.updateToMongo(code,oneLine,"party")
                continue

            self.saveToMongo(oneLine,flag)

    def saveToMongo(self,oneline,flag):
        listName = "gov" if flag==0 else "party"
        dataDict = {
            "province": oneline[0],
            "city": oneline[1],
            "area": oneline[2],
            "code": oneline[3],
            listName: oneline[4]
        }
        MongoDao.save(dataDict)

    def filterMongo(self,code):
        return MongoDao.isExist(code)

    def updateToMongo(self,code,oneline,keyName):
        MongoDao.madd(code,oneline[4],keyName)


if __name__ == "__main__":
    from util import Logging
    Logging.Logging("../log/excel2Db.log")

    ie = importExcel()

    c2d = County2Db()

    # 0为县长，1为县委
    d = ie.load("县长的名单.xlsx", 0)
    c2d.start(d,0)

    date = ie.load("县委书记名单.xls",1)
    c2d.start(date,1)
