package org.jtester.unitils.database;


import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.unitils.core.dbsupport.SQLHandler;
import org.unitils.dbmaintainer.DBMaintainer;
//import org.unitils.dbmaintainer.script.Script;
//import org.unitils.dbmaintainer.version.Version;
//import org.unitils.dbmaintainer.version.VersionScriptPair;
//import java.util.List;

public class JTesterDbMaintainer extends DBMaintainer {
	private static Log log = LogFactory.getLog(JTesterDbMaintainer.class);

	public JTesterDbMaintainer(Properties cfg, SQLHandler sqlHandler) {
		super(cfg, sqlHandler);
	}

	public void updateDatabase() {
//		// Get current version
//		Version currentVersion = versionSource.getDbVersion();
//
//		// Clear the database and retrieve scripts
//		List<VersionScriptPair> versionScriptPairs;
//		boolean updateDatabaseFromScratch = true;// updateDatabaseFromScratch(currentVersion);
//		if (updateDatabaseFromScratch) {
//			// constraints are removed before clearing the database, to be sure
//			// there will be no
//			// conflicts when dropping tables
//			constraintsDisabler.disableConstraints();
//			dbClearer.clearSchemas();
//			dbCodeClearer.clearSchemasCode();
//			versionScriptPairs = scriptSource.getAllScripts();
//		} else {
//			versionScriptPairs = scriptSource.getNewScripts(currentVersion);
//		}
//
//		// Check whether there are new scripts
//		if (!versionScriptPairs.isEmpty()) {
//			logger.info("Database update scripts have been found and will be executed on the database");
//
//			// Remove data from the database, that could cause errors when
//			// executing scripts. Such
//			// as for example when added a not null column.
//			if (dbCleaner != null) {
//				dbCleaner.cleanSchemas();
//			}
//
//			// Excute all of the scripts
//			executeScripts(versionScriptPairs);
//
//			// Execute postprocessing code scripts, if any
//			executeCodeScripts(scriptSource.getAllPostProcessingCodeScripts());
//
//			// Disable FK and not null constraints, if enabled
//			if (disableConstraintsEnabled) {
//				constraintsDisabler.disableConstraints();
//			}
//			// Update sequences to a sufficiently high value, if enabled
//			if (sequenceUpdater != null) {
//				sequenceUpdater.updateSequences();
//			}
//			// Generate a DTD to enable validation and completion in data xml
//			// files, if enabled
//			if (dataSetStructureGenerator != null) {
//				dataSetStructureGenerator.generateDataSetStructure();
//			}
//		}
//
//		// If the database structure was recreated from scratch, also recreate
//		// the database code
//		if (updateDatabaseFromScratch
//		// If the last code update failed, retry if configured to do so
//				|| (!versionSource.isLastCodeUpdateSucceeded() && keepRetryingAfterError)
//				// If a code script was added of changed, recreate the database
//				// code
//				|| scriptSource.getCodeScriptsTimestamp() > versionSource.getCodeScriptsTimestamp()) {
//
//			List<Script> codeScripts = scriptSource.getAllCodeScripts();
//			if (!codeScripts.isEmpty()) {
//				// If updateDatabaseFromScratch == true, the schema code has
//				// already been cleared.
//				if (clearDbCodeEnabled && !updateDatabaseFromScratch) {
//					dbCodeClearer.clearSchemasCode();
//				}
//				executeCodeScripts(codeScripts);
//			}
//		}
	}
}
