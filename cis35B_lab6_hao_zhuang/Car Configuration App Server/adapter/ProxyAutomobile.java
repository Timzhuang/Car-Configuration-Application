package adapter;
import model.*;
import scale.*;
import util.*;
import exception.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public abstract class ProxyAutomobile {
	
	private static LinkedHashMap<String,Automobile> autoMap = new LinkedHashMap<String,Automobile>();
	
	// the functionalities provided by the API
	
	/*
	 * buildAuto: builds a instance of Automobile object with the data from the file of given file name
	 * input: String fileName
	 */
	public void buildAuto(String fileName)
	{
		// build an Automobile object from input file
		FileIO fileHandler = new FileIO();
		Automobile auto = fileHandler.buildAutoObject(fileName);
		autoMap.put(auto.getModel(), auto);
	}
	
	/*
	 * printAuto: searches for an Automotive object and prints its properties
	 * input: String modelName
	 */
	public void printAuto(String modelName)
	{
		if (autoMap.containsKey(modelName)) // if the model exists
			autoMap.get(modelName).print();
		else
			System.out.println("model does not exist\n");
	}
	/*
	 * updateOptionSetName: searches for an optionSet by name and set its name to the new name
	 * input: String modelName, String optionSetName, String newName
	 */
	public void updateOptionSetName(String modelName, String optionSetName, String newName)
	{
		if (autoMap.containsKey(modelName)) // if the model exists
			autoMap.get(modelName).setOptionSetName(optionSetName, newName); // replace the name of the optionSet if found
		else
			System.out.println("model does not exist\n");
	}
	
	/*
	 * updateOptionPrice: searches for an option by its name and its optionSet' name, update its price 
	 * input: String modelName, String optionSetName, String optionName, double newPrice
	 */
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, double newPrice)
	{
		if (autoMap.containsKey(modelName)) // if the model exists
			autoMap.get(modelName).setOptionPrice(optionName, optionSetName, newPrice); // update the price of the option if found
		else
			System.out.println("model does not exist\n");
	}
	
	/*
	 * fix: fix the exception of the Automobile object
	 * input: AutoException e
	 */
	public void fix(AutoException e)
	{
	}
	

	protected LinkedHashMap<String,Automobile> getAutoMap()
	{
		return autoMap;
	}
	
	
	/*
	 * operation: do all of the multi-threading tasks based on option Number
	 * input: int opNum, String[] input
	 */
	public void operation(int opNum, String[] input)
	{
		EditOptions editor = new EditOptions(opNum, input);
		editor.start();
	}
	
	/*
	 * parseProperties: receive a properties object from a client and parse it to build an antomobile object 
	 */
	public void parseProperties(Properties props)
	{
		FileIO fileHandler = new FileIO();
		Automobile auto = fileHandler.parseProperties(props, 2);
		autoMap.put(auto.getModel(), auto);
	}
	
	/*
	 * getAutoList: return a list of all auto objects in the host
	 */
	public ArrayList<Automobile> getAutoList()
	{
		return new ArrayList<Automobile>(autoMap.values()); 
	}
	
	/*
	 * getModelList: return a list of names of all models
	 */
	public ArrayList<String> getModelList()
	{
		ArrayList<String> modelList = new ArrayList<String>();
		for (Map.Entry<String, Automobile> entry: autoMap.entrySet())
		{
			modelList.add(entry.getValue().getModel());
		}
		return modelList;
	}
	
	/*
	 * getAuto: return the auto object with given model
	 */
	public Automobile getAuto(String model)
	{
		if (autoMap.containsKey(model)) // if the model exists
		{	
			return autoMap.get(model);
		}
		else
			System.out.println("model does not exist\n");
		return null;
	}

}

/*
 * updateOptionName: update the name of the option in the given optionset and model.
 * this feature is supported by multi-threading 
 * input: String modelName, String optionSetName, String optionName, String newOptionName

public void updateOptionName(String modelName, String optionSetName, String optionName, String newOptionName)
{
	EditOptions editor = new EditOptions(EditOptions.opt_editOptionName, optionName, newOptionName, modelName, optionSetName);
	editor.start();
}

 */
