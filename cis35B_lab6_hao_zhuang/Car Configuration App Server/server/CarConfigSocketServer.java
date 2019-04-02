package server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

import model.Automobile;

public class CarConfigSocketServer extends DefaultSocketClient {
	
	// constructor:
	public CarConfigSocketServer(Socket socket)
	{
		super(socket);
	}
	
	/*
	 * @override handleSession: 
	 */
	public void handleSession() 
	{
		String clientChoice = readClientChoice(); // get the choice
		BuildCarModelOptions autoUpdator = new BuildCarModelOptions();
		System.out.println(clientChoice);
		
		// different operations based on choices
		if (clientChoice.equalsIgnoreCase("upload properties"))
		{
			// prompt the client to upload the properties file
			sendOutput("please uphold the properties object");
			
			//receive the properties file and build an auto
			Properties props = readProperties();
			if (props != null)
				autoUpdator.parseProperties(props);
			else
				return;	
			sendOutput("the Car Model is created successfully");
		}
		else if (clientChoice.equalsIgnoreCase("configure a car"))
		{	
			// send the client the auto list
			sendModelList(autoUpdator.getModelList());
			// read the choice of model
			String modelChoice = readModelChoice();
			// send the chosen auto
			sendAuto(autoUpdator.getAuto(modelChoice));
		}
		else if (clientChoice.equalsIgnoreCase("send model list"))
		{
			// send the client the auto list
			sendModelList(autoUpdator.getModelList());
		}
		else if (clientChoice.equalsIgnoreCase("send auto"))
		{
			// read the choice of model
			String modelChoice = readModelChoice();
			// send the chosen auto
			sendAuto(autoUpdator.getAuto(modelChoice));
		}
		else if (clientChoice.equals(null))
		{
			System.out.println("client does not provide a choice");
		}	
	}
	
	/*
	 * readClientChoice: read and return the choice of client: uphold properties or configure a car
	 */
	public String readClientChoice()
	{
		try {
			return (String) reader.readObject(); // read choice
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Error: unable to read input from the client");
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * readClientChoice: read and return the choice of model
	 */
	public String readModelChoice()
	{
		try {
			return (String) reader.readObject(); // read choice
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Error: unable to read input from the client");
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * readProperties: read and return the properties object from the client
	 */
	public Properties readProperties()
	{
		try {
			return (Properties) reader.readObject(); // read choice
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Error: unable to read input from the client");
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * sendOutput: send output to the client
	 * input: String output
	 */
	public void sendOutput(String output)
	{
		try {
			writer.writeObject(output);
		} catch (IOException e) {
			System.out.println("Error! unable to send output to the client");
		}
	}
	
	/*
	 * sendOutput: send output to the client
	 * input: String output
	 */
	public void sendAutoList(ArrayList<Automobile> autoList)
	{
		try {
			writer.writeObject(autoList);
		} catch (IOException e) {
			System.out.println("Error! unable to send auto list to the client");
		}
	}
	
	/*
	 * sendModelList: send a list of models to the client
	 * input: ArrayList<String> modelList
	 */
	public void sendModelList(ArrayList<String> modelList)
	{
		try {
			writer.writeObject(modelList);
		} catch (IOException e) {
			System.out.println("Error! unable to send model list to the client");
		}
	}
	
	/*
	 * sendAuto: send a Auto object to the client
	 * input: Automobile object
	 */
	public void sendAuto(Automobile auto)
	{
		try {
			writer.writeObject(auto);
		} catch (IOException e) {
			System.out.println("Error! unable to send auto to the client");
		}
	}
	

}
