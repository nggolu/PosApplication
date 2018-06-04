package com.nagarro.pos.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * POJO for cart
 * 
 * @author nishantgarg
 *
 */
@Entity
public class Cart {

	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * 
	 */
	public Cart() {
	}

	/**
	 * @param id
	 * @param customer
	 */
	public Cart(long id, Customer customer) {
		this.id = id;
		this.customer = customer;
	}

	/**
	 * @param customer
	 */
	public Cart(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

}
