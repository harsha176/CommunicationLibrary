package edu.ncsu.csc574.emailserver.commlayer;

import edu.ncsu.csc574.emailserver.exceptions.InitalizationException;
import edu.ncsu.csc574.emailserver.workflowmanager.IRequestProcessor;


//TODO: handle client-server server-server comm separately

/**
 * This interface starts/initializes email service
 * @author Harsha
 *
 */
public interface ICommunicationService {
	/**
	 * This method runs email server as a daemon and each module gets initialized.
	 * @param port      	email server listening port
	 * @param isMuthAuthRequired if mutual authentication is required or not
	 * @return 
	 * @throws Exception 
	 */
	public int startService() throws InitalizationException, Exception;
	
	/**
	 * This method checks if the service is running.
	 * @return
	 */
	public boolean isRunning();
	
	/**
	 * This methods stops email service
	 * @return
	 * @throws Exception
	 */
	public boolean stopService() throws Exception;
	
	
	/**
	 * Module needs to register requestProcessor.
	 * If no module registers requestProcessor a default processor will process requests.
	 * @param requestProcessor
	 */
	public void registerModule(IRequestProcessor requestProcessor);
	
	/**
	 * Returns requestProcessor of the registered module.
	 * @return
	 */
	public IRequestProcessor getRequestProcessor();
}
