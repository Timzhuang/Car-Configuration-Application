package model;

import java.io.Serializable;
import java.util.ArrayList;


public class Automobile implements Serializable {

	// attributes:
	private String model;
	private double basePrice;
	private String make;
	private ArrayList<OptionSet> optionSets;
	private ArrayList<Option> optionChoices;
	private static final long serialVersionUID = 1L;
	//private boolean isAvailableForEditing = true;
	
	
	// methods:
	// constructors:
	public Automobile()
	{
		setMake("");
		setModel("");
		setBasePrice(0);
		optionSets = new ArrayList<OptionSet>();
		optionChoices = new ArrayList<Option>();
	}
	
	public Automobile(String model, double basePrice, int size)
	{
		setMake("");
		setModel(model);
		setBasePrice(basePrice);
		optionSets = new ArrayList<OptionSet>();
		optionChoices = new ArrayList<Option>();
		
		for (int i = 0; i < size; i++)
			optionSets.add(new OptionSet());
	}
	
	public Automobile(double basePrice, int size)
	{
		setMake("");
		setModel("");
		setBasePrice(basePrice);
		optionSets = new ArrayList<OptionSet>();
		optionChoices = new ArrayList<Option>();
		
		for (int i = 0; i < size; i++)
			optionSets.add(new OptionSet());
	}
	
	public Automobile(String model, int size)
	{
		setMake("");
		setModel(model);
		setBasePrice(0);
		optionSets = new ArrayList<OptionSet>();
		optionChoices = new ArrayList<Option>();
		
		for (int i = 0; i < size; i++)
			optionSets.add(new OptionSet());
	}
	
	public Automobile(String model, String make, int size)
	{
		setMake(make);
		setModel(model);
		setBasePrice(0);
		optionSets = new ArrayList<OptionSet>();
		optionChoices = new ArrayList<Option>();
		
		for (int i = 0; i < size; i++)
			optionSets.add(new OptionSet());
	}
	
	public Automobile(String model, double basePrice)
	{
		setMake("");
		setModel(model);
		setBasePrice(basePrice);
		optionSets = new ArrayList<OptionSet>();
		optionChoices = new ArrayList<Option>();
	}
	
	public Automobile(String model, String make, double basePrice)
	{
		setMake(make);
		setModel(model);
		setBasePrice(basePrice);
		optionSets = new ArrayList<OptionSet>();
		optionChoices = new ArrayList<Option>();
	}
	
	public Automobile(String model, String make, double basePrice, int size)
	{
		setMake(make);
		setModel(model);
		setBasePrice(basePrice);
		optionSets = new ArrayList<OptionSet>();
		optionChoices = new ArrayList<Option>();
		
		for (int i = 0; i < size; i++)
			optionSets.add(new OptionSet());
	}
	// getters:
	// getMake: return the make
	public String getMake()
	{
		return make;
	}
	
	// getModel: return the name of model
	public String getModel()
	{
		return model;
	}
	
	// getBasePrice: return the base price
	public double getBasePrice()
	{
		return basePrice;
	}
	
	/*
	 * getOptionChoice: return the name of the optionChoice of the given optionSet
	 * input: String optionSetName
	 * output: the name of the optionChoice or null if not exist
	 */
	public String getOptionChoice(String optionSetName)
	{
		try
		{
			return findOptionSet(optionSetName).getOptionChoice().getName();
		}
		catch (NullPointerException e) // if the optionSet is not found
		{
			if (findOptionSet(optionSetName) != null &&findOptionSet(optionSetName).getOptionChoice() == null)
			{
				System.out.println("this option set has not had a choice");
				return "None";
			}
			return null;
		}
	}
	
	/*
	 * getOptionChoice: return the price of the optionChoice of the given optionSet
	 * input: String optionSetName
	 * output: the price of the optionChoice or -1 if not exist
	 */
	public double getOptionChoicePrice(String optionSetName)
	{
		try
		{
			return findOptionSet(optionSetName).getOptionChoice().getPrice();
		}
		catch (NullPointerException e) // if the optionSet is not found
		{
			if (findOptionSet(optionSetName) != null && findOptionSet(optionSetName).getOptionChoice() == null)
				System.out.println("this option set has not had a choice");
			return 0;
		}
	}
	
	/*
	 * getTotalPrice: return the total price of all option choices
	 * output: the total price
	 */
	public double getTotalPrice()
	{
		double sum = 0;
		
		for (Option opt : optionChoices)
		{
			sum += opt.getPrice();
		}
		
		/*
		for (OptionSet optSet: optionSets)
		{
			if (optSet.getOptionChoice() != null)
				sum += optSet.getOptionChoice().getPrice();
		}
		*/
		return sum + basePrice;
	}
	
