package org.bts_netmind.javaproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RestaurantMain
{	
	public static void main(String[] args) 
	{
		InterfaceOperator myOperator = new InterfaceOperator();
		String myFilePath = "./onlineOrderSample.txt";
		List<Object> myOrderArray = new ArrayList<>();	
		//private Order[] anotherOrderArray = new Order[5];
				
		ArrayList<String> fileLineArray = readFile(myFilePath);		
		
		Dish aDish;
		for (String anyString : fileLineArray) 
		{
			String[] mStringArray = anyString.split(",");
			
			switch (mStringArray[2])
			{
				case "st":
					aDish = new Starter(mStringArray[1], mStringArray[3].matches("true"), mStringArray[4].matches("true"), 
										mStringArray[5].matches("true"), mStringArray[6].matches("true"), mStringArray[7]);
					break;
					
				case "mc":
					aDish = new MainCourse(mStringArray[1], mStringArray[3].matches("true"), mStringArray[4].matches("true"), 
										   mStringArray[5].matches("true"), mStringArray[6].matches("true"), mStringArray[7]);
					break;
				default:   // Using 'default' to ensure 'aDish' object initialisation
					aDish = new Dessert(mStringArray[1], mStringArray[3].matches("true"), mStringArray[4].matches("true"), 
										mStringArray[5].matches("true"), mStringArray[6].matches("true"), Integer.valueOf(mStringArray[7]));
					break;		
			}
			System.out.println((aDish instanceof Dessert ? ((Dessert) aDish).toString() : ""));
			myOrderArray.add(new Order(mStringArray[0], aDish));
		}		
			
		System.out.println("Number of orders = " + myOperator.getNumberOrders(myOrderArray)); 
		System.out.println(((Order) myOperator.getOrder(myOrderArray, 0)).getaDish().getDishName());		
		
	}

	private static ArrayList<String> readFile(String filePath)
	{		
		String aLine;
		ArrayList<String> tempArrayList = new ArrayList<>();
		
		try 
		{
			final File myFile = new File(filePath);
			final FileInputStream myFInStream = new FileInputStream(myFile);     // throws 'FileNotFoundException'
			final InputStreamReader myInStrReader = new InputStreamReader(myFInStream);
			final BufferedReader myBuffReader = new BufferedReader(myInStrReader);
			
			aLine = myBuffReader.readLine();   // Dismisses the headers
			while ((aLine = myBuffReader.readLine()) != null)
			{
				tempArrayList.add(aLine);
			}
			
			myFInStream.close();
		}
		catch (IOException e) { e.printStackTrace(); }
		
		return tempArrayList;		
	}
}