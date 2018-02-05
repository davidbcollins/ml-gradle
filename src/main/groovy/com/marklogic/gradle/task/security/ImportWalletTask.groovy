package com.marklogic.gradle.task.security

import com.marklogic.gradle.task.MarkLogicTask
import org.gradle.api.tasks.TaskAction

class ImportWalletTask extends MarkLogicTask {

	@TaskAction
	void ImportWallet() {
		if (!project.hasProperty( "importWalletPath")) {
			println "Please specify the path from which to import the encrypted wallet file - e.g. -PimportWalletPath=/backups/MarkLogic.wallet.bak"
			return
		}
		if (!project.hasProperty( "importWalletPassword")) {
			println "Please specify the password with which to decrypt the encrypted wallet file - e.g. -PimportWalletPassword=password"
			return
		}
		SecurityManager mgr = new SecurityManager(getManageClient())
		mgr.importWallet(project.property("importWalletPath"), project.property("importWalletPassword"))
	}
}
