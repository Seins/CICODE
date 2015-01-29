# -*- coding: utf-8 -*-
'''
    @author wuqiaomin in 20140417
'''
from automatormonkey.monkeyrunnercore.UiAutomator import *
class click(object):
    def __init__(self, device, uiselect):
        self.device = device
        self.uiselect = uiselect
        self.uiautomatorDevice=UiautomatorDevice()
    
    def text(self,text, match=None):
        #TODO ui parser should be added here
        x,y = self.uiselect.text(text,match).getXY()
        self.device.touch(x,y)
        
    def index(self,index, match=None) :
        #TODO ui parser should be added here
        x,y = self.uiselect.index(index,match).getXY()
        self.device.touch(x,y)
    
    def className(self,className, match=None) :
        #TODO ui parser should be added here
        x,y = self.uiselect.className(className,match).getXY()
        self.device.touch(x,y)

    def description(self,description, match=None):
        x,y = self.uiselect.description(description,match).getXY()
        self.device.touch(x,y)
        
    def sid(self, sid, match=None):
        x,y = self.uiselect.sid(sid,match).getXY()
        self.device.touch(x,y)
        
    def longxy(self,x,y):
        #TODO ui parser should be added here
        self.__longClick(x,y)

    def longByClass(self,className, match=None) :
        #TODO ui parser should be added here
        x,y = self.uiselect.className(className,match).getXY()
        self.__longClick(x,y);
        
    def longByText(self,text, match=None):
        #TODO ui parser should be added here
        x,y = self.uiselect.text(text, match).getXY()
        self.__longClick(x,y);

    def longByIndex(self,index, match=None) :
        #TODO ui parser should be added here
        x,y = self.uiselect.index(index ,match).getXY()
        self.__longClick(x,y);
    
    def __longClick(self,x,y):
        self.uiautomatorDevice.swipexy(x,y,x,y,100);