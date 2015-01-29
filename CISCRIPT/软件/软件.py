# -*- coding: utf-8 -*-
import os,sys,unittest
from automatormonkey.monkeyrunnercore.MonkeyRunner import rMonkeyRunner
from automatormonkey.monkeyrunnercore.info.Enum import *
from automatormonkey.realunittest.RealJunit import *

ROOTPATH = 'D:/CIServers'
filename = __file__.split('\\')[-1].split('.')[0]
if os.path.exists(ROOTPATH)==False:
    os.mkdir(ROOTPATH)
modelname = '%s/%s'%(ROOTPATH, filename)
if os.path.exists(modelname)==False:
    os.mkdir(modelname)

class ModelName(unittest.TestCase):

    def test_TextView40(self):
        print 'xml_file_path#'+ROOTPATH
        print 'TestModelName#', filename
        logPath = modelname+'/test_TextView40'
        device=rMonkeyRunner(logPath)
        device.clearAppData("com.dragon.android.pandaspace")
        device.startActivity("com.dragon.android.pandaspace/com.dragon.android.pandaspace.main.MainActivity")
        device.sleep(2.0)
        device.click(UIELEMENT.TEXT, "第一层", 0)
        device.sleep(2.0)
        device.click(UIELEMENT.TEXT, "第二层", 0)
        device.sleep(2.0)

    def test_TextView43(self):
        print 'xml_file_path#'+ROOTPATH
        print 'TestModelName#', filename
        logPath = modelname+'/test_TextView43'
        device=rMonkeyRunner(logPath)
        device.clearAppData("com.dragon.android.pandaspace")
        device.startActivity("com.dragon.android.pandaspace/com.dragon.android.pandaspace.main.MainActivity")
        device.sleep(2.0)
        device.click(UIELEMENT.TEXT, "第一层3", 0)
        device.sleep(2.0)
        device.click(UIELEMENT.TEXT, "第二层2", 0)
        device.sleep(2.0)

    def test_TextView39(self):
        print 'xml_file_path#'+ROOTPATH
        print 'TestModelName#', filename
        logPath = modelname+'/test_TextView39'
        device=rMonkeyRunner(logPath)
        device.clearAppData("com.dragon.android.pandaspace")
        device.startActivity("com.dragon.android.pandaspace/com.dragon.android.pandaspace.main.MainActivity")
        device.sleep(2.0)
        device.click(UIELEMENT.TEXT, "第二层2", 0)
        device.sleep(2.0)

    def test_TextView42(self):
        print 'xml_file_path#'+ROOTPATH
        print 'TestModelName#', filename
        logPath = modelname+'/test_TextView42'
        device=rMonkeyRunner(logPath)
        device.clearAppData("com.dragon.android.pandaspace")
        device.startActivity("com.dragon.android.pandaspace/com.dragon.android.pandaspace.main.MainActivity")
        device.sleep(2.0)
        device.click(UIELEMENT.TEXT, "第一层4", 0)
        device.sleep(2.0)

    def test_TextView44(self):
        print 'xml_file_path#'+ROOTPATH
        print 'TestModelName#', filename
        logPath = modelname+'/test_TextView44'
        device=rMonkeyRunner(logPath)
        device.clearAppData("com.dragon.android.pandaspace")
        device.startActivity("com.dragon.android.pandaspace/com.dragon.android.pandaspace.main.MainActivity")
        device.sleep(2.0)
        device.click(UIELEMENT.TEXT, "第一层5", 0)
        device.sleep(2.0)
if __name__ == "__main__":
    unittest.main(testRunner=RealTestRunner)