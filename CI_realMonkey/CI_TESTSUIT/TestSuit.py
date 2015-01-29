# -*- coding: utf-8 -*-

import io
import subprocess
import time
import os,sys
import codecs
reload(sys)

class testsuit(object):
    def __init__(self):
        self.runScriptName = 'testScript.py'
        print 'init'
        
    def initScript(self):
        script = '# -*- coding: utf-8 -*-\n'
        script += 'import os,sys\n'
        script += 'sys.path.append(r\'E:\\CI_realMonkey\')\n'
        script += 'from automatormonkey.monkeyrunnercore.MonkeyRunner import rMonkeyRunner\n'
        script += 'from automatormonkey.monkeyrunnercore.info.Enum import *\n'
        script += 'device=rMonkeyRunner(__file__)\n'
        script += 'FLAG.SCREENSHOT=False\n'
        return script
    
    def readConfig(self):
        return self.readFile('config.txt')
    
    def runTestCase(self, scriptList):
        self.writeScript(scriptList)

        cmd = 'python %s\\%s'%(os.getcwd(),self.runScriptName)
        print cmd
        sub = self.runShell(cmd)
        print sub.stdout.read()
            
    def writeScript(self, scriptList):
        writeFile = file(self.runScriptName, 'w')
        writeFile.write(self.initScript())
        for script in scriptList:
            for str in self.readFile('%s\\%s'%(os.getcwd(),script.strip('\n').strip('\r'))):
                writeFile.write(str.decode('gbk').encode('utf-8'))
            writeFile.write('\n')
        writeFile.close()
            
    def readFile(self, scriptPath):
        print scriptPath
        file = open(scriptPath, 'a+')
        file.seek(0)
        scriptList = file.readlines()
        file.close()
        return scriptList        
            
    def runShell(self, cmd):
        sub2 = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True)
        while 1:
            ret1 = subprocess.Popen.poll(sub2)
            if ret1 == 0:
                break
            elif ret1 is None:
                time.sleep(0.1)
            else:
                break
        return sub2        

if __name__ == '__main__':
    tests = testsuit()
    tests.runTestCase(tests.readConfig())