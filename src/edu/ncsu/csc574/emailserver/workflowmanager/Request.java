
package edu.ncsu.csc574.emailserver.workflowmanager;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Request implements IRequest{
	
	private static final String HEADER_SEPARATOR = ":";
	private String command;
	private HashMap<String, String> headerLines;
	private byte[] body;
	
	public Request(String command, HashMap<String, String> headerLines, byte[] body) {
		this.command = command;
		this.headerLines = headerLines;
		this.body = body;
	}

	@Override
	public String getCommand() {
		return command;
	}

	@Override
	public HashMap<String, String> getHeaders() {
		return headerLines;
	}

	@Override
	public byte[] getBody() {
		return body;
	}

	public static IRequest parse(BufferedReader br) throws Exception {
		//dummy at this point of time
		int nrHeaderLines = Integer.parseInt(br.readLine());
		String command = br.readLine();
		HashMap<String, String> headerLines = new HashMap<String, String>();
		
		for(int i = 0; i < nrHeaderLines; i++) {
			String headerLine = br.readLine();
			String[] linePairs = parseHeaderLine(headerLine);
			if(linePairs.length != 2) {
				throw new Exception("Invalid request: header line containing: " + linePairs);
			}	
			headerLines.put(linePairs[0], linePairs[1]);
		}
		//get attachment data
		byte[] body = null;
		
		return new Request(command, headerLines, body);
	}

	@Override
	public String getRawRequest() {
		StringBuffer br = new StringBuffer();
		br.append(headerLines.size() + 2);	//nrHeaders + cmd + headers size
		br.append(command);
	
		Iterator<Entry<String, String>> itr = headerLines.entrySet().iterator(); 
		while(itr.hasNext()) {
			Entry<String,String> headerLine = itr.next();
			br.append(createHeaderLine(headerLine.getKey(), headerLine.getValue()));
		}
		
		br.append(new String(body));
		return br.toString();
	}

	private String createHeaderLine(String field, String value) {
		StringBuffer sb = new StringBuffer();
		sb.append(field);
		sb.append(HEADER_SEPARATOR);
		sb.append(value);
		
		return sb.toString();
	}
	
	
	private static String[] parseHeaderLine(String headerLine) {
		return headerLine.split(HEADER_SEPARATOR);
	}
}
