package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import model.Automobile;

public class SelectCarOption {

	// private attributes:
	private ObjectInputStream reader;
	private ObjectOutputStream writer;
	ArrayList<String> modelList = null;
	Automobile auto = null;
	
	// public methods
	// constructor
	public SelectCarOption(ObjectInputStream reader, ObjectOutputStream writer)
	{
		this.reader = reader;
		this.writer = writer;
	}
	
	// promptAvailableModels: prompts the user for available models
	public void promptAvailableModels()
	{
		readAutoList();
		// print the list
		System.out.println("The available models:******************************");
		for (String model: modelList)	
			System.out.println(model);
		System.out.println("******************************\n");
			
	}
	
	/*
	 * readAutoList: read a list of Auto object from the client
	 */
	public void readAutoList()
	{
		try {
			modelList = (ArrayList<String>) reader.readObject(); // r 
		} catch (ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Error: unable to read model list from the server");
			e.printStackTrace();
			return;
		}
	}
	/*
	 * readAuto: read an Auto object from the client
	 */
	public void readAuto()
	{
		try {
			auto = (Automobile) reader.readObject(); // r
		} catch (ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Error: unable to read auto from the server");
			e.printStackTrace();
			return;
		}
	}
	/*
	 * selectAuto: Allows the user to select a model
	 */
	public void selectAuto()
	{
		do {
			// select the auto
			System.out.println("please choose a model:");
			Scanner stdIn = new Scanner(System.in);
			String model = stdIn.nextLine();
			
			// send choice to the server
			sendModelChoice(model);
			// readAuto from the server
			readAuto();
			
		}while(auto == null); // check if the client choose a model 
	}
	/*
	 *  sendModelChoice: send the model choice to the server
	 *  input: string modelChoice
	 */
	public void sendModelChoice(String modelChoice)
	{
		// send the properties object
		try {
			writer.writeObject(modelChoice);
		} catch (IOException e) {
			System.out.println("Error! unable to write model choice to the server");
			e.printStackTrace();
		}
	}
	
	/*
	 *  Allows the user to enter its respective options.
	 */
	public void enterOptions()
	{
		System.out.println("\n****************your model choice:*******************\n");
		System.out.println(auto);
		int optionSetSize = auto.getOptionSetSize();
		Scanner stdIn = new Scanner(System.in);
		
		// enter choices
		for (int index = 0; index < optionSetSize; index++)
		{
			String optionSetName = auto.getOptionSetName(index);
			System.out.println("please enter your choice for "+ optionSetName);
			String optionName = stdIn.nextLine();
			auto.setOptionChoice(optionSetName, optionName);
		}
	}
	
	/*
	 * displayChoices: display what the client choices and the total price
	 */
	public void displayChoices()
	{
		int optionSetSize = auto.getOptionSetSize();
		
		// print choices
		System.out.println("*********************************");
		System.out.println("Configuration completed:");
		for (int index = 0; index < optionSetSize; index++)
		{
			String optionSetName = auto.getOptionSetName(index);
			String choice = auto.getOptionChoice(optionSetName);
			System.out.println(optionSetName +" : " + choice);
		}
		System.out.println("*************total price: " + auto.getTotalPrice());
	}
}
