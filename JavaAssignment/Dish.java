package org.bts_netmind.javaproject;

public abstract class Dish 
{
	public Dish() { }
	
	protected String dishName;

	public String getDishName() { return dishName; }
	public void setDishName(String dName) { this.dishName = dName; }
}
