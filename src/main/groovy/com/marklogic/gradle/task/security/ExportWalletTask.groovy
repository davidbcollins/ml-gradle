package com.marklogic.gradle.task.security

import com.marklogic.gradle.task.MarkLogicTask
import org.gradle.api.tasks.TaskAction

class ExportWalletTask extends MarkLogicTask {

	@TaskAction
	void exportWallet() {
		if (!project.hasProperty( "exportWalletPath")) {
			println "Please specify the path to which to export the encrypted wallet file - e.g. -PexportWalletPath=/backups/MarkLogic.wallet.bak"
			return
		}
		if (!project.hasProperty( "exportWalletPassword")) {
			println "Please specify the password with which to encrypt the wallet file - e.g. -PexportWalletPassword=password"
			return
		}
		SecurityManager mgr = new SecurityManager(getManageClient())
		mgr.exportWallet(project.property("exportWalletPath"), project.property("exportWalletPassword"))
	}
}
