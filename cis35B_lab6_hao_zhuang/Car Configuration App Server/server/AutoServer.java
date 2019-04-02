package server;

import java.util.ArrayList;
import java.util.Properties;
import model.Automobile;

public interface AutoServer {

	/*
	 * parseProperties: receive a properties object from a client and parse it to build an antomobile object 
	 */
	public void parseProperties(Properties props);
	
	/*
	 * getAutoList: return a list of all auto models in the host
	 */
	public ArrayList<String> getModelList();
	
	/*
	 * getAuto: return the auto object with given model
	 */
	public Automobile getAuto(String model);
	
}
