package server;
import java.net.*;
import java.io.*;


public class DefaultSocketClient extends Thread implements SocketClientInterface,SocketClientConstants {

	// protected attributes:
	protected ObjectInputStream reader;
	protected ObjectOutputStream writer;
	protected Socket socket = null;
	protected String host;
	protected int port;
	
	
	// public methods:
	public DefaultSocketClient(int port, String host)
	{
		this.host = host;
		this.port = port;
		
		try {
			this.socket = new Socket(host, port);
		}
		catch (IOException socketError) {
			 if (DEBUG) System.err.println("Unable to connect to " + host);
		}
	}
	
	public DefaultSocketClient(Socket socket)
	{
		this.socket = socket;
		port = socket.getPort();
		host = socket.getInetAddress().getHostName();
	}
	
	// setters:
	public void setHost(String host)
	{
		this.host = host;
	}
	
	public void setPort(int port)
	{
		this.port = port;
	}
	
	/*
	 * handleInput: print the input from the server
	 * input: String input
	 */
	public void handleInput(String input)
	{
		System.out.println(input);
	}
	
	
	/*
	 * sendOutput: send output to the server 
	 * input: String output
	 */
	/*
	public void sendOutput(String output)
	{
		try {
			writer.write(output, 0, output.length());
		} catch (IOException e) {
			if (DEBUG) System.out.println ("Error writing to " + host);
		}
	}
	*/
	
	/*
	 * Override: run
	 */
	public void run()
	{
		if (openConnection())
		{
			handleSession();
			closeSession();	
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see server.SocketClientInterface#openConnection()
	 */
	@Override
	public boolean openConnection() {
		try {
			reader = new ObjectInputStream( socket.getInputStream() );
			writer = new ObjectOutputStream( socket.getOutputStream() );
		}
		catch (Exception e){     
			if (DEBUG) System.err.println("Unable to obtain stream to/from " + host);     
			return false;  
		}
		
		
		return true;
	}

	@Override
	public void handleSession() {
		
		
	}

	@Override
	public void closeSession() {
		try {
			writer = null;
			reader = null;
			socket.close();
		}
		catch (IOException e){        
			if (DEBUG) System.err.println("Error closing socket to " + host);     
			}   
		
		
	}
	
	

}
