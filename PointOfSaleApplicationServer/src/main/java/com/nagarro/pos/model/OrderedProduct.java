package com.nagarro.pos.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * POJO of {@link OrderedProduct}
 * @author nishantgarg
 *
 */
@Entity
public class OrderedProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(cascade = CascadeType.ALL)
	private Orders orders;

	@OneToOne(cascade = CascadeType.ALL)
	private Products products;
	/**
	 * @param orders
	 * @param products
	 * @param quantity
	 */
	public OrderedProduct(Orders orders, Products products, int quantity) {
		this.orders = orders;
		this.products = products;
		this.quantity = quantity;
	}

	private int quantity;

	/**
	 * 
	 */
	public OrderedProduct() {
	}

	/**
	 * @param id
	 * @param products
	 * @param orderId
	 * @param quantity
	 */
	public OrderedProduct(long id, Products products, Orders orders, int quantity) {
		this.id = id;
		this.products = products;
		this.orders = orders;
		this.quantity = quantity;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the orderId
	 */
	public Orders getOrderId() {
		return orders;
	}

	/**
	 * @return the products
	 */
	public Products getProducts() {
		return products;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(Orders orders) {
		this.orders = orders;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(Products products) {
		this.products = products;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
