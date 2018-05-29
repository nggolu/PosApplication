package com.nagarro.pos.dao;

import java.util.List;

import com.nagarro.pos.model.Products;

/**
 * it will interact with database and handle query related to {@link Products}
 * 
 * @author nishantgarg
 *
 */
public interface ProductRepository {

	/**
	 * This will return the list of all {@link Products}
	 * 
	 * @return {@link List <{@link Products}>
	 */
	public List<Products> getAllProducts();

	/**
	 * This wil return {@link Products} by product id
	 * 
	 * @param id
	 * @return {@link List <{@link Products}>
	 */
	public Products getProductById(long id);

	/**
	 * This will update product by product id
	 * 
	 * @param products
	 * @return {@link Products}
	 */
	public Products updateProductById(Products products);

	/**
	 * This will get al the products by filers
	 * 
	 * @param filter
	 * @param Number
	 * @return {@link List <{@link Products}>
	 */
	public List<Products> getAllProductsByFilter(String filter, long Number);
}