	/*
	 * getOptionSet: return the optionset of given index
	 * input: index
	 * output: optionSet
	 */
	public OptionSet getOptionSet(int index)
	{
		// boundary check
		if (index >= 0 && index < optionSets.size() )
			return optionSets.get(index);
		else
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
	}
	
	/*
	 * getOptionSet: return the optionset of given name
	 * input: String optionSetName
	 * output: optionSet
	 */
	public OptionSet getOptionSet(String optionSetName)
	{
		// get the old choice
		for (int index = 0; index < optionSets.size(); index++)
		{
			if (optionSets.get(index).getName().equals(optionSetName))
				return optionSets.get(index);
		}
		System.out.println("optionSet not found");
		return null;
	}
	
	/*
	 * getOptionSetNames: return all the names of the optionsets
	 * output: ArrayList<String> 
	 */
	public ArrayList<String> getOptionSetNames()
	{
		ArrayList<String> optionSetNames = new ArrayList<String>();
		// get the old choice
		for (int index = 0; index < optionSets.size(); index++)
			optionSetNames.add(optionSets.get(index).getName());
		
		return optionSetNames;
	}
	
	/*
	 * getOptionNames: return all the names of otpions of the given optionSet
	 * string optionSetName
	 */
	public ArrayList<String> getOptionNames(String optionSetName)
	{
		ArrayList<String> optionNames = new ArrayList<String>();
		// find the optionSet
		OptionSet optSet = getOptionSet(optionSetName);
	
		if (optSet == null)
			return null;
		// get all the names of options
		for (int index = 0; index < optSet.size(); index++)
			optionNames.add(optSet.getOption(index).getName());
		return optionNames;
	}
	/*
	 * getOptionNames: return all the names of otpions of the given optionSet
	 * string optionSetName
	 */
	public ArrayList<String> getOptionPrices(String optionSetName)
	{
		ArrayList<String> optionNames = new ArrayList<String>();
		// find the optionSet
		OptionSet optSet = getOptionSet(optionSetName);
	
		if (optSet == null)
			return null;
		// get all the prices of options
		for (int index = 0; index < optSet.size(); index++)
			optionNames.add(optSet.getOption(index).getPrice()+"");
		return optionNames;
	}
	/*
	 * getOptionSetName: return the name of optionset of given index
	 * input: index
	 * output: optionSetName
	 */
	public String getOptionSetName(int index)
	{
		// boundary check
		if (index >= 0 && index < optionSets.size() )
			return optionSets.get(index).getName();
		else
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
	}
	
	/*
	 * getOptionSetSize: return the number of option sets
	 * output: int size
	 */
	public int getOptionSetSize()
	{
		return optionSets.size();
	}
	
	// find methods
	
	/*
	 * findOptionSet: return the OptionSet of given name
	 * input: name
	 * output: optionSet
	 */
	public OptionSet findOptionSet(String name)
	{
		// iterate over the optionSets to find the optionSet
		for (int index = 0; index < optionSets.size(); index++)
		{
			if (optionSets.get(index).getName().equals(name))
				return optionSets.get(index);	
		}
		System.out.println("optionSet not found");
		return null;
	}
	
	
	/*
	 * findOption: return the Option of given optionSet and name
	 * input: option name, optionSet name
	 * output: option
	 */
	public Option findOption(String optionSetName, String optionName)
	{
		try
		{
			// find the optionSet first, then use it to find the Option
			return findOptionSet(optionSetName).findOption(optionName);
		}
		catch (NullPointerException e) // if the optionSet is not found
		{
			return null;
		}
	}
	
	// setters:
	// setMake: set the make
	public void setMake(String make)
	{
		this.make = make;
	}
	
	// setModel: set the name of model
	public void setModel(String model)
	{
		this.model = model;
	}
	
	// setBasePrice: set basePrice
	public void setBasePrice(double price)
	{
		basePrice = price;
	}
	
	/*
	 * setOptionChoice: set the optionChoice of the given optionSet
	 * input: String optionSetName, String optionName
	 */
	public void setOptionChoice(String optionSetName, String optionName)
	{
		try
		{
			// get the old choice
			Option oldChoice = getOptionSet(optionSetName).getOptionChoice();
			// set the new choice
			findOptionSet(optionSetName).setOptionChoice(optionName);
			updateOptionChoices(findOption(optionSetName, optionName), oldChoice);
		}
		catch (NullPointerException e) // if the optionSet is not found
		{
			return;
		}
	}
	
