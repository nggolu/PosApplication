package com.nagarro.pos.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.pos.constant.ConfigurationConstant;
import com.nagarro.pos.model.Cart;
import com.nagarro.pos.model.CartProducts;
import com.nagarro.pos.model.Customer;
import com.nagarro.pos.service.CartProductsService;

/**
 * It will invoke when '/api/cart' has been call and it will query related to
 * cart
 * 
 * @author nishantgarg
 *
 */
@RequestMapping("/api/cart")
@CrossOrigin(origins = ConfigurationConstant.CORS)
@RestController
public class CartRestController {

	@Autowired
	private CartProductsService cartProductsService;

	/**
	 * It will return the all the cart by customer Id
	 * 
	 * @param id
	 * @return {@link List <{@link CartProducts}>
	 */
	@GetMapping("/")
	public List<CartProducts> findCartByCustomerId(@RequestParam long id) {
		return cartProductsService.findCarts(id);
	}

	/**
	 * It will post the product into the cart
	 * 
	 * @param customer
	 * @param id
	 * @return {@link Cart}
	 */
	@PostMapping("/")
	public Cart addToCart(@RequestBody Customer customer, @RequestParam long id) {
		return cartProductsService.addToCart(customer.getId(), id);
	}

	/**
	 * It will change the quantity of the product in the cart
	 * 
	 * @param cartProducts
	 * @param quantity
	 * @return {@link Cart}
	 */
	@PostMapping("/changeQuantity")
	public Cart updateQuantity(@RequestBody CartProducts cartProducts, @RequestParam int quantity) {
		cartProductsService.updateQuantity(cartProducts.getId(), quantity);
		return null;
	}

	/**
	 * It will remove the entire product from the cart
	 * 
	 * @param id
	 * @return {@link Cart}
	 */
	@DeleteMapping("/")
	public Cart removeCartProduct(@RequestParam long id) {

		cartProductsService.removeCartProductById(id);
		return null;
	}

	/**
	 * It will delete the entire cart from the cart table
	 * 
	 * @param id
	 * @return {@link Cart}
	 */
	@DeleteMapping("/deleteCart")
	public Cart deleteCart(@RequestParam long id) {
		cartProductsService.deleteCart(id);
		return null;
	}
}
