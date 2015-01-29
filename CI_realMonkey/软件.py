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
    
    def setUp(self):
        print 'xml_file_path#'+ROOTPATH
        print 'TestModelName#', modelname
        logPath = modelname+'/test_textview29'
        device=rMonkeyRunner(logPath)
        device.clearAppData("com.dragon.android.pandaspacze")
        device.startActivity("com.dragon.android.pandaspazce/com.dragon.android.pandaspace.main.MainActivity")  
        device.sleep(2.0)

    #def test_textview31(self):
        #print 'TestModelName:', filename
        #logPath = modelname+'/test_textview31'
        #device=rMonkeyRunner(logPath)
        #device.clearAppData("com.dragon.android.pandaspace")
        #device.startActivity("com.dragon.android.pandaspace/com.dragon.android.pandaspace.main.MainActivity")
        #device.sleep(2.0)
        #device.drag(DIRECTION.LEFT)
        #device.drag(DIRECTION.LEFT)
        #device.drag(DIRECTION.LEFT)        
        #device.click(UIELEMENT.TEXT, "软件", 0)
        #device.sleep(2.0)            
        #device.click(UIELEMENT.TEXT, "分类", 0)
        #device.sleep(2.0)
        #device.click(UIELEMENT.TEXT, "游戏", 0)
        #device.sleep(2.0)

    def test_textview29(self):     
        device.click(UIELEMENT.TEXT, "软件", 0)
        device.sleep(2.0)
        device.click(UIELEMENT.TEXT, "推荐", 0)
        device.sleep(2.0)
        
if __name__ == "__main__":
    unittest.main(testRunner=RealTestRunner)
