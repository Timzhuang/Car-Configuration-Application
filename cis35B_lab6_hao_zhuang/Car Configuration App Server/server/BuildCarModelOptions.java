package server;
import java.util.ArrayList;
import java.util.Properties;

import adapter.BuildAuto;
import model.Automobile;
import server.AutoServer;

public class BuildCarModelOptions implements AutoServer {
	
	// private attributes:
	AutoServer autoServer = new BuildAuto();

	/*
	 * parseProperties: receive a properties object from a client and parse it to build an antomobile object 
	 */
	public void parseProperties(Properties props)
	{
		autoServer.parseProperties(props);
	}

	/*
	 * getAutoList: return a list of all auto objects in the host
	 */
	public ArrayList<String> getModelList()
	{
		return autoServer.getModelList();
	}

	/*
	 * getAuto: return the auto object with given model
	 */
	public Automobile getAuto(String model)
	{
		return autoServer.getAuto(model);
	}
	

}
