package org.jtester.unitils.dbwiki;

import java.lang.reflect.Method;

import org.unitils.dbunit.DbUnitModule;
import org.unitils.dbunit.datasetfactory.DataSetFactory;
import org.unitils.dbunit.util.MultiSchemaDataSet;

public class WikiDataSetModule extends DbUnitModule {

	@Override
	protected MultiSchemaDataSet getDataSet(Class<?> testClass, String[] dataSetFileNames, DataSetFactory dataSetFactory) {
		// TODO Auto-generated method stub
		return super.getDataSet(testClass, dataSetFileNames, dataSetFactory);
	}

	@Override
	public MultiSchemaDataSet getDataSet(Method testMethod, Object testObject) {
		// TODO Auto-generated method stub
		return super.getDataSet(testMethod, testObject);
	}

}
