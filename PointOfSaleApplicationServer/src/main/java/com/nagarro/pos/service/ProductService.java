package com.nagarro.pos.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.pos.dao.impl.ProductRepositoryImplement;
import com.nagarro.pos.model.Products;

/**
 * It will handle all the query relate to products coming from product
 * Controller
 * 
 * @author nishantgarg
 *
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepositoryImplement productRepositoryImplement;

	/**
	 * It will written all the product
	 * 
	 * @return {@link List <{@link Products}>
	 */
	@Transactional
	public List<Products> getAllProducts() {
		return productRepositoryImplement.getAllProducts();
	}

	/**
	 * it will written all the products on the basis of filter
	 * 
	 * @param filter
	 * @return  {@link List <{@link Products}>
	 */
	@Transactional
	public List<Products> getAllProductsByFilter(String filter) {
		long number = -1;
		//if filter is number then search by number also
		if (filter != null && filter.matches("\\d+")) {
			number = Long.parseLong(filter);
		}

		return productRepositoryImplement.getAllProductsByFilter(filter, number);
	}
}
