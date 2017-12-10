package com.marklogic.gradle.task.roxy

import com.marklogic.gradle.task.MarkLogicTask
import com.marklogic.roxy.test.DefaultJUnitTestReporter
import com.marklogic.roxy.test.JUnitTestSuite
import com.marklogic.roxy.test.RoxyTestManager
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction

/**
 * Task for running test suites found by the Roxy-Test test framework. Tries to mirror "gradle test" in a reasonable
 * way.
 */
class RoxyTestTask extends MarkLogicTask {

	@TaskAction
	void runRoxyTests() {
		def client = getAppConfig().newTestDatabaseClient()
		try {
			def roxyTestManager = new RoxyTestManager(client)
			def suites = roxyTestManager.runAllSuites()
			def report = new DefaultJUnitTestReporter().reportOnJUnitTestSuites(suites)
			println report

			String resultsPath = "build/test-results/roxy-test"
			String resultProperty = "roxyTestResultsPath"
			if (project.hasProperty(resultProperty)) {
				resultsPath = project.property(resultProperty)
			}

			File resultsDir = new File(resultsPath)
			if (resultsDir.exists()) {
				boolean deleted = resultsDir.deleteDir()
				if (!deleted) {
					println "Unable to delete test results directory: " + resultsPath
				}
			}
			resultsDir.mkdirs()

			int fileCount = 0;
			boolean testsFailed = false
			for (JUnitTestSuite suite : suites) {
				if (suite.hasTestFailures()) {
					testsFailed = true
				}
				String xml = suite.getXml()
				String filename = "TEST-" + suite.getName() + ".xml"
				org.springframework.util.FileCopyUtils.copy(xml.getBytes(), new File(resultsDir, filename))
				fileCount++;
			}

			println "\n" + fileCount + " test result files were written to: " + resultsPath

			if (testsFailed) {
				throw new GradleException("There were failing tests. See the test results at: " + resultsPath)
			}

		} finally {
			client.release()
		}
	}
}
