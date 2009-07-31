package org.jtester.jdbcproxy.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.jtester.jdbcproxy.util.GenerateMergerFile;

public class JdbcProxyDemo {
	public static void main(String args[]) throws Exception {
		System.out.println("record");
		record();
		System.out.println("stub call");
		callstub();
	}

	public static void record() throws Exception {
		ProxyUrl proxy = ProxyUrl.generating();
		execute(proxy);

		GenerateMergerFile mergerfile = new GenerateMergerFile("output/mergerfile.xml");
		mergerfile.generateFile();
	}

	public static void callstub() throws Exception {
		ProxyUrl proxy = ProxyUrl.filestub();
		execute(proxy);
	}

	private static void execute(ProxyUrl proxy) throws Exception {
		Class.forName(proxy.driver);
		Connection connection = DriverManager.getConnection(proxy.url, "root", "password");
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("select greeting from greetings");
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
		rs.close();
		stmt.close();

		stmt = connection.createStatement();
		stmt.execute("insert into greetings(greeting)values(\"hello\");");

		stmt = connection.createStatement();
		rs = stmt.executeQuery("select * from greetings");
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
		rs.close();
		stmt.close();

		stmt = connection.createStatement();
		stmt.execute("delete from greetings where greeting =\"hello\"");

		connection.close();
	}

	public static class ProxyUrl {
		public String driver;
		public String url;

		public static ProxyUrl factual() {
			return new ProxyUrl() {
				{
					this.driver = "com.mysql.jdbc.Driver";
					this.url = "jdbc:mysql://localhost/greetings?characterEncoding=UTF8";
				}
			};
		}

		public static ProxyUrl tracer() {
			return new ProxyUrl() {
				{
					this.driver = "nl.griffelservices.proxy.jdbc.oracle.StubTracerDriver";
					this.url = "jdbc:tracer:log.txt:com.mysql.jdbc.Driver:jdbc:mysql://localhost/greetings?characterEncoding=UTF8";
				}
			};
		}

		public static ProxyUrl generating() {
			return new ProxyUrl() {
				{
					this.driver = "nl.griffelservices.proxy.jdbc.oracle.StubTracerDriver";
					this.url = "jdbc:stubtracer:output:com.mysql.jdbc.Driver:jdbc:mysql://localhost/greetings?characterEncoding=UTF8";
				}
			};
		}

		public static ProxyUrl httpstub() {
			return new ProxyUrl() {
				{
					this.driver = "nl.griffelservices.proxy.jdbc.oracle.StubTracerDriver";
					this.url = "jdbc:stub:localhost:80:1000";
				}
			};
		}

		public static ProxyUrl filestub() {
			return new ProxyUrl() {
				{
					this.driver = "org.jtester.jdbcproxy.driver.FileStubTracerDriver";
					this.url = "jdbc:stub:output/mergerfile.xml";
				}
			};
		}
	}
}
