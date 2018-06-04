package com.nagarro.pos.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * POJO for cartProduct
 * 
 * @author nishantgarg
 *
 */
@Entity
public class CartProducts {

	@ManyToOne(cascade = CascadeType.ALL)
	private Cart cart;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(cascade = CascadeType.ALL)
	private Products products;

	@Column(nullable = false, columnDefinition = "int default 0")
	private int quantity;

	/**
	 * 
	 */
	public CartProducts() {
	}

	/**
	 * @param cart
	 * @param products
	 * @param quantity
	 */
	public CartProducts(Cart cart, Products products, int quantity) {
		this.cart = cart;
		this.products = products;
		this.quantity = quantity;
	}

	/**
	 * @param id
	 */
	public CartProducts(long id) {
		this.id = id;
	}

	/**
	 * @param id
	 * @param cart
	 * @param products
	 * @param quantity
	 */
	public CartProducts(long id, Cart cart, Products products, int quantity) {
		this.id = id;
		this.cart = cart;
		this.products = products;
		this.quantity = quantity;
	}

	/**
	 * @return the cart
	 */
	public Cart getCart() {
		return cart;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
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
	 * @param cart
	 *            the cart to set
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
