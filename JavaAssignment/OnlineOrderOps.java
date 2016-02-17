package org.bts_netmind.javaproject;

import java.util.List;

// 'OnlineOrderOps' is an interface designed by the system administrator. The aim is to indicate the 
// developer (you, in this case) in which way the on-line order data should be accessed/treated/employed
// within the application. 
// This interface MUST be implemented by at least one class of your code.
public interface OnlineOrderOps 
{
	// 'getNumberOrders' simply retrieves the number or orders from the List input argument
	public int getNumberOrders(List<Object> orderList);
	// 'getOrder' returns an Object which represents one single order. It takes an index 
	// (referring to a List, for instance) as input argument
	public Object getOrder(List<Object> orderList, int orderIndex);
	// 'getAllOrdersToString' retrieves a String describing every order included in a List used as
	// input argument
	public String getAllOrdersToString(List<Object> orderList);	
	// 'getDish' returns an Object representing one singular dish. It takes an index (referring to a
	// List, for instance) as input argument
	public Object getDish(List<Object> dishList, int dishIndex);
	// 'getAllDishToString' retrieves a String outlining all the dish names included in a List.
	public String getAllDishToString(List<Object> dishList);
	// 'getDishesByType' retrieves a List of Object corresponding to those dishes which match the
	// type 'dishType' (st, mc, or ds) used as input argument
	public List<Object> getDishesByType(List<Object> dishList, String dishType);
	// 'getDishesByFeature' returns a List of Object including those dishes which belong to a category
	// defined by a specific feature taken as input argument (gfd, vgd, hmd, or sfd)
	public List<Object> getDishesByFeature(List<Object> dishList, String feature);
	// 'getStatsByDishType' returns a String with the percentage of dishes ordered that correspond to
	// a specific type (taken as input argument)
	public String getStatsByDishType(List<Object> dishList, String dishType);
}
