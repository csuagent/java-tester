package org.jtester.dbfit;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.neuri.trinidad.FolderTestResultRepository;
import com.neuri.trinidad.InMemoryTestImpl;
import com.neuri.trinidad.Test;
import com.neuri.trinidad.TestEngine;
import com.neuri.trinidad.TestResult;
import com.neuri.trinidad.TestResultRepository;
import com.neuri.trinidad.fitnesserunner.FitLibraryTestEngine;

import fit.Counts;

public class JTesterRunner {
	private TestEngine testRunner = null;
	private TestResultRepository resultRepository;

	public JTesterRunner(String outputPath) throws IOException {
		this(new FolderTestResultRepository(outputPath));
	}

	public JTesterRunner(TestResultRepository resultRepository) throws IOException {
		this.testRunner = new FitLibraryTestEngine();
		this.resultRepository = resultRepository;
		prepareResultRepository();
	}

	public Counts runTest(String name, String url) throws Exception {
		InputStream is = ClassLoader.getSystemResourceAsStream(url);
		String wiki = convertStreamToString(is);
		JTesterPage html = new JTesterPage(wiki);
		Test test = new InMemoryTestImpl(name, html.getHtml());
		TestResult tr = testRunner.runTest(test);
		resultRepository.recordTestResult(tr);
		return tr.getCounts();
	}

	public void prepareResultRepository() throws IOException {
		File filesFolder = new File(new File(new File("."), "FitNesseRoot"), "files");
		File cssDir = new File(filesFolder, "css");
		resultRepository.addFile(new File(cssDir, "fitnesse_base.css"), "fitnesse.css");
		File javascriptDir = new File(filesFolder, "javascript");
		resultRepository.addFile(new File(javascriptDir, "fitnesse.js"), "fitnesse.js");
		File imagesDir = new File(filesFolder, "images");
		resultRepository.addFile(new File(imagesDir, "collapsableOpen.gif"), "images/collapsableOpen.gif");
		resultRepository.addFile(new File(imagesDir, "collapsableClosed.gif"), "images/collapsableClosed.gif");
	}

	/*
	 * To convert the InputStream to String we use the BufferedReader.readLine()
	 * method. We iterate until the BufferedReader return null which means
	 * there's no more data to read. Each line will appended to a StringBuilder
	 * and returned as String.
	 */
	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder buffer = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				buffer.append(line + "\n");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return buffer.toString();
	}
}
