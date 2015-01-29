# -*- coding: utf-8 -*-
import os,sys
sys.path.append(r'E:\CI_realMonkey')
from automatormonkey.monkeyrunnercore.MonkeyRunner import rMonkeyRunner
from automatormonkey.monkeyrunnercore.info.Enum import *
device=rMonkeyRunner(__file__)
FLAG.SCREENSHOT=False
device.screenIsLock()
device.press('KEYCODE_HOME')
device.click(UIELEMENT.CLASSNAME,'android.widget.TextView', 11)
device.click(UIELEMENT.TEXT,'91助手', 0)


