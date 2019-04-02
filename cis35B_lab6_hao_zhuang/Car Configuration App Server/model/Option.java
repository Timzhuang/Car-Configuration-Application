package model;

import java.io.Serializable;

class Option implements Serializable {
	// attributes:
	private String name;
	private double price;
	private static final long serialVersionUID = 1L;
	
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