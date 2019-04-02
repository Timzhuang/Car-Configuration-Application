package exception;

public class ErrorCode {
	// public static final variables:
	public static final int NO_MODEL_PRICE = 1;
	public static final int NO_MODEL_NAME = 2;
	public static final int NO_OPTIONSET_NAME = 3;
	public static final int NO__OPTION = 4;
	public static final int NO_OPTIONSET = 5;
	public static final int NO_MAKE = 6;
	
	// private variables:
	private int errorNO;
	private String errorMSG;
	
	// constructors:
	public ErrorCode()
	{
		setErrorNO(-1);
		setErrorMSG("");
	}
	public ErrorCode(int errorNO)
	{
		setErrorNO(errorNO);
		setErrorMSG("");
	}
	public ErrorCode(String errorMSG)
	{
		setErrorNO(-1);
		setErrorMSG(errorMSG);
	}
	public ErrorCode(int errorNO, String errorMSG)
	{
		setErrorNO(errorNO);
		setErrorMSG(errorMSG);
	}
	
	
	// setters:
	public void setErrorNO(int errorNO)
	{
		this.errorNO = errorNO;
	}
	
	public void setErrorMSG(String errorMSG)
	{
		this.errorMSG = errorMSG;
	}
	
	// getters:
	public int getErrorNO()
	{
		return errorNO;
	}
	
	public String getErrorMSG()
	{
		return errorMSG;
	}

}
