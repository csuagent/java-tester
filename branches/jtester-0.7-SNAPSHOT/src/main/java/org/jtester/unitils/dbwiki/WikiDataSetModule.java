package org.jtester.unitils.dbwiki;

import static org.unitils.util.AnnotationUtils.getMethodOrClassLevelAnnotation;

import java.lang.reflect.Method;

import org.unitils.dbunit.DbUnitModule;
import org.unitils.dbunit.datasetfactory.DataSetFactory;
import org.unitils.dbunit.util.MultiSchemaDataSet;

public class WikiDataSetModule extends DbUnitModule {
	@Override
	public MultiSchemaDataSet getDataSet(Method testMethod, Object testObject) {
		Class<?> testClass = testObject.getClass();
		WikiDataSet wikiDataSetAnnotation = getMethodOrClassLevelAnnotation(WikiDataSet.class, testMethod, testClass);
		if (wikiDataSetAnnotation == null) {
			// No @DataSet annotation found
			return null;
		}

		// Create configured factory for data sets
		DataSetFactory dataSetFactory = getDataSetFactory(WikiDataSet.class, testMethod, testClass);

		// Get the dataset file name
		String[] dataSetFileNames = wikiDataSetAnnotation.value();
		if (dataSetFileNames.length == 0) {
			dataSetFileNames = new String[] { getDefaultDataSetFileName(testClass, dataSetFactory
					.getDataSetFileExtension()) };
		}

		return getDataSet(testClass, dataSetFileNames, dataSetFactory);
	}
}