	/*
	 * setOptionSet: set the optionSet of the given index
	 * input: index, OptionSet
	 */
	public void setOptionSet(int index, OptionSet optSet)
	{
		// boundary check
		if (index >= 0 && index < optionSets.size() )
			optionSets.set(index, optSet);
		else
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
	}
	
	/*
	 * setOptionSet: set the optionSet of the given index and name, options' names and price
	 * input: index, optionSetName, optionName[], optionPrice[]
	 */
	public void setOptionSet(int index, String optionSetName, String[] optionNames, double[] optionPrices)
	{
		// boundary check
		if (index >= 0 && index < optionSets.size() )
		{
			// create a new option set
			OptionSet optSet = new OptionSet(optionSetName, optionNames.length);
			// set all options
			for (int position = 0; position < optionNames.length; position++)
				optSet.setOption(position, optionNames[position], optionPrices[position]);
			
			optionSets.set(index, optSet);
		}
		else
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
	}
	
	/*
	 * setOptionSet: set the optionSet of the given old and new name, options' names and price
	 * input: oldOptionSetName , newOptionSetName, optionName[], optionPrice[]
	 */
	public void setOptionSet(String oldOptionSetName ,String newOptionSetName, String[] optionNames, double[] optionPrices)
	{
		// find the optionSet with the old name
		for (int index = 0; index < optionSets.size(); index++)
		{
			//System.out.println( "|"+optionSets[index].getName() +"|"+ oldOptionSetName +"|");
			// if found the optionSet, set it to the new optionSet
			if (optionSets.get(index).getName().equals(oldOptionSetName))
			{
				// create a new option set
				OptionSet optSet = new OptionSet(newOptionSetName, optionNames.length);
				// set all options
				for (int position = 0; position < optionNames.length; position++)
					optSet.setOption(position, optionNames[position], optionPrices[position]);
				
				optionSets.set(index, optSet);
				return;
			}
		}
		
		// if not found
		System.out.println("option set not found");
	}
	
	// update optionSets and options
	/*
	 * setOptionSetName: change name of the optionSet of the given old name
	 * input: oldName, newName
	 */
	public void setOptionSetName(String oldName, String newName)
	{
		try
		{
			findOptionSet(oldName).setName(newName); // if the optionSet is found
		}
		catch (NullPointerException e) // if the optionSet is not found
		{
			return;
		}
	}
	
	/*
	 * setOptionSetName: change name of the optionSet of the given index
	 * input: oldName, newName
	 */
	public void setOptionSetName(int index, String name)
	{
		// boundary check
		if (index >= 0 && index < optionSets.size() )
		{
			optionSets.get(index).setName(name);	
		}
		else
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
	}
	
	/*
	 * setOptionSeSize: change size of the optionSet of the given name
	 * input: name, size
	 */
	public void setOptionSetSize(String name, int size)
	{
		try
		{
			findOptionSet(name).setSize(size);; // if the optionSet is not found
		}
		catch (NullPointerException e) // if the optionSet is not found
		{
			return;
		}
	}
	
	/*
	 * setOptionSetName: change size of the optionSet of the given index
	 * input: index, size
	 */
	public void setOptionSetSize(int index, int size)
	{
		// boundary check
		if (index >= 0 && index < optionSets.size() )
		{
			optionSets.get(index).setSize(size);
		}
		else
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
	}
	
	
	/*
	 * createOptionSets: set a array of optionSets with a given size
	 * input: size
	 */
	public void createOptionSets(int size)
	{
		optionSets = new ArrayList<OptionSet>();
		
		for (int i = 0; i < size; i++)
			optionSets.add(new OptionSet());
	}
	
	/*
	 * setOption: set option for the given optionSet with the index
	 * input: OptionSetName, option, index
	 */
	public void setOption(String optionSetName, int index, Option opt)
	{
		try
		{
			// find the optionSet first then use it to set the option
			findOptionSet(optionSetName).setOption(index, opt);
		}
		catch (NullPointerException e) // if optionSet not found
		{
			return;
		}
		
	}
	
