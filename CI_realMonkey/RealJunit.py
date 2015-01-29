# -*- coding: utf-8 -*-
import sys
import time
import unittest
import datetime
import StringIO
from xml.dom import minidom
import os,sys
from automatormonkey.monkeyrunnercore.MonkeyRunner import rMonkeyRunner
from automatormonkey.monkeyrunnercore.info.Enum import *


STATUS = {
    0: 'pass',
    1: 'fail',
    2: 'error',
    }

class OutputRedirector(object):
    """ Wrapper to redirect stdout or stderr """
    def __init__(self, fp):
        self.fp = fp

    def write(self, s):
        self.fp.write(s)

    def writelines(self, lines):
        self.fp.writelines(lines)

    def flush(self):
        self.fp.flush()

stdout_redirector = OutputRedirector(sys.stdout)
stderr_redirector = OutputRedirector(sys.stderr)

########################################################################
class RealTestRunner:
    """"""

    #----------------------------------------------------------------------
    def __init__(self, stream=sys.stdout, verbosity=1, title=None, description=None):
        """Constructor"""
        self.doc = minidom.Document()
        self.verbosity = verbosity
        self.startTime = time.time()
        
    def run(self, test):
        result = RealTestResult(self.verbosity)
        test(result)
        run = result.testsRun
        endtime = time.time()
        print run,'%.3fs'%(endtime - self.startTime)
        self.generateXmlReport(result.result)
        return result
    
    def generateXmlReport(self,result):  
        self.doc.appendChild(self.generateAllNode(result))
        f = file('D:/testReport.xml', 'w')
        self.doc.writexml(f)
        f.close()
        
    def generateTestCaseNode(self, cid, name, test_method, time, msg):
        print 'generateTestCaseNode'
        testcase = self.doc.createElement('testcase')
        testcase.setAttribute('classname', name.split('\'')[1])
        testcase.setAttribute('name', test_method.split(' ')[0])
        testcase.setAttribute('time', time)
        if cid == 1:
            testcase.appendChild(self.generateFailureNode(msg))
        elif cid == 2:
            testcase.appendChild(self.generateErrorNode(msg))
        return testcase
        
    def generateErrorNode(self, msg):
        print 'generateErrorNode'
        error = self.doc.createElement('error')
        error.appendChild(self.doc.createTextNode(msg))
        return error
        
    def generateFailureNode(self, msg):
        print 'generateFailureNode'    
        failure = self.doc.createElement('failure')
        failure.appendChild(self.doc.createTextNode(msg))
        return failure        
    
    def generateTestSuitNode(self, errors, failures, name, tests, time, testCaseNode):
        print 'generateTestSuitNode'
        testsuit = self.doc.createElement('testsuit')
        testsuit.setAttribute('errors', errors)
        testsuit.setAttribute('failures', failures)
        testsuit.setAttribute('name', name.split('\'')[1])
        testsuit.setAttribute('tests', tests)
        testsuit.setAttribute('time', time)
        for node in testCaseNode:
            testsuit.appendChild(node)
        return testsuit

    def generateAllNode(self, result):
        '''
        testsuit,errors,failures, name , tests, totaltime
          testcase, name, methodname, time
            if msg != ''
               errormessage,errortype
        '''
        sortResult = self.sortResult(result)   
        testsuits = self.doc.createElement('testsuits')
        for cid, (cls, cls_result) in enumerate(sortResult):
            #testsuit
            testCaseNode = []
            name = str(cls)
            mf = me = tests = totaltime = 0
            for _id, _test_method, _output, _msg, _time in cls_result:
                tests += 1
                totaltime += float(_time)
                if _id == 1 : mf += 1
                elif _id == 2 : me += 1
                testCaseNode.append(self.generateTestCaseNode(_id, name, str(_test_method), str(_time), str(_msg)))
            testsuits.appendChild(self.generateTestSuitNode(str(me), str(mf), name, str(tests), str(totaltime), testCaseNode))
        return testsuits
                
    def sortResult(self, result_list):
        # unittest does not seems to run in any particular order.
        # Here at least we want to group them together by class.
        rmap = {}
        classes = []
        for n,t,o,e,tm in result_list:
            cls = t.__class__
            if not rmap.has_key(cls):
                rmap[cls] = []
                classes.append(cls)
            rmap[cls].append((n,t,o,e,tm))
        r = [(cls, rmap[cls]) for cls in classes]
        return r 
        
              
########################################################################
TestResult = unittest.TestResult

class RealTestResult(TestResult):
    """"""

    #----------------------------------------------------------------------
    def __init__(self, verbosity):
        """Constructor"""
        TestResult.__init__(self)
        self.stdout0 = None
        self.stderr0 = None
        
        self.verbosity = verbosity
        self.success_count = 0
        self.failure_count = 0
        self.error_count = 0
        self.result = []
        
    def complete_output(self):
        """
        Disconnect output redirection and return buffer.
        Safe to call multiple times.
        """
        if self.stdout0:
            sys.stdout = self.stdout0
            sys.stderr = self.stderr0
            self.stdout0 = None
            self.stderr0 = None
        return self.outputBuffer.getvalue()    
        
    def startTest(self, test):
        print '--------------------'
        TestResult.startTest(self, test)
        self.outputBuffer = StringIO.StringIO()
        stdout_redirector.fp = self.outputBuffer
        stderr_redirector.fp = self.outputBuffer
        self.stdout0 = sys.stdout
        self.stderr0 = sys.stderr
        sys.stdout = stdout_redirector
        sys.stderr = stderr_redirector
        self.startTime = time.clock()
    
    def addSuccess(self, test):
        print 'addSuccess'
        self.success_count += 1
        TestResult.addSuccess(self, test)
        output = self.complete_output()
        endtime = '%.3f'%(time.clock() - self.startTime)
        self.result.append((0, test, output, '', endtime))

    def addFailure(self, test, err):
        print 'addFailure'
        self.failure_count += 1
        TestResult.addFailure(self, test, err)
        output = self.complete_output()
        _, _exc_str = self.failures[-1]
        endtime = '%.3f'%(time.clock() - self.startTime)
        self.result.append((1, test, output, _exc_str, endtime))
    
    def addError(self, test, err):
        print 'addError'
        self.error_count += 1
        TestResult.addError(self, test, err)
        output = self.complete_output()
        _, _exc_str = self.errors[-1]
        endtime = '%.3f'%(time.clock() - self.startTime)
        self.result.append((2, test, output, _exc_str, endtime))
    
    def stopTest(self, test):
        print '--------------------'
        self.complete_output()

if __name__ == "__main__":
    unittest.main(testRunner=RealTestRunner())   
    
        
        
    
    
        
    
    
