package edu.ncsu.csc574.emailserver.commlayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import edu.ncsu.csc574.emailserver.workflowmanager.Request;
import edu.ncsu.csc574.module.IRequest;
import edu.ncsu.csc574.module.IRequestProcessor;
import edu.ncsu.csc574.module.IResponse;
import edu.ncsu.csc574.module.IUserContext;

public class ClientHandler implements IClientHandler {

	/**
	 * 1. Read data from socket 2. Parse data and create IRequestObject 3.
	 * create usercontext object 4. Get requestProcessor from
	 * communicationService 5. pass IRequest object and usercontext object to
	 * requestProcessor 6. get Iresponse object from request processor and
	 * translate it to string and put it on wire.
	 */
	@Override
	public void handle(Socket socket) {
		try {
			// Read data from socket
			BufferedReader br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			
			IRequest request;
			ICommunicationService commService = CommunicationServiceFactory.getInstance();
			IRequestProcessor reqProcessor = commService.getRequestProcessor();
			IUserContext userctx = null;
			

			//process each command
			while ((request = Request.parse(br)).getCommand().equalsIgnoreCase("LOGOUT")) {
				
				//create usercontext object if user has loggedin
				if (request.getCommand() == "LOGIN") {
					userctx = new UserContext(request.getHeaders()
							.get("username"));
				}
				
				IResponse response = reqProcessor.process(userctx, request);
				pw.print(response.getRawResponse());
				
				//flush data
				pw.flush();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
