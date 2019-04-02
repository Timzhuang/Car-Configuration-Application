package server;

import java.io.IOException;
import java.net.*;

public class Server {

	private ServerSocket serverSocket = null;
	
	// public methods:
	// constructor:
	public Server()
	{
		try {
			serverSocket = new ServerSocket(4444);
		} catch (IOException e) {
			System.out.println("Error: unable to set up a server on port: 4444");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/*
	 * runServer: keep listening to the requests from clients and handle each of them by a single thread
	 */
	public void runServer()
	{
		while(true) // listen to the request
		{
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.out.println("Error! unable to accept an request of a client");
				e.printStackTrace();
			}
			// handle the request
			CarConfigSocketServer clientHandler = new CarConfigSocketServer(clientSocket);
			clientHandler.start();
		}
	}
	
}
