package com.nagarro.pos.dao;

import java.util.List;

import com.nagarro.pos.model.CartProducts;
import com.nagarro.pos.model.OrderedProduct;
import com.nagarro.pos.model.Orders;

/**
 * it will interact with database and handle query related to {@link Orders}
 * 
 * @author nishantgarg
 *
 */
public interface OrderRepository {

	/**
	 * This will return the all orders by employee id
	 * 
	 * @param id
	 * @return {@link List <{@link Orders}>
	 */
	public List<Orders> findAllOrders(long id);

	/**
	 * this will save order into the {@link Orders} table
	 * 
	 * @param orders
	 * @return
	 */
	public Orders saveOrder(Orders orders);

	/**
	 * this will insert the product into {@link OrderedProduct}
	 * 
	 * @param orderedProduct
	 */
	public void insertOrderedProduct(OrderedProduct orderedProduct);

	/**
	 * this will delete the save order form the table on the basis of oder id;
	 * 
	 * @param id
	 */
	public void deleteSavedOrder(long id);
}
