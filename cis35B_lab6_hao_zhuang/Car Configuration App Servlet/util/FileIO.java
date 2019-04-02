package util;

import model.*;
import java.util.Properties;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

import exception.*;


public class FileIO {
	
	/*
	 * buildAutoObject: read data from a file and build an Automotive Object
	 * input: fileName
	 * output: Automotive object
	 */
	public Automobile buildAutoObject(String fileName)
	{
		// initialize the error codes 
		AutoException.initializeErrorCode();
		
		// open the input file 
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
		{
			// read information for Automobile
			Automobile auto = new Automobile();
				
			// read the make for the model
			try
			{
				readMake(reader, auto);
			}
			catch(AutoException e) // if the make is missing
			{
				//e.printErrorMessage();
				String make = (String) e.fix();
				auto.setMake(make);
			}
			// read name and price for the model
			try 
			{
				readModelName(reader, auto);
			} 
			catch(AutoException e) // if the name is missing
			{
				//e.printErrorMessage();
				String model = (String) e.fix();
				auto.setModel(model);
			}
			
			try 
			{
				readBasePrice(reader, auto);
			} 
			catch(AutoException e) // if the price is missing
			{
				//e.printErrorMessage();
				double price = (double) e.fix();
				auto.setBasePrice(price);
			}
			
			// read the size 
			int optionSetSize = Integer.parseInt(reader.readLine()); 
			auto.createOptionSets(optionSetSize); 			
			
			
			// read information for optionSets
			for (int index = 0; index < optionSetSize; index++)
			{
				readOptionSet(reader,auto);
			}
			return auto;
		
		} 
		catch (IOException e) // if the file is not found
		{
			System.out.print("input file does not exist!");
			System.exit(1);
			return null;
		}
		
	}
	
