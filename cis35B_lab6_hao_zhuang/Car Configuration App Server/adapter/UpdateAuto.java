package adapter;

public interface UpdateAuto {

	/*
	 * updateOptionSetName: searches for an optionSet by name and set its name to the new name
	 * input: String modelName, String optionSetName, String newName
	 */
	public void updateOptionSetName(String modelName, String optionSetName, String newName);
	/*
	 * updateOptionPrice: searches for an option by its name and its optionSet' name, update its price 
	 * input: String modelName, String optionSetName, String optionName, double newPrice
	 */
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, double newPrice);
	
}
