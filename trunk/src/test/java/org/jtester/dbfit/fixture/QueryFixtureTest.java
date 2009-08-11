package org.jtester.dbfit.fixture;

import org.jtester.dbfit.DbFixtureTest;
import org.jtester.dbfit.JTesterPage;
import org.jtester.utility.SerializeUtil;
import org.testng.annotations.Test;

import com.neuri.trinidad.InMemoryTestImpl;
import com.neuri.trinidad.TestEngine;
import com.neuri.trinidad.TestRepository;
import com.neuri.trinidad.TestResult;
import com.neuri.trinidad.TestRunner;
import com.neuri.trinidad.fitnesserunner.FitLibraryTestEngine;
import com.neuri.trinidad.fitnesserunner.FitNesseRepository;

import fit.Counts;
import fitnesse.wiki.PageData;

public class QueryFixtureTest extends DbFixtureTest {
	@Test
	public void simpleQuery() {
		run("AcceptanceTests.JavaTests.MySqlTests.ConnectUsingFile.SimpleSelect");
	}

	@Test
	public void simpleQuery2() {
		run("AcceptanceTests.JavaTests.SimpleQuery");
	}

	@Test
	public void testRepository() throws Exception {
		TestRepository repository = new FitNesseRepository(".");
		com.neuri.trinidad.Test test = repository.getTest("AcceptanceTests.JavaTests.SimpleQuery");
		want.object(test).propertyEq("name", "AcceptanceTests.JavaTests.SimpleQuery").propertyMatch("content",
				the.object().notNull());
		System.out.println(test.getContent());
	}

	@Test
	public void pageData() throws Exception {
		PageData pd = SerializeUtil.fromXML(PageData.class, "classpath:org/jtester/dbfit/fixture/page data.xml");
		want.object(pd).notNull();
		String html = pd.getHtml();
		System.out.println(html);

	}

	@Test
	public void pageData_new() throws Exception {
		StringBuffer context = new StringBuffer();
		context.append("|!-org.jtester.dbfit.DatabaseFixture-!|\n");
		context.append("|connect|\n");
		context.append("\n");
		context.append("|Query|select 'test' as x|\n");
		context.append("|x|\n");
		context.append("|test|\n");
		context.append("\n");
		context.append("\n");
		context.append("|Query|select * from tdd_user|\n");
		context.append("|first_name|\n");
		context.append("|null|\n");
		context.append("|null|\n");
		context.append("|null|\n");
		context.append("|null|\n");
		JTesterPage pd = new JTesterPage(context.toString());

		want.object(pd).notNull();
		String html = pd.getHtml();
		want.string(html).contains("table").contains("td").contains("tr");
		System.out.println(html);
		com.neuri.trinidad.Test test = new InMemoryTestImpl("jtester", html);
		TestEngine engine = new FitLibraryTestEngine();
		TestResult result = engine.runTest(test);
		System.out.println(result.getCounts().right);
	}

	@Test
	public void myPageData() throws Exception {
		PageData pd = SerializeUtil.fromXML(PageData.class, "classpath:org/jtester/dbfit/fixture/page data.xml");
		JTesterPage my = new JTesterPage(pd);
		String html = my.getHtml();
		want.string(html).contains("table").contains("td").contains("tr");
		System.out.println(html);
	}

	public static void main(String[] args) throws Exception {
		TestRunner tdd = new TestRunner(new FitNesseRepository("."), new FitLibraryTestEngine(), "./test-output");

		// Counts cs = tdd.runSuite("AcceptanceTests.JavaTests.SimpleQuery");
		Counts cs = tdd.runTest("AcceptanceTests.JavaTests.SimpleQuery");
		System.err.println("Total right=" + cs.right + "; wrong=" + cs.wrong + " exceptions=" + cs.exceptions);
	}
}
