package edu.ncsu.csc574.emailserver.commlayer;

import edu.ncsu.csc574.module.IUserContext;

public class UserContext implements IUserContext {
	private String username;
	private String domainName;
	private String ipAddress;
	private boolean isLoggedIn;
	
	public UserContext(String username) {
		this.username = username;
		this.domainName = null;  //set this to default domain
		this.isLoggedIn = false;
		this.ipAddress = null;
	}
	
	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getDomainName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLoggedIn() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	public String getIPAddress() {
		return ipAddress;
	}
	
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
}
