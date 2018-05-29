package com.nagarro.pos.dao;

import java.util.List;

import com.nagarro.pos.model.Employee;
import com.nagarro.pos.model.OrderedProduct;

/**
 * it will interact with database and handle query related to
 * {@link OrderedProduct}
 * 
 * @author nishantgarg
 *
 */
public interface OrderedProductRepository {

	/**
	 * This will get the order's detail on the basis of orderId
	 * 
	 * @param id
	 * @param employee
	 * @return {@link List <{@link OrderedProduct}>
	 */
	public List<OrderedProduct> getOrderDetailsByOrderId(long id, Employee employee);

	/**
	 * This will save the order by employee id
	 * 
	 * @param employee
	 * @return {@link List <{@link OrderedProduct}>
	 */
	public List<OrderedProduct> getSaveOrdersById(Employee employee);

	/**
	 * This will delete the saved Ordered product from the table
	 * 
	 * @param id
	 */
	public void deleteSavedProductOrder(long id);
}
