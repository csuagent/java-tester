/*
 * Created on Sep 16, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.jtester.jdbcproxy.util;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import nl.griffelservices.proxy.stub.ProxyIdentity;
import nl.griffelservices.proxy.stub.RequestResponse;
import nl.griffelservices.proxy.stub.Response;
import nl.griffelservices.proxy.stub.ResponseEncoder;
import nl.griffelservices.proxy.stub.StubTracerHandler;

import org.jtester.jdbcproxy.stub.FileStubTracerMerger;
import org.jtester.jdbcproxy.stub.RequestTreeEncoder;
import org.jtester.jdbcproxy.stub.ResponseTreeEncoder;

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
			TreeMap<ProxyIdentity, RequestResponse> map = StubTracerHandler.map;
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
			// throw new RuntimeException(e);
		}
	}

	public void generateTreeFile() {
		try {
			File mergerFile = new File(filepath);
			if (mergerFile.exists()) {
				mergerFile.delete();
			}
			mergerFile.createNewFile();
			StringBuffer buffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			buffer.append("<root>");
			for (Iterator<Map.Entry<ProxyIdentity, RequestResponse>> it = StubTracerHandler.map.entrySet().iterator(); it
					.hasNext();) {
				Map.Entry<ProxyIdentity, RequestResponse> entity = it.next();
				ProxyIdentity id_status = entity.getKey();
				RequestResponse rr = entity.getValue();
				String context = trace(rr);
				buffer.append(String.format("<request-response id=\"%s\" status=\"%s\">", id_status.getProxyId(),
						id_status.getProxyStatus()));
				buffer.append(context);
				buffer.append("</request-response>");
			}
			buffer.append("</root>");
			FileWriter fw = new FileWriter(mergerFile);
			fw.write(buffer.toString());
			fw.close();
		} catch (Throwable e) {
			// throw new RuntimeException(e);
		}
	}

	/** the request encoder */
	private static final RequestTreeEncoder requestEncoder = new RequestTreeEncoder();
	/** the response encoder */
	private static final ResponseTreeEncoder responseEncoder = new ResponseTreeEncoder();

	public String trace(RequestResponse rr) throws Exception {
		StringBuffer buffer = new StringBuffer();
		String request_context = requestEncoder.encode(rr.getRequest());
		buffer.append(request_context);
		String response_context = responseEncoder.encode(rr.getResponse());
		buffer.append(response_context.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", ""));
		return buffer.toString();
	}
}
