package edu.ncsu.csc574.emailserver.commlayer;

import java.net.Socket;

/**
 * Handles each client in separate thread
 * @author Harsha
 *
 */
public interface IClientHandler {
	/**
	 * Handles each connection 
	 * Creates usercontext per connection.
	 * Read data from socket parse and create IRequest object
	 * call RequestProcessor.process(usercontext IRequest object)
	 * convert IResponse to raw response object and send it over network.
	 * @param socket
	 */
	public void handle(Socket socket);
}