	/*
	 * writeAutoToDisk: write an Automotive object to the disk
	 * input: Automotive object
	 */
	public void writeAutoToDisk(Automobile auto)
	{
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("FordZTW.dat") ) )
		{
			out.writeObject(auto);
		}
		catch (IOException e) 
		{
			System.err.println("An IOException is caught :" + e.getMessage());
		}
	}

	
	
	
	/*
	 * readAutoFromDisk: read an Automotive object from the disk
	 * output: Automotive object
	 */
	public Automobile readAutoFromDisk()
	{
		try (ObjectInputStream out = new ObjectInputStream(new FileInputStream("FordZTW.dat") ) )
		{
			return (Automobile) out.readObject();
		}
		catch (IOException e) 
		{
			System.err.println("An IOException is caught :" + e.getMessage());
		}
		catch (ClassNotFoundException e)
		{
			System.err.println("An IOException is caught :" + e.getMessage());
		}
		return null;
	}
	
	// private inner methods:
	/*
	 * openFile: open a input file for a reader
	 * input: String fileName
	 */
	/*
	private BufferedReader openFile(String fileName) throws AutoException 
	{
		try 
		{
			return new BufferedReader(new FileReader(fileName));
		}
		catch (IOException e) // if file not found
		{
			throw new AutoException(AutoException.INVALID_FILENAME, "the input file for the model is not found!");
		}
	}
	*/
	/*
	 * readModelName: read the model make for the Automobile object
	 * input: BufferedReader reader, Automobile auto
	 */
	private void readMake(BufferedReader reader, Automobile auto) throws IOException, AutoException
	{
		// read the name
		String make = reader.readLine().replaceAll("^\\s+|\\s+$", "");
		
		if (make.equals("")) // if the model make is missing
			throw new AutoException(AutoException.errorCodes.get(ErrorCode.NO_MAKE).getErrorNO(), AutoException.errorCodes.get(ErrorCode.NO_MAKE).getErrorMSG());
		
		auto.setMake(make);
		
	}
	
	
	/*
	 * readModelName: read the model name for the Automobile object
	 * input: BufferedReader reader, Automobile auto
	 */
	private void readModelName(BufferedReader reader, Automobile auto) throws IOException, AutoException
	{
		// read the name
		String modelName = reader.readLine().replaceAll("^\\s+|\\s+$", "");
		
		if (modelName.equals("")) // if the model name is missing
			throw new AutoException(AutoException.errorCodes.get(ErrorCode.NO_MODEL_NAME).getErrorNO(), AutoException.errorCodes.get(ErrorCode.NO_MODEL_NAME).getErrorMSG());
		
		auto.setModel(modelName); 
		
	}
	/*
	 * readBasePrice: read the base price for the Automobile object
	 * input: BufferedReader reader, Automobile auto
	 */
	private void readBasePrice(BufferedReader reader, Automobile auto) throws IOException, AutoException
	{	
		// read the price
		try 
		{
			auto.setBasePrice(Double.parseDouble(reader.readLine()));  		
		} 
		catch (NumberFormatException e)
		{
			throw new AutoException(AutoException.errorCodes.get(ErrorCode.NO_MODEL_PRICE).getErrorNO(), AutoException.errorCodes.get(ErrorCode.NO_MODEL_PRICE).getErrorMSG());
		}
		
	}
	
	/*
	 * readOption: read the data of option for Automobile object
	 * 	 * input: BufferedReader reader, Automobile auto, String optionSetName, int position
	 */
	private void readOption(BufferedReader reader, Automobile auto,  String optionSetName, int position) throws IOException, AutoException
	{
		// get the option name and price
		String line = reader.readLine();
		if (line.equals("")) // if the option is missing
			throw new AutoException(AutoException.errorCodes.get(ErrorCode.NO__OPTION).getErrorNO(), AutoException.errorCodes.get(ErrorCode.NO__OPTION).getErrorMSG());
		
		String[] optionDatas= line.split(",");
		String optionName = optionDatas[0].replaceAll("^\\s+|\\s+$", "");
		double optionPrice = Double.parseDouble(optionDatas[1]);
		auto.setOption(optionSetName, position, optionName, optionPrice);
	}
	
	/*
	 * readOptionSet: read the data of optionSet for Automobile object
	 * input: BufferedReader reader, Automobile auto
	 */
	private void readOptionSet(BufferedReader reader, Automobile auto) throws IOException
	{
		// get option set name
		String optionSetName = null;
		try
		{
			optionSetName = readOptionSetName(reader, auto);
		}
		catch (AutoException e) // if the name is missing
		{
			//e.printErrorMessage();
			//StringBuffer temp = new StringBuffer();
			optionSetName = (String) e.fix();
			auto.setOptionSetName("", optionSetName);
			//optionSetName = temp.toString();
		}
		
		// get option size
		int optionSize = Integer.parseInt(reader.readLine());				
		auto.setOptionSetSize(optionSetName, optionSize);
		
		// read data for options
		for (int position = 0; position < optionSize; position++)
		{
			try 
			{
				readOption(reader,auto,optionSetName,position); 
			} 
			catch (AutoException e) // if the option is missing
			{
				//e.printErrorMessage();
				ArrayList<String> optionFields = (ArrayList<String>) e.fix();
				String optionName = optionFields.get(0);
				double optionPrice = Double.parseDouble(optionFields.get(1));
				auto.setOption(optionSetName, position, optionName, optionPrice);
			}
		}
	}
	
	/*
	 * readOptionSetName: read the name of optionSet for Automobile object
	 * input: BufferedReader reader, Automobile auto
	 * output: String optionSetName
	 */
	private String readOptionSetName(BufferedReader reader, Automobile auto) throws IOException, AutoException
	{
		String optionSetName = reader.readLine().replaceAll("\\s+","");	
		if (optionSetName.equals("")) // if the name is missing
			throw new AutoException(AutoException.errorCodes.get(ErrorCode.NO_OPTIONSET_NAME).getErrorNO(), AutoException.errorCodes.get(ErrorCode.NO_OPTIONSET_NAME).getErrorMSG());
		auto.setOptionSetName("", optionSetName);
		return optionSetName;
	}
	
	/*
	public static void main(String[] args)
	{
		FileIO fileHandler = new FileIO();
		
		Automotive auto = fileHandler.buildAutoObject("FordZTW.txt");
		//auto.print();
		fileHandler.writeAutoToDisk(auto);
		auto = fileHandler.readAutoFromDisk();
		auto.print();
		
		//System.out.print(Double.parseDouble(" 21 "));
	}
	*/
	
	/*
	 * parseProperties: parse a Properties object and build an automobile object 
	 * input: Properties object, fileType - 1 : text file, 2 : properties file
	 * output: Automobile object
	 */
	public Automobile parseProperties(Properties props, int fileType)
	{
		Automobile auto = null;
		String make = props.getProperty("make");
		
		if (!make.equals(null)) // check if the properties is empty or not
		{
			// get properties for model
			String model = props.getProperty("model");
			double basePrice = Double.parseDouble(props.getProperty("basePrice"));
			int noOfOptionSets = Integer.parseInt(props.getProperty("noOfOptionSets"));
			
			auto = new Automobile(model,make,basePrice,noOfOptionSets);
			// get properties for each optionSet
			for (int optionSetIndex = 1; optionSetIndex <= noOfOptionSets; optionSetIndex++)
			{
				String optionSetName = props.getProperty("optionSetName"+optionSetIndex);
				auto.setOptionSetName("", optionSetName);
				
				int noOfOptions = Integer.parseInt(props.getProperty("noOfOptions"+optionSetIndex));
				auto.setOptionSetSize(optionSetName, noOfOptions);
				
				// get properties for each option
				for (int optionIndex = 1; optionIndex <= noOfOptions; optionIndex++)
				{
					String optionName = props.getProperty("optionName"+optionSetIndex+"-"+optionIndex);
					double optionPrice = Double.parseDouble(props.getProperty("optionPrice"+optionSetIndex+"-"+optionIndex));
					auto.setOption(optionSetName, optionIndex -1, optionName, optionPrice);
				}
			}
			
		}
		
		
		return auto;
		
	}
	

}

