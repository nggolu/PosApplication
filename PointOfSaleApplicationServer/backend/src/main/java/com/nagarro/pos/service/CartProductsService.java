package com.nagarro.pos.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.pos.controller.rest.CartRestController;
import com.nagarro.pos.dao.ProductRepository;
import com.nagarro.pos.dao.impl.CartRepositoryImplement;
import com.nagarro.pos.dao.impl.CustomerRepositoryImplement;
import com.nagarro.pos.model.Cart;
import com.nagarro.pos.model.CartProducts;
import com.nagarro.pos.model.Customer;
import com.nagarro.pos.model.Products;

/**
 * it will handle all the query related to {@link CartProducts} coming from
 * {@link CartRestController}
 * 
 * @author nishantgarg
 *
 */
@Service
public class CartProductsService {

	@Autowired
	private CartRepositoryImplement cartRepositoryImplement;

	@Autowired
	private CustomerRepositoryImplement customerRepositoryImplement;

	@Autowired
	private ProductRepository productRepository;

	/**
	 * return all the {@link CartProducts} based on customer id;
	 * 
	 * @param id
	 * @return {@link List <{@link CartProducts}>
	 */
	@Transactional
	public List<CartProducts> findCarts(long id) {
		Customer customer = customerRepositoryImplement.getCoustomerById(id);
		return cartRepositoryImplement.findAllByCartByCustomer(customer);
	}

	/**
	 * This will add the product to cart
	 * 
	 * @param customerId
	 * @param productId
	 * @return {@link Cart}
	 */
	@Transactional
	public Cart addToCart(long customerId, long productId) {

		// find the customer and product by their id's
		Customer customer = customerRepositoryImplement.getCoustomerById(customerId);
		Products product = productRepository.getProductById(productId);

		// get cart by customer id and if it is null then create the cart
		Cart cart = cartRepositoryImplement.getCartByCustomer(customer);
		if (cart == null) {
			cart = cartRepositoryImplement.addProductToCart(customer);
		}

		// if getCartProduct from db and if required quantity is less then stock then
		// add to cart
		CartProducts cartProducts = cartRepositoryImplement.getCartProductByCartIdAndProductId(cart, product);
		if (cartProducts == null || (cartProducts != null && cartProducts.getQuantity() < product.getStock()))
			cartRepositoryImplement.addCartInCartProduct(cart, product);
		
		return null;
	}

	/**
	 * remove the carProduct by cart id
	 * @param id
	 * @return {@link Cart}
	 */
	@Transactional
	public Cart removeCartProductById(long id) {
		return cartRepositoryImplement.removeCartProductById(id);
	}

	/**
	 * Delete cart by its id
	 * @param id
	 * @return {@link String}
	 */ 
	@Transactional
	public String deleteCart(long id) {
		int deletedRow = cartRepositoryImplement.deleteCart(id);
		return deletedRow + "row is deleted";
	}

	/**
	 * Update the quantity of the cartProduct's quantity
	 * @param cartProductId
	 * @param quantity
	 * @return {@link String}
	 */
	@Transactional
	public String updateQuantity(long cartProductId, int quantity) {
		int updatedRow = cartRepositoryImplement.updateQuantity(cartProductId, quantity);
		return updatedRow + " is updated";
	}
}
