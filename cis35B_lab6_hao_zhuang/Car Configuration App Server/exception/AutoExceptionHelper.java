package exception;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

public class AutoExceptionHelper {

	
	/*
	 * fixError0: handle the exception that file name is missing or wrong
	 */
	/*
	public void fixError0()
	{
		System.out.println("Please check the file path");
	}
	*/
	/*
	 * fixError1: handle the exception that the price for Automobile is not provided
	 * 			  via asking the user to enter it through console
	 * output: the price of the model
	 */
	
	public double fixError1()
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("\nPlease enter the price for the automobile model:");
		return reader.nextDouble();
	}
	
	/*
	 * fixError2: handle the exception that the name for Automobile is not provided
	 * 			  via asking the user to enter it through console
	 * output: name for the automobile model
	 */
	public String fixError2()
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("\nPlease enter the name for the automobile model:");
		return reader.nextLine();
	}
	
	/*
	 * fixError3: handle the exception that optionSet name is not provided or missing
	 * 			  via asking the user to enter it through console
	 * output: name for the optionSet
	 */
	public String fixError3()
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("\nPlease enter the name for the optionSet:");
		return reader.nextLine();
	}
	
	/*
	 * fixError4: handle the exception that a option missing
	 * 			  via asking the user to enter it through console
	 * input: Automotive object
	 */
	public ArrayList<String> fixError4()
	{
		
		// get data of Option from the user
		Scanner reader = new Scanner(System.in);
		System.out.println("\nPlease enter the name of the option:");
		String optionName = reader.nextLine();
		System.out.println("\nPlease enter the price of the option:");
		double price = reader.nextDouble();
		//System.out.println("\nPlease enter the name of the optionSet:");
		reader.nextLine(); // clear the "\n"
		//String optionSetName = reader.nextLine();
		
		ArrayList<String> optionFields = new ArrayList<String>();
		optionFields.add(optionName);
		optionFields.add(price+"");
		//optionFields.add(optionSetName);
		
		return optionFields;
		
	}
	
	/*
	 * fixError5: handle the exception that a optionSet missing
	 * 			  via asking the user to enter it through console
	 * input: Automotive object, int position
	 */
	public void fixError5()
	{
		/*
		// get data of OptionSet from the user
		Scanner reader = new Scanner(System.in);
		System.out.println("\nPlease enter the name of the option set:");
		String optionSetName = reader.nextLine();
		System.out.println("\nPlease enter the number of options:");
		int size = reader.nextInt();
		
		double[] optionPrices = new double[size];
		String[] optionNames = new String[size];
		
		for (int index = 0; index < size; index++)
		{
			System.out.println("\nPlease enter the price of the option:");
			optionPrices[index] = reader.nextDouble();
			System.out.println("\nPlease enter the name of the optionSet:");
			optionNames[index] = reader.nextLine();
		}
		
		// create the Option
		auto.setOptionSet("", optionSetName, optionNames, optionPrices);
		*/
	}
	
	/*
	 * fixError6: handle the exception that the make for Automobile is not provided
	 * 			  via asking the user to enter it through console
	 * input: Automotive object
	 */
	
	public String fixError6()
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("\nPlease enter the make for the automobile model:");
		return reader.nextLine();
	}
	
	
}
