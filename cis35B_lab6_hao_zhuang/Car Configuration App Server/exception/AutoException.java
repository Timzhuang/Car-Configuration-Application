package exception;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import model.*;

import java.util.ArrayList;

public class AutoException extends Exception {
	
	// public static constant
	//public static final int INVALID_FILENAME = 0;
	/*
	public static final int NO_MODEL_PRICE = 1;
	public static final int NO_MODEL_NAME = 2;
	public static final int NO_OPTIONSET_NAME = 3;
	public static final int NO__OPTION = 4;
	public static final int NO_OPTIONSET = 5;
	*/
	// public static variable
	public static ArrayList<ErrorCode> errorCodes;
	
	
	// private attributes:
	private int errorNO;
	private String errorMSG;
	
	// public static methods:
	/*
	 * initializeErrorCodes: initialize the errorCodes from a file
	 */
	public static void initializeErrorCode()
	{
		errorCodes = new ArrayList<ErrorCode>();
		
		// open the file 
		try (BufferedReader reader = new BufferedReader(new FileReader("error_codes.txt")))
		{
			// read line by line and each line is for one error code
			String line = "";
			while ((line = reader.readLine()) != null)
			{
				String[] errorDatas = line.split(",");
				ErrorCode errorCode = new ErrorCode(Integer.parseInt(errorDatas[0]), errorDatas[1].replaceAll("^\\s+|\\s+$", ""));
				errorCodes.add(errorCode);
			}
		}
		catch (IOException e) // if the file is not found
		{
			System.exit(1);
		}
		//for (ErrorCode e: errorCodes)
			//System.out.println(e.getErrorNO());
	}
	
	// constructors:
	public AutoException()
	{
		super();
		errorNO = -1;
		errorMSG = "";
		printErrorMessage();
		logTimestamps();
	}
	
	public AutoException(int errorNO)
	{
		super();
		errorMSG = "";
		setErrorNO(errorNO);
		printErrorMessage();
		logTimestamps();
	}
	
	public  AutoException(String errorMSG)
	{
		super();
		errorNO = -1;
		setErrorMSG(errorMSG);
		printErrorMessage();
		logTimestamps();
	}
	
	public  AutoException(int errorNO, String errorMSG)
	{
		super();
		setErrorNO(errorNO);
		setErrorMSG(errorMSG);
		printErrorMessage();
		logTimestamps();
	}
	
	// setters:
	/*
	 * setErrorNO: set the error number
	 * input: int errorNO
	 */
	public void setErrorNO(int errorNO) 
	{
		this.errorNO = errorNO;
	}

	/*
	 * setErrorMSG: set the error message
	 * input: String errorMSG
	 */
	public void setErrorMSG(String errorMSG) 
	{
		this.errorMSG = errorMSG;
	}
	
	// getters:
	/*
	 * getErrorMSG: return the error message
	 * output: errorMSG
	 */
	public String getErrorMSG() 
	{
		return errorMSG;
	}
	
	/*
	 * getErrorNO: return the error number
	 * output: errorNO
	 */
	public int getErrorNO() 
	{
		return errorNO;
	}

	/*
	 * printErrorMessage(): print the error message
	 */
	public void printErrorMessage()
	{
		System.out.println("Error Number: " + errorNO + ", Error Message: " + errorMSG);
	}
	
	/*
	 * fix: fixes any exception for the application
	 * input: AutoException autoException, Automobile auto, StringBuffer optionSetName
	 */
	// Automobile auto, StringBuffer optionSetName
	public Object fix()
	{
		AutoExceptionHelper helper = new AutoExceptionHelper();
		Object holder = null;
		
		switch(errorNO) // fix the problem based on the error number
		{
		//case INVALID_FILENAME:
			//helper.fixError0();
			//break;
		case ErrorCode.NO_MODEL_PRICE:
			holder = helper.fixError1();
			break;
		case ErrorCode.NO_MODEL_NAME:
			holder = helper.fixError2();
			break;
		case ErrorCode.NO_OPTIONSET_NAME:
			holder = helper.fixError3();
			break;
		case ErrorCode.NO__OPTION:
			holder = helper.fixError4();
			break;
		/*
		case ErrorCode.NO_OPTIONSET:
			//returb helper.fixError5();
			break;
		*/
		case ErrorCode.NO_MAKE:
			holder = helper.fixError6();
			break;
		default:
			System.out.println("This error cannot be handled yet");
			break;
		}
		
		return holder;
	}
	
	/*
	 * logTimestamps: log AutoException with timestamps into a log file
	 */
	public void logTimestamps()
	{
		try 
		{
			File logFile = new File("AutoExceptionLog.txt");
			if (!logFile.exists())
				logFile.createNewFile();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true));
			writer.write("Error NO: " + (errorNO != -1? errorNO : "Error No not given") + " Error Message: " + (errorMSG != ""? errorMSG : "Error message not given"));
			writer.newLine();
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
}
