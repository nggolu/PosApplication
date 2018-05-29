package com.nagarro.pos.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nagarro.pos.model.Cart;
import com.nagarro.pos.model.CartProducts;
import com.nagarro.pos.model.Customer;
import com.nagarro.pos.model.Products;

/**
 * It will interact with database related to {@link Cart} and
 * {@link CartProducts}
 * 
 * @author nishantgarg
 *
 */
@Repository
public interface CartRepository {

	/**
	 * It will return all the cartProducts List from the database on the basis of
	 * customer
	 * 
	 * @param customer
	 * @return {@link List <{@link CartProducts}>
	 */
	public List<CartProducts> findAllByCartByCustomer(Customer customer);

	/**
	 * it will create the cart int the cart table
	 * 
	 * @param customer
	 * @return {@link Cart}
	 */
	public Cart addProductToCart(Customer customer);

	/**
	 * It will return cart by customer form table
	 * 
	 * @param customer
	 * @return {@link Cart}
	 */
	public Cart getCartByCustomer(Customer customer);

	/**
	 * add product into the {@link CartProducts}
	 * 
	 * @param cart
	 * @param products
	 * @return {@link Cart}
	 */
	public Cart addCartInCartProduct(Cart cart, Products products);

	/**
	 * Remove {@link CartProducts} by {@link CartProducts}'s id
	 * 
	 * @param id
	 * @return
	 */
	public Cart removeCartProductById(long id);

	/**
	 * Get {@link CartProducts} by {@link Cart} and {@link Products}
	 * 
	 * @param cart
	 * @param products
	 * @return {@link CartProducts}
	 */
	public CartProducts getCartProductByCartIdAndProductId(Cart cart, Products products);

	/**
	 * Delete {@link CartProducts} by cart id
	 * 
	 * @param id
	 * @return {@link Integer}
	 */
	public int deleteCart(long id);

	/**
	 * update quantity into cart
	 * 
	 * @param cartProductId
	 * @param quantity
	 * @return {@link Integer}
	 */
	public int updateQuantity(long cartProductId, int quantity);

	/**
	 * insert Product into {@link CartProducts}
	 * 
	 * @param cartProducts
	 */
	public void insertProductTocart(CartProducts cartProducts);
}
