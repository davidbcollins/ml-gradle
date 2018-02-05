package com.marklogic.gradle.task.security

import com.marklogic.gradle.task.MarkLogicTask
import org.gradle.api.tasks.TaskAction

class RotateConfigEncryptionKeyTask extends MarkLogicTask {

	@TaskAction
	void rotateConfigEncryptionKey() {
		SecurityManager mgr = new SecurityManager(getManageClient())
		mgr.rotateConfigEncryptionKey()
	}
}
