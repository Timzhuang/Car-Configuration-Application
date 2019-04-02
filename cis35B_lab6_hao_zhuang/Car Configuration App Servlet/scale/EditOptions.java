package scale;
import adapter.ProxyAutomobile;


public class EditOptions extends ProxyAutomobile implements Runnable{
	
	// public static constants:
	public static final int opt_editOptionName = 0;
	public static final int opt_editOptionName_nonSyncrhonized = 1;
	
	// private variables:
	private int optNum;
	private Thread thread;
	private boolean isAvailableForEditing = true; 

	// variables for updating option name:
	private String optOldName;
	private String optNewName;
	private String modelName;
	private String optSetName;
	
	
	// public methods:
	
	// constructors:
	public EditOptions(int optNum) 
	{
		this.optNum = optNum;
		thread = new Thread(this);
	}
	public EditOptions()
	{
		this.optNum = -1;
		thread = new Thread(this);
	}
	
	public EditOptions(int optNum, String[] input) 
	{
		this.optNum = optNum;
		thread = new Thread(this);
		this.optOldName = input[2];
		this.optNewName = input[3];
		this.optSetName = input[1];
		this.modelName = input[0];
	}
	
	/*
	 * run: run the task based on optNum in the thread
	 */
	public void run()
	{
		opt();
	}
	
	/*
	 * opt: run the task based on optNum
	 */
	public void opt()
	{
		switch(optNum)
		{
		case opt_editOptionName:
			updateOptionName();
			break;
		case opt_editOptionName_nonSyncrhonized:
			updateOptionName_nonSynchronized();
			break;
		default:
			System.out.println("the option does not exist!");
		}
	}
	
	/*
	 * updateOptionName: update the option name of the given option in the given option set and model
	 */
	public void updateOptionName()
	{
		synchronized (getAutoMap()) 
		{
			while (!isAvailableForEditing)
			{
				try {
					getAutoMap().wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			isAvailableForEditing = false;
			
			System.out.println("\nstarting to update the option in thread NO: " + Thread.currentThread().getId());
			if (getAutoMap().containsKey(modelName)) // if the model exists
				getAutoMap().get(modelName).setOptionName(optOldName, optSetName, optNewName);
			else
				System.out.println("model does not exist\n");
			System.out.println("\nfinished to update the option in thread NO: " + Thread.currentThread().getId());
		
			isAvailableForEditing = true;
			getAutoMap().notifyAll();
		}
	}
	
	public void updateOptionName_nonSynchronized()
	{
		System.out.println("\nstarting to update the option in thread NO: " + Thread.currentThread().getId());
		if (getAutoMap().containsKey(modelName)) // if the model exists
			getAutoMap().get(modelName).setOptionName(optOldName, optSetName, optNewName);
		else
			System.out.println("model does not exist\n");
		System.out.println("\nfinished to update the option in thread NO: " + Thread.currentThread().getId());
	}
	
	
	
	/*
	 * start: run the thread
	 */
	public void start()
	{
		thread.start();
	}

}
