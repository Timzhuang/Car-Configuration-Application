package client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;
import java.util.Scanner;

import adapter.BuildAuto;
import adapter.CreateAuto;

public class CarModelOptionsIO {

	private ObjectInputStream reader;
	private ObjectOutputStream writer;
	
	public CarModelOptionsIO(ObjectInputStream reader, ObjectOutputStream writer)
	{
		this.reader = reader;
		this.writer = writer;
	}
	
	/*
	 * loadPropertiesFile: load the Properties file to create a properties object 
	 * output: properties object
	 */
	public void uploadPropertiesFile()
	{
		// prompt for the path
		Scanner stdIn = new Scanner(System.in);
		System.out.println("please enter the path for the file: either FordZTW-properties.txt or 12.txt");
		String fileName = stdIn.nextLine();
		
		// read the file
		FileInputStream in;
		try {
			in = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		// create the properties object
		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}	
		// send the properties object
		try {
			writer.writeObject(props);
		} catch (IOException e) {
			System.out.println("Error! unable to write to the server");
			e.printStackTrace();
		}
	}
	
	/*
	 * readProperties: read and return the properties object from the client
	 */
	public void readConfrimation()
	{
		try {
			System.out.println((String) reader.readObject()); // read str
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Error: unable to read input from the client");
			e.printStackTrace();
			return;
		}
	}
	
	/*
	 * createAutoFromTxt: build an auto object from txt file
	 */
	public void createAutoFromTxt()
	{
		// build Automobile from a txt file
		System.out.println("creating auto from txt file");
		CreateAuto creator = new BuildAuto();
		creator.buildAuto("FordZTW.txt");
	}
	
	
}
