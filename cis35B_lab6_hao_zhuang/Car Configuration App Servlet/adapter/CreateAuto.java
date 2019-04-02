package adapter;

public interface CreateAuto {
	/*
	 * buildAuto: builds a instance of Automobile object with the data from the file of given file name
	 * input: String fileName
	 */
	public void buildAuto(String fileName);
	/*
	 * printAuto: searches for an Automotive object and prints its properties
	 * input: String modelName
	 */
	public void printAuto(String modelName);

}
