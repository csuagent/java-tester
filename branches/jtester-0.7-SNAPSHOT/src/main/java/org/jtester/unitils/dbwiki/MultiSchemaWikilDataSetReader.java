package org.jtester.unitils.dbwiki;

import static org.unitils.thirdparty.org.apache.commons.io.IOUtils.closeQuietly;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.unitils.core.UnitilsException;
import org.unitils.dbunit.util.MultiSchemaDataSet;
import org.unitils.dbunit.util.MultiSchemaXmlDataSetReader;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class MultiSchemaWikilDataSetReader extends MultiSchemaXmlDataSetReader {
	private String defaultSchemaName;

	public MultiSchemaWikilDataSetReader(String defaultSchemaName) {
		super(defaultSchemaName);
		this.defaultSchemaName = defaultSchemaName;
	}

	/**
	 * Parses the datasets from the given files. Each schema is given its own
	 * dataset and each row is given its own table.
	 * 
	 * @param dataSetFiles
	 *            The dataset files, not null
	 * @return The read data set, not null
	 */
	public MultiSchemaDataSet readDataSetWiki(File... dataSetFiles) {
		try {
			DataSetContentHandler dataSetContentHandler = new DataSetContentHandler(defaultSchemaName);
			for (File wikiFile : dataSetFiles) {

			}

			XMLReader xmlReader = createXMLReader();
			xmlReader.setContentHandler(dataSetContentHandler);
			xmlReader.setErrorHandler(dataSetContentHandler);

			for (File dataSetFile : dataSetFiles) {
				InputStream dataSetInputStream = null;
				try {
					dataSetInputStream = new FileInputStream(dataSetFile);
					xmlReader.parse(new InputSource(dataSetInputStream));
				} finally {
					closeQuietly(dataSetInputStream);
				}
			}
			return dataSetContentHandler.getMultiSchemaDataSet();

		} catch (Exception e) {
			throw new UnitilsException("Unable to parse data set xml.", e);
		}

	}
}
