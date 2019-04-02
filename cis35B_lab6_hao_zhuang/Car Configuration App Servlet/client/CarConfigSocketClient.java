package client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import adapter.BuildAuto;
import adapter.CreateAuto;

public class CarConfigSocketClient extends DefaultSocketClient{

	// constructor:
	public CarConfigSocketClient(int port, String host) {
		super(port, host);
	}
	
	// override handleSession: load and send properties object to server
	public void handleSession()
	{
		// prompt the client for his choices
		System.out.println("you have two options");
		System.out.println("0 - send a properties file to the server to create an automobile");
		System.out.println("1 - select an Automobile and configure a car");
		System.out.println("please type 0 or 1 for your option");
		
		Scanner stdIn = new Scanner(System.in);
		int clientChoice = stdIn.nextInt();
		
		if (clientChoice == 0) // send a properties file
		{
			// notify the server for upholding the properties file
			sendOutput("upload properties");
			// wait for the response for the server
			System.out.println(readInputStr());
			
			CarModelOptionsIO builder = new CarModelOptionsIO(reader, writer);
			// send the properties object
			builder.uploadPropertiesFile();
			// wait for the response for the server
			builder.readConfrimation();
			//build an auto object from txt file
			builder.createAutoFromTxt();
		}
		else if (clientChoice == 1) // configure a car
		{
			// notify the server for configuring a car
			sendOutput("configure a car");
			SelectCarOption configurator = new SelectCarOption(reader,writer);
			// prompts the user for available models
			configurator.promptAvailableModels();
			// the client makes his choice
			configurator.selectAuto();
			// the client enter options
			configurator.enterOptions();
			// display the choices
			configurator.displayChoices();
		}
	}
	
	/*
	 * readProperties: read and return the properties object from the client
	 */
	public String readInputStr()
	{
		try {
			return (String) reader.readObject(); // read str
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Error: unable to read input from the server");
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

}
