package org.bts_netmind.javaproject;

import java.io.File;
import java.util.List;

public class RestaurantMain implements OnlineOrderOps
{	
	public static void main(String[] args) 
	{


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
