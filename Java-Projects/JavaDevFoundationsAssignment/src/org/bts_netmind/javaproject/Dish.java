package org.bts_netmind.javaproject;

public abstract class Dish 
{
	protected String dishName;
	
	public Dish(String anyDishName) { this.dishName = anyDishName; }
	
	public String getDishName() { return dishName; }
	public void setDishName(String dName) { this.dishName = dName; }
	
	@Override
	public String toString() { return this.getDishName(); }
}
