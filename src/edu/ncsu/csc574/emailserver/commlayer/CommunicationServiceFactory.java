package edu.ncsu.csc574.emailserver.commlayer;

import edu.ncsu.csc574.emailserver.exceptions.InitalizationException;

public class CommunicationServiceFactory {
	
	private static ICommunicationService instance = null;
	public static ICommunicationService getCommServiceInstance(int port, boolean isMutAuthRequired) throws InitalizationException {
		//return default implementation
		if(instance == null) {
			return new CommunicationService(port, isMutAuthRequired);
		}
		
		return instance;
	}
	
	public static ICommunicationService getInstance() throws Exception{
		if(instance == null) {
			throw new Exception("Communication service not initialized");
		} 
		
		return instance;
		
	}
	
}
