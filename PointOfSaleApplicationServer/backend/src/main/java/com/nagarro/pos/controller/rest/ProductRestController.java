package com.nagarro.pos.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.pos.constant.ConfigurationConstant;
import com.nagarro.pos.model.Products;
import com.nagarro.pos.service.ProductService;

/**
 * It will invoke when '/api/products' has been call and it will control the
 * query related to products
 * 
 * @author nishantgarg
 *
 */
@RequestMapping("/api/products")
@CrossOrigin(origins = ConfigurationConstant.CORS)
@RestController
public class ProductRestController {

	@Autowired
	private ProductService productService;

	/**
	 * It will return list of products
	 * 
	 * @return {@link List <{@link Products}>
	 */
	@GetMapping("/")
	public List<Products> getAllProducts() {
		return productService.getAllProducts();
	}

	/**
	 * It will return list of products on the basis of filter
	 * 
	 * @param filter
	 * @return  {@link List <{@link Products}>
	 */
	@GetMapping("/filter")
	public List<Products> getAllProductsByFilter(@RequestParam String filter) {
		return productService.getAllProductsByFilter(filter);
	}
}
