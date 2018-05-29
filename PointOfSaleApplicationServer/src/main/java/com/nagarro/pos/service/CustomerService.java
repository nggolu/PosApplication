package com.nagarro.pos.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.pos.controller.rest.CustomerRestController;
import com.nagarro.pos.dao.impl.CustomerRepositoryImplement;
import com.nagarro.pos.model.Customer;

/**
 * It will handle all the query related to {@link Customer} which is coming from
 * {@link CustomerRestController}
 * 
 * @author nishantgarg
 *
 */
@Service
public class CustomerService {

	@Autowired
	private CustomerRepositoryImplement customerRepositoryImplement;

	/**
	 * It will get all the customer by filter
	 * 
	 * @param filter
	 * @return {@link List <{@link Customer}>
	 */
	@Transactional
	public List<Customer> getCustomerByFilter(String filter) {
		return customerRepositoryImplement.getCustomerByFilter(filter);
	}

	/**
	 * It will add the customer to the database
	 * 
	 * @param customer
	 * @return {@link List <{@link Customer}>
	 */
	@Transactional
	public Customer addCustomer(Customer customer) {
		return customerRepositoryImplement.addCustomer(customer);
	}
}
