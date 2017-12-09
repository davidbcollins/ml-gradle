package com.marklogic.gradle.task.roxy

import com.marklogic.gradle.task.MarkLogicTask
import com.marklogic.roxy.test.DefaultJUnitTestReporter
import com.marklogic.roxy.test.RoxyTestManager
import org.gradle.api.tasks.TaskAction

class RoxyTestTask extends MarkLogicTask {

	@TaskAction
	void runRoxyTests() {
		def client = getAppConfig().newTestDatabaseClient()
		try {
			def roxyTestManager = new RoxyTestManager(client)
			def suites = roxyTestManager.runAllSuites()
			def report = new DefaultJUnitTestReporter().reportOnJUnitTestSuites(suites)

			// TODO roxy-test-client should know how to write its XML to files
			
			println report
		} finally {
			client.release()
		}
	}
}
