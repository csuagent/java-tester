package org.jtester.jdbcproxy.stub;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Retrieves data from a local file.
 * 
 * @author xufangbj@cn.ibm.com
 */
public class FileClient {
	/** the default encoding for the HTTP content */
	protected static final String DEFAULT_ENCODING = "ISO-8859-1";

	/** the hostname of the HTTP server for which this is the client */
	private String filename;

	/**
	 * Constructs a FileClient object.
	 * 
	 * @param filename
	 *            the merger file name
	 */
	public FileClient(String filename) {
		this.filename = filename;
	}

	/**
	 * Sends a string as the content of a HTTP Post request and returns the
	 * content of merger file.
	 * 
	 * @param request
	 *            the request that must be send to the merger file
	 * @return the response from the merger file
	 * @throws IOException
	 *             if an error occurs
	 */
	public String invoke(String request) throws IOException {
		/*
		 * read from file
		 */
		FileInputStream fis = new FileInputStream(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		StringBuffer sb = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line + "\r\n");
		}
		br.close();
		String response = new String(sb);
		br.close();
		fis.close();
		return response;
	}
}
