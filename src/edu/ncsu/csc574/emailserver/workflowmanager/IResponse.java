package edu.ncsu.csc574.emailserver.workflowmanager;

import java.util.HashMap;

public interface IResponse {
	public int getStatusCode();
	public String getMessage();
	public HashMap<String, String> getHeaders();
	public byte[] getBody();
	
	public void parse(String raw_response);
	public String getRawResponse();
}
