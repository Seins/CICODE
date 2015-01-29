# -*- coding: utf-8 -*-

import os,sys
sys.path.append(r'E:\realMonkey')
from automatormonkey.monkeyrunnercore.MonkeyRunner import rMonkeyRunner
from automatormonkey.monkeyrunnercore.info.Enum import *
#
device=rMonkeyRunner(__file__)

FLAG.SCREENSHOT=True
device.startActivity('com.nd.android.u.oap.nd/com.nd.android.u.cloud.activity.SplashActivity')
device.sleep(5.0)
device.click(UIELEMENT.TEXT,'设置')
device.click(UIELEMENT.TEXT,'个人主页')
