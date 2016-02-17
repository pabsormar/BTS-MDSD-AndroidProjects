package org.deafsapps.javadevfoundations.mainpackage;

import org.deafsapps.javadevfoundations.asidepackage.TaxPayer;

public class Employee implements TaxPayer
{
	private String name;
	private String lastName;
	private String position;
	private int department;
	private float salary;
	private boolean hasCarparkPass;

	public String getName() { return this.name; }
	public void setName(String mName) { this.name = mName; }
	
	public String getLastName() { return this.lastName; }
	public void setLastName(String mLastName) { this.lastName = mLastName; }
	
	public String getPosition() { return this.position; }
	public void setPosition(String mPosition) { this.position = mPosition; }
	
	public int getDepartment() { return this.department; }
	public void setDepartment(int mDepartment) { this.department = mDepartment; }
	
	public float getSalary() { return this.salary; }
	public void setSalary(float mSalary) { this.salary = mSalary; }
	
	public boolean isHasCarparkPass() { return this.hasCarparkPass; }
	public void setHasCarparkPass(boolean hasCarparkPass) { this.hasCarparkPass = hasCarparkPass; }
	
	@Override
	public float payTaxes(float income, float pcRetention) 
	{
		
		return 0;
	}	
}