# -*- coding: utf-8 -*-

import os,sys
sys.path.append(r'E:\realMonkey')
from automatormonkey.monkeyrunnercore.MonkeyRunner import rMonkeyRunner
from automatormonkey.monkeyrunnercore.info.Enum import *
#
device=rMonkeyRunner(__file__)

#device.assertEqual('w','2', True)
FLAG.SCREENSHOT=False
#device.uninstall('com.dragon.android.pandaspace')
#device.install('3.9.6.1.apk')


#device.clearAppData('com.nd.android.pandahome2')
#device.startActivity('com.nd.android.pandahome2/com.nd.hilauncherdev.launcher.Launcher')
#device.drag(DIRECTION.LEFT)
#device.sleep(2.0)
#device.drag(DIRECTION.LEFT)
#device.sleep(2.0)
#device.drag(DIRECTION.LEFT)
#device.sleep(2.0)
device.testMethod('wqmdump.xml')
device.clickAndWaitForNewWindow(UIELEMENT.CLASSNAME,'android.widget.Button', 0)
#device.sleep(2.0)
#device.click(UIELEMENT.CLASSNAME,'android.view.View',3)
#device.sleep(3.0)
#device.click(UIELEMENT.TEXT, '排行')

