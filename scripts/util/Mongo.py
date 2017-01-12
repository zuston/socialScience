#! coding:utf-8
import pymongo
from pymongo import MongoClient



class Mongo(object):
    def __init__(self):
        self.client = MongoClient("localhost",27017)
        self.db = self.client["socialScience"]