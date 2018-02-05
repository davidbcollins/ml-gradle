package com.marklogic.gradle.task.security

import com.marklogic.gradle.task.MarkLogicTask
import org.gradle.api.tasks.TaskAction

class RotateDataEncryptionKeyTask extends MarkLogicTask {

	@TaskAction
	void rotateDataEncryptionKey() {
		SecurityManager mgr = new SecurityManager(getManageClient())
		mgr.rotateDataEncryptionKey()
	}
}
