package model;

import java.util.ArrayList;
import java.io.Serializable;

class OptionSet implements Serializable {
	
	// attributes:
	private ArrayList<Option> options;
	private String name;
	private Option optionChoice;
	private static final long serialVersionUID = 1L;
	
	// constructor
	protected OptionSet()
	{
		options = new ArrayList<Option>();
		setName("");
		optionChoice = null;
	}
	
	protected OptionSet(String name, int size)
	{
		options = new ArrayList<Option>();
		setName(name);
		optionChoice = null;
		
		for (int i = 0; i < size; i++)
			options.add(new Option());
	}
	
	protected OptionSet(String name)
	{
		setName(name);
		options = new ArrayList<Option>();
		optionChoice = null;
	}
	
	protected OptionSet(int size)
	{
		options = new ArrayList<Option>();
		setName("");
		optionChoice = null;
		
		for (int i = 0; i < size; i++)
			options.add(new Option());
	}
	
	// getters:
	/*
	 * getOptionChoice: return the optionChoice
	 * output: Option optionChoice
	 */
	protected Option getOptionChoice()
	{
		return optionChoice;
	}
	
	
	/*
	 * getOption: return the option at give position
	 * input: position
	 * output: option
	 */
	protected Option getOption(int position)
	{
		// boundary check
		if (position >= 0 && position < options.size() )
			return options.get(position);
		else
			throw new IndexOutOfBoundsException("Index " + position + " is out of bounds!");
	}
	
	// getName: return name
	protected String getName() 
	{
		return name;
	}
	
	// setter:
	// setName: set name
	protected void setName(String name)
	{
		this.name = name;
	}
	
	/*
	 *  setOptionChoice: set the optionChoice of the given option name 
	 *  input: String optionName
	 */
	protected void setOptionChoice(String optionName)
	{
		if(findOption(optionName) != null) // if the option exists
			optionChoice = findOption(optionName);
	}
	
	
	/*
	 * setOption: set the option at given position
	 * input: option, position
	 */
	protected void setOption(int position, Option opt)
	{
		// boundary check
		if (position >= 0 && position < options.size() )
			options.set(position, opt);
		else
			throw new IndexOutOfBoundsException("Index " + position + " is out of bounds!");
	}
	/*
	 * setOption: set the option at given position
	 * input: name, price, position
	 */
	protected void setOption(int position, String name, double price)
	{
		// boundary check
		if (position >= 0 && position < options.size() )
			options.set(position, new Option(name, price));
		else
			throw new IndexOutOfBoundsException("Index " + position + " is out of bounds!");
	}
	
	/*
	 * setSize: change the size of the optionSet
	 * input size
	 */
	protected void setSize(int size)
	{
		options = new ArrayList<Option>();
		
		for (int i = 0; i < size; i++)
			options.add(new Option());
	}
	
	/*
	 * findOption: search for the option of given name
	 * input: name
	 * output: option or null if not found
	 */
	protected Option findOption(String name)
	{
		// iterate over the optionSet to find the option
		for (int index = 0; index < options.size(); index++)
		{
			// if found, return the option, otherwise return null
			if (options.get(index).getName().equals(name))
				return options.get(index);	
		}
		System.out.println("option not found");
		return null;
		
	}
	
	/*
	 * deleteOption: delete the option with given name
	 * input: name
	 * output: true or false
	 */
	protected boolean deleteOption(String name)
	{
		// iterate over the optionSet to find the option
		for (int index = 0; index < options.size(); index++)
		{
			// delete if found
			if (options.get(index).getName().equals(name))
			{
				/*
				while (index < options.length - 1)
					options[index] = options[++index];
				*/
				options.set(index, new Option());
				return true;
			}
		}
		return false;		
	}
	/*
	 * createOptions: based on the given size, create a number of empty options
	 * input: int size
	 */
	protected void createOptions(int size)
	{
		options = new ArrayList<Option>();
		
		for (int i = 0; i < size; i++)
			options.add(new Option());
	}
	
	// print its data
	protected void print()
	{
		// print the name of the optionSets
		System.out.printf("OptionSet: %s\n" , name);
		// print the data of all options
		for (Option opt: options)
		{
			opt.print();
		}
	}
	
	// toString: print its data
	public String toString()
	{
		// store the name of the optionSets
		StringBuffer sb = new StringBuffer("OptionSet: ");
		sb.append(name);
		sb.append("\n");
		// store the data of all options
		for (Option opt: options)
		{
			sb.append(opt.toString());
		}
		return sb.toString();
		
	}
	
	
	// size(): return the size of optionset
	protected int size()
	{
		return options.size();
	}
	
	/*
	// inner class: Option
	
	protected class Option implements Serializable {
		// attributes:
		private String name;
		private double price;
		
		// methods:
		// constructor:
		protected Option() 
		{
			setName("");
			setPrice(0);
		}
		
		protected Option(String name, double price)
		{
			setName(name);
			setPrice(price);
		}
		
		protected Option(String name)
		{
			setName(name);
			setPrice(0);
		}
		
		protected Option(double price)
		{
			setName("");
			setPrice(price);
		}
		// getters:
		// getName: return name
		protected String getName()
		{
			return name;
		}
		
		// getPrice: return price
		protected double getPrice()
		{
			return price;
		}
		
		
		// setters:
		// setters for name
		protected void setName(String name)
		{
			this.name = name;
		}
		
		// setter for price
		protected void setPrice(double price)
		{
			this.price = price;
		}
		
		// print its data
		protected void print()
		{
			System.out.printf("option: %s, price: %.0f\n", name, price);
		}
		
		// toString: print its data
		public String toString()
		{
			StringBuffer sb = new StringBuffer("option: ");
			sb.append(name);
			sb.append(", price: ");
			sb.append(price);
			sb.append("\n");
			return sb.toString();
		}
	}
	
	*/
	/*
	public static void main(String[] args)
	{
		OptionSet opts = new OptionSet("A",4);
		

		opts.setOption(0, new Option("blue", 10));
		opts.setOption(1, new Option("red", 20));
		opts.setOption(2, new Option("green", 30));
		opts.setOption(3, new Option("white", 40));
		
		System.out.println(opts.getName());
		opts.print();
		
		
		System.out.println(opts.findOption("1"));
		opts.print();
		
	}
	*/

}


