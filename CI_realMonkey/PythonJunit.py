import unittest
from selenium import webdriver
import time
import HTMLTestRunner
import sys

class MyTestRunner:
    def __init__(self, stream=sys.stderr, descriptions=1, verbosity=1):
        self.stream = stream
        self.descriptions = descriptions
        self.verbosity = verbosity

    def run(self, test):
        result = MyTestResult(self.stream, self.descriptions, self.verbosity)
        print 'Note: Your Unit Tests Starts\n'
        print('')
        startTime = time.time()
        test(result)
        stopTime = time.time()
        timeTaken = stopTime - startTime
        print result.separator2
        run = result.testsRun
        print("Run %d test%s in %.3fs\n" %
                            (run, run != 1 and "s" or "", timeTaken))

        failed, errored = map(len, (result.failures, result.errors))

        print "[  PASSED  ] %d tests\n" % (run - failed - errored)

        if not result.wasSuccessful():
            errorsummary = ""
            if failed:
                print "[  FAILED  ] %d tests, listed below:\n" % failed
                for failedtest, failederorr in result.failures:
                    print "[  FAILED  ] %s\n" % failedtest
            if errored:
                print "[  ERRORED ] %d tests\n" % errored
                for erroredtest, erorrmsg in result.errors:
                    print "[  ERRORED ] %s\n" % erroredtest
            if failed:
                print("%d ERRORED TEST\n" % failed)
            if errored:
                print("%d ERRORED TEST\n" % errored)

        return result

class MyTestResult(unittest.TestResult):
    
    separator1 = '[----------] '
    separator2 = '[==========] '
    def __init__(self, stream=sys.stderr, descriptions=1, verbosity=1):
        unittest.TestResult.__init__(self)
        self.stream = stream
        self.showAll = verbosity > 1
        self.dots = verbosity == 1
        self.descriptions = descriptions

    def getDescription(self, test):
        if self.descriptions:
            return test.shortDescription() or str(test)
        else:
            return str(test)

    def startTest(self, test):
        print '[ Run ] \n'
        print(self.getDescription(test))
        unittest.TestResult.startTest(self, test)
        if self.showAll:
            print(self.getDescription(test))
            print(" ... ")

    def addSuccess(self, test):
        unittest.TestResult.addSuccess(self, test)
        if self.showAll:
            print("ok\n")
        elif self.dots:
            print '[ OK ] \n'
            print(self.getDescription(test))

    def addError(self, test, err):
        unittest.TestResult.addError(self, test, err)
        if self.showAll:
            print("ERROR")
        elif self.dots:
            print('E')

    def addFailure(self, test, err):
        unittest.TestResult.addFailure(self, test, err)
        if self.showAll:
            print("FAIL\n")
        elif self.dots:
            print '[  FAILED  ] \n'
            print(self.getDescription(test))
            err
            print(self._exc_info_to_string(err, test))


class TestJunit(unittest.TestCase):
    #----------------------------------------------------------------------
    def setUp(self):
        """"""
        print 'setUp'
    
    #----------------------------------------------------------------------
    def tearDown(self):
        """"""
        print 'tearDown'
        
    def testTestJunit(self):
        print 'testTestJunit'
        time.sleep(2)
        a = 1/0
        
    def testError(self):
        print 'testError'
        self.assertEqual(1,2)
        
class TestJunitTwo(unittest.TestCase):
    #----------------------------------------------------------------------
    def setUp(self):
        """"""
        print 'setUp'
    
    #----------------------------------------------------------------------
    def tearDown(self):
        """"""
        print 'tearDown'
        
    def testTestJunit(self):
        print 'testTestJunit'
        time.sleep(2)
        a = 1/0
        
    def testError(self):
        print 'testError'
        self.assertEqual(1,2)
        

if __name__ == "__main__":
    unittest.main(testRunner=MyTestRunner())
    
    testsuite = unittest.TestSuite()
    testsuite.addTests(TestJunit)
    unittest.TextTestRunner(verbosity=2).run(testsuite)  
    filename="e:\\result.html"
    fp=file(filename,'wb')
    
    runner=HTMLTestRunner.HTMLTestRunner(stream=fp,title='Result',description='Test_Report')
    runner.run(testsuite)