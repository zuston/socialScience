#!coding:utf-8
import xlrd
import xlwt
import os
import logging
from util import Mongo as mg


'''
将地级市官员信息放入mongodb中
'''

mongoDb = mg.Mongo().db["city"]


class importExcel(object):
    def __init__(self):
        self.dataPath = '../data/officerExcel/'

    def load(self, fileName):
        if os.path.exists(self.dataPath) and os.path.isfile(self.dataPath + fileName):
            excelName = self.dataPath + fileName
            tag_row = 3
            book = xlrd.open_workbook(excelName, encoding_override='utf-8')
            sh = book.sheet_by_index(0)

            cityDict = dict()
            for rx in range(tag_row, sh.nrows):
                province = unicode(sh.row(rx)[0].value)
                city = unicode(sh.row(rx)[2].value)
                code = str(long(sh.row(rx)[3].value))
                year = str(long(sh.row(rx)[4].value))
                # 省委书记名称
                provincePartyName = unicode(sh.row(rx)[5].value)
                # 省委书记籍贯
                provincePartyNativePlace = unicode(sh.row(rx)[6].value)

                # 省长名称
                provinceGovName = unicode(sh.row(rx)[7].value)
                # 省长籍贯
                provinceGovNativePlace = unicode(sh.row(rx)[8].value)

                # 市长名字
                name = unicode(sh.row(rx)[9].value)
                # 出生日期
                birth = unicode(sh.row(rx)[10].value)
                # 年龄
                age = self.filter(sh.row(rx)[11].value)
                # 籍贯
                nativePlace = unicode(sh.row(rx)[13].value)
                # 任职前经历
                beforeExperience = unicode(sh.row(rx)[18].value)
                # 去向
                afterExperience = unicode(sh.row(rx)[19].value)
                # 教育
                education = unicode(sh.row(rx)[20].value)
                # 标注去向
                promotion = self.filter(sh.row(rx)[21].value)
                # 来源
                origin = unicode(sh.row(rx)[23].value)

                if year == "" or name == "":
                    continue

                officerInfoDict = {
                    "year": year,
                    "provincePartyName":provincePartyName,
                    "provincePartyNativePlace":provincePartyNativePlace,
                    "provinceGovName":provinceGovName,
                    "provinceGovNativePlace":provinceGovNativePlace,
                    "name":name,
                    "birth":birth,
                    "age":age,
                    "nativePlace":nativePlace,
                    "beforeExperience":beforeExperience,
                    "afterExperience":afterExperience,
                    "education":education,
                    "promotion":promotion,
                    "origin":origin
                }


                if cityDict.has_key(code):

                    cityDict[code]["mayor"][year] = officerInfoDict

                else:

                    cityDict[code] = {
                        "province":province,
                        "city":city,
                        "code":code
                    }
                    cityDict[code]["mayor"] = dict()
                    cityDict[code]["mayor"][year] = officerInfoDict

            return cityDict
        else:
            logging.error("file path error")
            return None

    def filter(self,string):
        if string=="" or string==" ":
            return string
        return str(long(string))


class city2Mongo(object):
    def __init__(self):
        pass


    def start(self,data):
        for code,oneline in data.items():
            mongoDb.insert(oneline)


if __name__ == "__main__":
    from util import Logging
    Logging.Logging("../log/officerCITY.log")

    ie = importExcel()
    allDict = ie.load("地级市市长.xls")

    c2m = city2Mongo()
    c2m.start(allDict)