package com.nagarro.pos.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nagarro.pos.model.Customer;

/**
 * it will interact with database and handle query related to {@link Customer}
 * 
 * @author nishantgarg
 *
 */
@Repository
public interface CustomerRepository {

	/**
	 * it will return list of customers
	 * 
	 * @param filter
	 * @return {@link List <{@link Customer}>
	 */
	public List<Customer> getCustomerByFilter(String filter);

	/**
	 * it will add customer to the customer table
	 * 
	 * @param customer
	 * @return {@link Customer}
	 */
	public Customer addCustomer(Customer customer);

	/**
	 * it will return customer on the basis of customer id
	 * 
	 * @param id
	 * @return {@link Customer}
	 */
	public Customer getCoustomerById(long id);

}
