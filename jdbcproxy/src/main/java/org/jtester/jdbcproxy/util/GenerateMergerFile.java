/*
 * Created on Sep 16, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.jtester.jdbcproxy.util;

import java.io.File;
import java.io.FileWriter;
import java.util.TreeMap;

import org.jtester.jdbcproxy.stub.FileStubTracerMerger;

import nl.griffelservices.proxy.stub.Response;
import nl.griffelservices.proxy.stub.ResponseEncoder;
import nl.griffelservices.proxy.stub.StubTracerHandler;

/**
 * @author xufangbj@cn.ibm.com
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class GenerateMergerFile {

	String filepath = "";

	public GenerateMergerFile(String filepath) {
		this.filepath = filepath;
	}

	public void generateFile() {
		try {
			// generate xml when doing record
			ResponseEncoder responseEncoder = new ResponseEncoder();
			TreeMap map = StubTracerHandler.map;
			Response response = FileStubTracerMerger.getResponse(map, "0", "0");
			String responseContent = responseEncoder.encode(response);

			// output to a file
			File mergerFile = new File(filepath);
			if (mergerFile.exists()) {
				mergerFile.delete();
				mergerFile.createNewFile();
			} else {
				mergerFile.createNewFile();
			}
			FileWriter fw = new FileWriter(mergerFile);
			fw.write(responseContent);
			fw.close();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

}
