package com.nagarro.pos.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.pos.constant.ConfigurationConstant;
import com.nagarro.pos.model.Customer;
import com.nagarro.pos.service.CustomerService;

/**
 * It will invoke when '/api/customer' has been call and it will control query
 * related to customer
 * 
 * @author nishantgarg
 *
 */
@CrossOrigin(origins = ConfigurationConstant.CORS)
@RequestMapping("/api/customer")
@RestController
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;


	/**
	 * It create the new Customer
	 * 
	 * @param customer
	 * @return {@link Customer}
	 */
	@PostMapping("/")
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}

	/**
	 * It will return all the customer on the basis of filter
	 * 
	 * @param filter
	 * @param httpServletRequest
	 * @return {@link List <{@link Customer}>
	 */
	@GetMapping("/")
	public List<Customer> getCustomerByFilter(@RequestParam String filter, HttpServletRequest httpServletRequest) {
		// System.out.println(customerService.getCustomerByFilter(filter).size());
		return customerService.getCustomerByFilter(filter);
	}

}
