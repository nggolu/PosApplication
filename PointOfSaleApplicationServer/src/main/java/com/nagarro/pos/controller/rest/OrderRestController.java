package com.nagarro.pos.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.pos.constant.ConfigurationConstant;
import com.nagarro.pos.model.CartProducts;
import com.nagarro.pos.model.EmployeeCashDrawer;
import com.nagarro.pos.model.OrderedProduct;
import com.nagarro.pos.model.Orders;
import com.nagarro.pos.model.ResponseMessage;
import com.nagarro.pos.service.OrderService;
import com.nagarro.pos.service.OrderedProductService;
import com.nagarro.pos.service.VerificationService;

/**
 * it will invoke when '/api/orders' is called and it will control query related
 * to orders
 * 
 * @author nishantgarg
 *
 */
@CrossOrigin(origins = ConfigurationConstant.CORS)
@RequestMapping("/api/orders")
@RestController
public class OrderRestController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderedProductService orderedProductService;

	@Autowired
	private VerificationService verificationService;

	/**
	 * It will give the list of {@link OrderedProduct} after confirming the user
	 * 
	 * @param id
	 * @param httpServletRequest
	 * @return {@link List <{@link OrderedProduct}>
	 */
	@GetMapping("/")
	public List<OrderedProduct> getOrderDetailsByOrderId(@RequestParam long id, HttpServletRequest httpServletRequest) {

		// verifying the user
		String token = httpServletRequest.getHeader("token");
		if (token == null || verificationService.verifyingEmployee(token) == null) {
			return null;
		}
		// after verification product details returned
		EmployeeCashDrawer employeeCashDrawer = verificationService.verifyingEmployee(token);
		return orderedProductService.getOrderDetailsByOrderId(id, employeeCashDrawer.getEmployees());
	}

	/**
	 * It will return all the orders on the Basis of user logged in
	 * 
	 * @param httpServletRequest
	 * @return {@link List <{@link Orders}>
	 */
	@GetMapping("/getOrders")
	public List<Orders> getAllOrders(HttpServletRequest httpServletRequest) {

		// verifying the user
		String token = httpServletRequest.getHeader("token");
		if (token == null || verificationService.verifyingEmployee(token) == null) {
			return null;
		}

		// if user is verified then returned the ordered list
		EmployeeCashDrawer employeeCashDrawer = verificationService.verifyingEmployee(token);
		return orderService.getAllOrders(employeeCashDrawer.getEmployees().getId());
	}

	/**
	 * It will save the Order on the basis of user logged in
	 * 
	 * @param cartProducts
	 * @param httpServletRequest
	 * @return {@link ResponseMessage}
	 */
	@PostMapping("/saveOrder")
	public ResponseMessage saveOrdersById(@RequestBody List<CartProducts> cartProducts,
			HttpServletRequest httpServletRequest) {
		ResponseMessage res = new ResponseMessage();

		// verifying the user
		String token = httpServletRequest.getHeader("token");
		if (token == null || verificationService.verifyingEmployee(token) == null) {
			res.setErrorMessage("invalid User");
			res.setStatus(401);
			return res;
		}

		// if user is verified ordered has been placed
		EmployeeCashDrawer employeeCashDrawer = verificationService.verifyingEmployee(token);
		orderService.saveOrder(cartProducts, employeeCashDrawer.getEmployees());
		res.setStatus(200);
		res.setSuccessMessage("Order Has been saved");
		return res;
	}

	/**
	 * It will reload the saved Order into Cart
	 * 
	 * @param orderedProducts
	 */
	@PostMapping("/orderToCart")
	public void saveOrderToCart(@RequestBody List<OrderedProduct> orderedProducts) {
		orderedProductService.saveOrderToCart(orderedProducts);
		// orderService.saveOrder(cartProducts);
	}

	/**
	 * It will return all the saved Order on the basis of user logged in and if user
	 * wrong then it will return null
	 * 
	 * @param httpServletRequest
	 * @return {@link List <{@link OrderedProduct}>
	 */
	@GetMapping("/saveOrder")
	public List<OrderedProduct> getSaveOrdersById(HttpServletRequest httpServletRequest) {

		// verifying user
		String token = httpServletRequest.getHeader("token");
		if (token == null || verificationService.verifyingEmployee(token) == null) {
			return null;
		}

		// if user if verified then order is save
		EmployeeCashDrawer employeeCashDrawer = verificationService.verifyingEmployee(token);
		return orderedProductService.getSaveOrdersById(employeeCashDrawer.getEmployees());
	}

	/**
	 * It will save the place order after verifyiong the user
	 * 
	 * @param cartProducts
	 * @param modeOfPayment
	 * @param httpServletRequest
	 * @return {@link ResponseMessage}
	 */
	@PostMapping("/placeOrder")
	public ResponseMessage placeOrdersById(@RequestBody List<CartProducts> cartProducts,
			@RequestParam String modeOfPayment, HttpServletRequest httpServletRequest) {

		// verifying the user
		ResponseMessage res = new ResponseMessage();
		String token = httpServletRequest.getHeader("token");
		if (token == null || verificationService.verifyingEmployee(token) == null) {
			return null;
		}

		// if user is verified place the order
		EmployeeCashDrawer employeeCashDrawer = verificationService.verifyingEmployee(token);
		String response = orderService.placeOrder(cartProducts, employeeCashDrawer.getId(), modeOfPayment,
				employeeCashDrawer.getEmployees());

		// if stock is less than the required quantity then it wont place order
		if (response != null) {
			res.setErrorMessage(response);
			res.setStatus(403);
		} else {
			res.setSuccessMessage(" order has been placed");
			res.setStatus(200);
		}
		return res;
	}
}
