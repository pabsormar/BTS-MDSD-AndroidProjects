package org.bts_netmind.javaproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class RestaurantMain implements OnlineOrderOps
{	
	public static void main(String[] args) 
	{
		
		
		
		// Reading from a file
		String aLine;
		try 
		{
			final File myFile = new File("./onlineOrderSample.txt");
			final FileInputStream myFInStream = new FileInputStream(myFile);     // throws 'FileNotFoundException'
			final InputStreamReader myInStrReader = new InputStreamReader(myFInStream);
			final BufferedReader myBuffReader = new BufferedReader(myInStrReader);

			while ((aLine = myBuffReader.readLine()) != null)
				System.out.println(aLine);
			
			myFInStream.close();
		}
		catch (IOException e) { e.printStackTrace(); }
	
		// Reading the same file in a different way
		String mLine;
		try 
		{
			final File myFile = new File("./onlineOrderSample.txt");
			final FileReader myFReader = new FileReader(myFile);
			final BufferedReader myBuffReader = new BufferedReader(myFReader);
			
			while ((mLine = myBuffReader.readLine()) != null)
				System.out.println(mLine);
			
			myBuffReader.close();
		} 
		catch (IOException e) { e.printStackTrace(); }			
	}

	public static String readFile(File f)
	{
		return null;
	}
	
	@Override
	public Object getOrder(List<Object> orderList, int orderIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllOrdersToString(List<Object> orderList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDish(List<Object> dishList, int dishIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllDishToString(List<Object> dishList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getDishesByType(List<Object> dishList, String dishType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getDishesByFeature(List<Object> dishList, String feature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStatsByDishType(List<Object> dishList, String dishType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOrders(List<Object> orderList) {
		// TODO Auto-generated method stub
		return 0;
	}
}
