# -*- coding: utf-8 -*-

import os,sys
sys.path.append(r'E:\CI_realMonkey\realMonkey')
from automatormonkey.monkeyrunnercore.MonkeyRunner import rMonkeyRunner
from automatormonkey.monkeyrunnercore.info.Enum import *

device=rMonkeyRunner(__file__)
FLAG.SCREENSHOT=False

device.click(UIELEMENT.TEXT,'91助手', 0)

elementList = device.getAllElements()
elementListB = device.getAllElements()
if len(elementList) != len(elementListB):
    break
else:
    for i in xrange(len(elementList)):
        elementList[i].equal(elementListB[i])