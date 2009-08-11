package org.jtester.dbfit;

import java.io.File;
import java.io.InputStream;

import org.jtester.utility.ResourceUtil;

import com.neuri.trinidad.FolderTestResultRepository;
import com.neuri.trinidad.InMemoryTestImpl;
import com.neuri.trinidad.Test;
import com.neuri.trinidad.TestEngine;
import com.neuri.trinidad.TestResult;
import com.neuri.trinidad.TestResultRepository;
import com.neuri.trinidad.fitnesserunner.FitLibraryTestEngine;

import fit.Counts;

public class JTesterRunner {
	private final TestEngine testRunner;
	private final TestResultRepository resultRepository;
	private final String outputPath;
	private String rootPath = null;

	public JTesterRunner() {
		this("dbfit-output");
	}

	public JTesterRunner(String outputPath) {
		this.testRunner = new FitLibraryTestEngine();
		this.resultRepository = new FolderTestResultRepository(outputPath);
		this.outputPath = outputPath;
		this.prepareFiles();
	}

	public void runTest(Class<?> claz, final String url) throws Exception {
		String name = url.replace(".wiki", "").replace('\\', '.').replace('/', '.');
		this.runTest(name, claz, url);
	}

	public void runTest(final String name, Class<?> claz, final String url) throws Exception {
		String wiki = getWikiContent(claz, url);
		JTesterPage page = new JTesterPage(wiki);
		String html = this.addCssFile(page.getHtml());
		Test test = new InMemoryTestImpl(name, html);
		TestResult tr = testRunner.runTest(test);
		resultRepository.recordTestResult(tr);
		this.isSuccess(tr, name, url);
	}

	private String getWikiContent(Class<?> claz, final String url) {
		InputStream is = ResourceUtil.findClassPathStream(claz, url);
		if (is == null) {
			throw new RuntimeException("can't find file " + url);
		}
		String wiki = ResourceUtil.convertStreamToString(is);
		if (wiki.contains(DatabaseFixture.class.getName())) {
			return wiki;
		} else {
			StringBuffer buffer = new StringBuffer();
			buffer.append(String.format("|!-%s-!|", DatabaseFixture.class.getName()));
			buffer.append("\n");
			buffer.append(wiki);
			return buffer.toString();
		}
	}

	private final static String ERR_MESSAGE = "Run Wiki Page[%s], url[%s], Result is right=%d; wrong=%d;exceptions=%d;";

	public void isSuccess(TestResult tr, String name, String url) {
		Counts count = tr.getCounts();
		int right = count.right;
		int wrong = count.wrong;
		int exception = count.exceptions;
		if (wrong + exception != 0) {
			throw new RuntimeException(String.format(ERR_MESSAGE, name, url, right, wrong, exception));
		}
	}

	private final static String CSS_FILE = "<link rel='stylesheet' media='all' type='text/css' href='file:///%s/fitnesse.css' />";

	private String addCssFile(String html) {
		StringBuffer buffer = new StringBuffer("<html>");
		buffer.append("\n");
		buffer.append(String.format(CSS_FILE, this.rootPath));
		buffer.append("\n");
		buffer.append(html);
		buffer.append("\n");
		buffer.append("</html>");
		return buffer.toString();
	}

	private void prepareFiles() {
		File folder = new File(outputPath + File.separatorChar + "files");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		this.rootPath = folder.getAbsolutePath();
		File css = new File(outputPath + File.separatorChar + "files/fitnesse.css");
		if (!css.exists()) {
			ResourceUtil.copyFile("files/css/fitnesse_base.css", css);
		}
	}

	private static JTesterRunner defaultRunner = new JTesterRunner();

	public static void run(Class<?> claz, String url) {
		try {
			defaultRunner.runTest(claz, url);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
