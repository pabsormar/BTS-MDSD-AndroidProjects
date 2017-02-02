package org.bts_mdsd.javafoundations;

import java.util.List;

import org.bts_mdsd.javafoundations.model.Dish;

// 'OnlineOrderOps' is an interface designed by the system administrator. The aim is to indicate the 
// developer (you, in this case) in which way the on-line order data should be accessed/treated/employed
// within the application. 
// This interface MUST be implemented by at least one class of your code.
public interface OnlineOrderOps<T, S extends Dish> {
    // 'getNumberOrders' simply retrieves the number or orders from the List input argument
    public int getNumberOrders(List<T> orderList);

    // 'getOrder' returns an object which represents one single order. It takes an index
    // (referring to a List, for instance) as input argument
    public T getOrder(List<T> orderList, int orderIndex);

    // 'getAllOrdersToString' retrieves a String describing every order included in a List used as
    // input argument
    public String getAllOrdersToString(List<T> orderList);

    // 'getDish' returns an object representing one singular dish. It takes an index (referring to a
    // List, for instance) as input argument
    public S getDish(List<S> dishList, int dishIndex);

    // 'getAllDishToString' retrieves a String outlining all the dish names included in a List.
    public String getAllDishToString(List<S> dishList);

    // 'getDishesByType' retrieves a List of Dish-related objects corresponding to those dishes which match the
    // type 'dishType' (st, mc, or ds) used as input argument
    public List<S> getDishesByType(List<S> dishList, String dishType);

    // 'getDishesByFeature' returns a List of Dish-related objects including those dishes which belong to a category
    // defined by a specific feature taken as input argument (gfd, vgd, hmd, or sfd)
    public List<S> getDishesByFeature(List<S> dishList, String feature);

    // 'getStatsByDishType' returns a String with the percentage of dishes ordered that correspond to
    // a specific type (taken as input argument)
    public String getStatsByDishType(List<S> dishList, String dishType);
}
