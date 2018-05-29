package com.nagarro.pos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * POJO of {@link Products}
 * 
 * @author nishantgarg
 *
 */
@Entity
public class Products {

	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	private double price;

	private int stock;

	/**
	 * 
	 */
	public Products() {
	}

	/**
	 * @param id
	 * @param name
	 * @param price
	 * @param quantity
	 * @param description
	 */
	public Products(long id, String name, double price, int quantity, String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = quantity;
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
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
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @return the quantity
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setStock(int quantity) {
		this.stock = quantity;
	}

}
