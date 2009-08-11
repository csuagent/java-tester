package org.jtester.dbfit;

import java.io.IOException;

import org.jtester.testng.JTester;
import org.testng.annotations.BeforeClass;

//import org.jtester.junit4.JTester;
//import org.junit.Before;

import com.neuri.trinidad.JUnitHelper;
import com.neuri.trinidad.TestEngine;
import com.neuri.trinidad.TestRepository;
import com.neuri.trinidad.fitnesserunner.FitLibraryTestEngine;
import com.neuri.trinidad.fitnesserunner.FitNesseRepository;

public class DbFixtureTest extends JTester {
	protected JUnitHelper helper;

	private static String outputDir = null;

	private static TestRepository repository = null;

	private static TestEngine testEngine = null;
	static {
		try {
			outputDir = "d:/fitnesse";
			repository = new FitNesseRepository(".");
			testEngine = new FitLibraryTestEngine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void initHelper() throws Exception {
		helper = new JUnitHelper(repository, testEngine, outputDir);
	}

	public void run(String wiki) {
		try {
			helper.assertTestPasses(wiki);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
