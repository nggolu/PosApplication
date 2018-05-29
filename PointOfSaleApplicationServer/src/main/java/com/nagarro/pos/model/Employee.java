package com.nagarro.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * POJO for employee
 * 
 * @author nishantgarg
 *
 */
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String password;
	@Column(unique = true, nullable = false)
	private String username;

	/**
	 * @param id
	 */
	public Employee(long id) {
		this.id = id;
	}

	/**
	 * 
	 */
	public Employee() {
	}

	/**
	 * @param id
	 * @param username
	 * @param password
	 */
	public Employee(long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	/**
	 * @param username
	 * @param password
	 */
	public Employee(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String toString () {
		return this.getPassword()+this.getUsername();
	}

}
