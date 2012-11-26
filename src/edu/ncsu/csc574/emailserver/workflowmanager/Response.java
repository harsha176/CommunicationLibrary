package edu.ncsu.csc574.emailserver.workflowmanager;

import java.util.HashMap;

public class Response implements IResponse {

	private int statusCode;
	private String message;
	private HashMap<String, String> headers;
	private byte[] body;

	@Override
	public int getStatusCode() {
		// TODO Auto-generated method stub
		return statusCode;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

	@Override
	public HashMap<String, String> getHeaders() {
		// TODO Auto-generated method stub
		return headers;
	}

	@Override
	public byte[] getBody() {
		// TODO Auto-generated method stub
		return body;
	}

	@Override
	public void parse(String raw_response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRawResponse() {
		// TODO Auto-generated method stub
		return null;
	}

}
