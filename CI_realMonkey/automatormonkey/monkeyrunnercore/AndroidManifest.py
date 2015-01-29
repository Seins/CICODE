## -*- coding: utf-8 -*-
'''
Created on 2011-7-21

@author: yanbo01
'''

import os
import zipfile
import xml.dom.minidom
import inspect
from xml.dom.minidom import Node

class AndroidManifest():
    
    def getVersion(self, path):
        fileName = 'AndroidManifest.xml'
        self.__unzip(path, fileName)
        file = self.__decode(fileName)
        
        return self.__parseRootAtt(file)
    
    def getPackage(self, path):
        fileName = 'AndroidManifest.xml'
        self.__unzip(path, fileName)
        file = self.__decode(fileName)
        
        return self.__parseRootAtt(file, att='package')
    
    def getMainActivity(self, path):
        fileName = 'AndroidManifest.xml'
        self.__unzip(path, fileName)
        file = self.__decode(fileName)
        
        root = xml.dom.minidom.parse(file).childNodes[0]
        appNode = root.getElementsByTagName('application')[0]
        
        for activityNode in appNode.getElementsByTagName('activity'):
            for intentNode in activityNode.getElementsByTagName('intent-filter'):
                for actionNode in intentNode.getElementsByTagName('action'):
                    if actionNode.getAttribute('android:name') == 'android.intent.action.MAIN':
                        return activityNode.getAttribute('android:name')
        
        return None
    
    def getAllActivity(self, path):
        fileName = 'AndroidManifest.xml'
        self.__unzip(path, fileName)
        file = self.__decode(fileName)
        
        root = xml.dom.minidom.parse(file).childNodes[0]
        appNode = root.getElementsByTagName('application')[0]    
        activiytList = []
        for activityNode in appNode.getElementsByTagName('activity'):
            activity = activityNode.getAttribute('android:name')
            activiytList.append(activity)
        return activiytList
    
    def getAllService(self, path):
        fileName = 'AndroidManifest.xml'
        self.__unzip(path, fileName)
        file = self.__decode(fileName)
        
        root = xml.dom.minidom.parse(file).childNodes[0]
        appNode = root.getElementsByTagName('application')[0]    
        serviceList = []
        for serviceNode in appNode.getElementsByTagName('service'):
            service = serviceNode.getAttribute('android:name')
            serviceList.append(service)
        return serviceList    
    
    def getAllProvider(self, path):
        fileName = 'AndroidManifest.xml'
        self.__unzip(path, fileName)
        file = self.__decode(fileName)
        
        root = xml.dom.minidom.parse(file).childNodes[0]
        appNode = root.getElementsByTagName('application')[0]    
        providerList = []
        for providerNode in appNode.getElementsByTagName('provider'):
            provider = providerNode.getAttribute('android:name')
            providerList.append(provider)
        return providerList    
    
    def getAllReceiver(self, path):
        fileName = 'AndroidManifest.xml'
        self.__unzip(path, fileName)
        file = self.__decode(fileName)
        
        root = xml.dom.minidom.parse(file).childNodes[0]
        appNode = root.getElementsByTagName('application')[0]    
        receiverList = []
        for receiverNode in appNode.getElementsByTagName('receiver'):
            receiver = receiverNode.getAttribute('android:name')
            receiverList.append(receiver)
        return receiverList   
        
    def __parseRootAtt(self, file, att='android:versionName'):
        root = xml.dom.minidom.parse(file).childNodes[0]
        return root.getAttribute(att)
    
    def __unzip(self, path, fileName):
        os.popen(r'%s\res\unzip.exe -o %s %s' % (self.script_path(),path, fileName))
#        zipFile = zipfile.ZipFile(path,'a', compression=zipfile.ZIP_STORED)
#        zipFile.extract(fileName)
#        zipFile.close()
        
    def script_path(self):
        this_file = inspect.getfile(inspect.currentframe())
        return os.path.abspath(os.path.dirname(this_file))
    
    def __decode(self, fileName):
        genFile = 'test.xml'
        os.popen(r'java -jar %s\res\AXMLPrinter2.jar %s > %s' % (self.script_path(),fileName, genFile))
        return self.__realdecode(genFile)
        # return genFile

    def __realdecode(self,fileName) :
        '''
        '''
        # return fileName
        xml = 'realManifest.xml'
        try :
            os.remove(xml)
        except Exception , e :
            '''
            '''
        fb = open(xml , 'a')
        for tmp in open(fileName) :
            try :
                # tmp = tmp.decode('uft-8').encode('gbk')
                tmp = tmp.decode('gbk').encode('utf-8')
                self.__writefile(fb,tmp)
            except Exception , e:
                '''
                    TODO
                '''
                print e
                print 'error : ' + tmp
                fb.close()
                return fileName
                # print e
        fb.close()
        return xml

    def __writefile(self,fb,content) :
        # fb = open(fileName ,'a')
        fb.write(content)
        fb.flush()    
if __name__ == '__main__':
    test = AndroidManifest()
    # print test.getMainActivity(r'e:\baidumobile_2010beta3_android_757b(1).apk')
    temp = test.getPackage(r'91zhuomian_5320.apk')
    print temp

#    print test.getMainActivity(r'E:\test\bdmobile.android.app-1.apk')
