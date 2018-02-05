package com.marklogic.gradle.task.security

import com.marklogic.gradle.task.MarkLogicTask
import org.gradle.api.tasks.TaskAction

class RotateLogsEncryptionKeyTask extends MarkLogicTask {

	@TaskAction
	void rotateLogsEncryptionKey() {
		SecurityManager mgr = new SecurityManager(getManageClient())
		mgr.rotateLogsEncryptionKey()
	}
}
