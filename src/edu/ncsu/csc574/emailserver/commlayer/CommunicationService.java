/**
 * 
 */
package edu.ncsu.csc574.emailserver.commlayer;

import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

import com.ncsu.csc574.securitymanagement.ISecurityManager;
import com.ncsu.csc574.securitymanagement.SecurityManagerFactory;

import edu.ncsu.csc574.emailserver.exceptions.InitalizationException;
import edu.ncsu.csc574.module.IRequestProcessor;

/**
 * @author Harsha
 *
 */
public class CommunicationService implements ICommunicationService {

	private ServerSocket serverListeningSocket;
	private int port;
	private boolean isMutAuthRequired;
	private IRequestProcessor reqProcessor;
	
	/* Start service 
	 * 1. Start server socket
	 * 
	 * (non-Javadoc)
	 * @see edu.ncsu.csc574.emailserver.commlayer.ICommunicationService#startService(int, java.lang.String, boolean)
	 */
	public CommunicationService(int port, boolean isMutAuthRequired) throws InitalizationException {
		this.port = port;
		this.isMutAuthRequired = isMutAuthRequired;
	}

	@Override
	public int startService() throws Exception {
		
		ISecurityManager securityManager = SecurityManagerFactory.getInstance();
		
		/*Initialize SSLContext*/
		SSLContext sslctx = SSLContext.getInstance("SSLv3");
		sslctx.init(securityManager.getkeyManagerList(), securityManager.getTrustStoreManagerList(),
				null);

		SSLServerSocketFactory socketFactory = sslctx.getServerSocketFactory();
		SSLServerSocket socket = (SSLServerSocket) socketFactory.createServerSocket(port);

		socket.setNeedClientAuth(isMutAuthRequired);
		System.out.println("Waiting for clients...");
		
		while (true) {
			Socket connectSocket = socket.accept();
			//launch new client handler thread
			new Thread(new ClientHandlerThread(connectSocket)).start();
		}
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc574.emailserver.commlayer.ICommunicationService#isRunning()
	 */
	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc574.emailserver.commlayer.ICommunicationService#stopService()
	 */
	@Override
	public boolean stopService() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc574.emailserver.commlayer.ICommunicationService#registerModule(edu.ncsu.csc574.emailserver.workflowmanager.IRequestProcessor)
	 */
	@Override
	public void registerModule(IRequestProcessor requestProcessor) {
		this.reqProcessor = requestProcessor;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc574.emailserver.commlayer.ICommunicationService#getRequestProcessor()
	 */
	@Override
	public IRequestProcessor getRequestProcessor() {
		return reqProcessor;
	}

}


class ClientHandlerThread implements Runnable {
	private Socket connectedSocket;

	public ClientHandlerThread(Socket socket) {
		connectedSocket = socket;
	}

	@Override
	public void run() {
		IClientHandler clientHandler = new ClientHandler();
		clientHandler.handle(connectedSocket);
	}
}