	/*
	 * setOption: set option for the given optionSet with the index and option's name and price
	 * input: OptionSetName, name, price, index
	 */
	public void setOption(String optionSetName, int index, String name, double price)
	{
		/*
		while(!isAvailableForEditing)
		{
			try {
				wait();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		isAvailableForEditing = false; 
		*/
		try
		{
			// find the optionSet first then use it to set the option
			findOptionSet(optionSetName).setOption(index, name, price);
		}
		catch (NullPointerException e) // if optionSet not found
		{
			return;
		}
		/*
		finally
		{
			isAvailableForEditing = true;
			notifyAll();
		}
		*/
		
	}
	
	/*
	 * setOptionName: change the name of the option of a given name
	 * input; oldOptionName, optionSetName, newOptionName
	 */
	public void setOptionName(String oldOptionName, String optionSetName, String newOptionName)
	{
		System.out.println("setting option name from " + oldOptionName + " to " + newOptionName);
		try
		{
			// find the optionSet first then use it to set the option name
			findOptionSet(optionSetName).findOption(oldOptionName).setName(newOptionName);
		}
		catch (NullPointerException e) // if optionSet not found
		{
			return;
		}
		System.out.println("after setting:\n" + findOptionSet(optionSetName));
	}
	
	/*
	 * setOptionPrice: change the price of the option of a given name
	 */
	public void setOptionPrice(String optionName, String optionSetName, double price)
	{
		try
		{
			// find the optionSet first then use it to set the option name
			findOptionSet(optionSetName).findOption(optionName).setPrice(price);
		}
		catch (NullPointerException e) // if optionSet not found
		{
			return;
		}
	}
	
	// delete
	
	/*
	 * deleteOptionSet: delete the optionSet of given name
	 * input: name
	 * output: true or false
	 */
	public boolean deleteOptionSet(String name)
	{
		// iterate over the optionSets to find the optionSet
		for (int index = 0; index < optionSets.size(); index++)
		{
			if (optionSets.get(index).getName().equals(name))
			{
				/*
				while (index < options.length - 1)
					options[index] = options[++index];
				*/
				optionSets.set(index, new OptionSet());
				return true;
			}
		}
		return false;
	}
	
	/*
	 * deleteOption: delete the optionSet of given optionSet and name
	 * input: optionSetname, optionName
	 * output: true or false
	 */
	
	public boolean deleteOption(String optionSetName, String optionName)
	{
		try
		{
			// find the OptionSet first, then use it to delete the option
			return findOptionSet(optionSetName).deleteOption(optionName);
				
		}
		catch (NullPointerException e) // if the optionSet not found
		{
			return false;
		}
	}
	
	/*
	 * updatOptionChoices: update the optionChoices when a new option is choosen
	 * input: Option choice, Option oldChoice
	 */
	private void updateOptionChoices(Option choice, Option oldChoice)
	{	
		// make sure the choice is found
		if (choice != null)
		{
			// delete the old choice
			optionChoices.remove(oldChoice);
			// add the new choice
			optionChoices.add(choice);
		}
	}
	
	// print its data
	public void print()
	{
		// print the model name and base price
		System.out.printf("Model: %s, make: %s, base price: %.0f\n" , model, make, basePrice);
		// print the data of all optionSet
		for (OptionSet optSet: optionSets)
		{
			optSet.print();
			System.out.println();
		}
	}
	
	// toString: print its data
	public String toString()
	{
		// store the model name and base price
		StringBuffer sb = new StringBuffer("Model: ");
		sb.append(model);
		sb.append(", make: ");
		sb.append(make);
		sb.append(", base price: ");
		sb.append(basePrice);
		sb.append("\n");
		// store the data of all optionSet
		for (OptionSet optSet: optionSets)
		{
			sb.append(optSet.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	/*
	public static void main(String[] args)
	{
		OptionSet opts1 = new OptionSet("A",4);
		

		opts1.setOption(0, opts1.new Option("blue", 10));
		opts1.setOption(1, opts1.new Option("red", 20));
		opts1.setOption(2, opts1.new Option("green", 30));
		opts1.setOption(3, opts1.new Option("white", 40));
		
		OptionSet opts2 = new OptionSet("B",4);
		

		opts2.setOption(0, opts2.new Option("dark", 35));
		opts2.setOption(1, opts2.new Option("orange", 24));
		opts2.setOption(2, opts2.new Option("gold", 50));
		opts2.setOption(3, opts2.new Option("silver", 40));
		
		
		Automotive model = new Automotive("ZTG", 18000, 2);
		
		model.setOptionSet(0, opts1);
		model.setOptionSet(1, opts2);
		model.setOption("B",2,opts2.new Option("good", 1223) );
		System.out.println(model.toString());
		
		
		
	}
	*/
	
}
