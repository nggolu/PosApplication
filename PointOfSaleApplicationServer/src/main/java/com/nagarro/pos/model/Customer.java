package com.nagarro.pos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * POJO for customer
 * @author nishantgarg
 *
 */
@Entity
public class Customer {

	private String email;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String phoneNumber;

	/**
	 * @param id
	 * @param phoneNumber
	 * @param name
	 * @param email
	 */
	public Customer(long id, String phoneNumber, String name, String email) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.email = email;
	}
	
	/**
	 * @param id
	 */
	public Customer(long id) {
		this.id = id;
	}
	
	public Customer() {
		
	}

	/**
	 * @param phoneNumber
	 * @param name
	 * @param email
	 */
	public Customer(String phoneNumber, String name, String email) {
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.email = email;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
