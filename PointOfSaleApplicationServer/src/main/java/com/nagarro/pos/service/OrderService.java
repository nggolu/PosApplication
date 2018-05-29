package com.nagarro.pos.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.pos.controller.rest.OrderRestController;
import com.nagarro.pos.dao.OrderRepository;
import com.nagarro.pos.dao.impl.CartRepositoryImplement;
import com.nagarro.pos.dao.impl.EmployeeCashDrawerRepositoryImplment;
import com.nagarro.pos.dao.impl.ProductRepositoryImplement;
import com.nagarro.pos.model.CartProducts;
import com.nagarro.pos.model.Employee;
import com.nagarro.pos.model.EmployeeCashDrawer;
import com.nagarro.pos.model.OrderedProduct;
import com.nagarro.pos.model.Orders;
import com.nagarro.pos.model.Products;

/**
 * Handle all the request related to orders which is coming from
 * {@link OrderRestController}
 * 
 * @author nishantgarg
 *
 */
@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartRepositoryImplement cartRepositoryImplement;

	@Autowired
	private ProductRepositoryImplement productRepositoryImplement;

	@Autowired
	private EmployeeCashDrawerRepositoryImplment employeeCashDrawerRepositoryImplment;

	/**
	 * Get all the Orders By employee id
	 * 
	 * @param id
	 * @return {@link List <{@link Orders}>
	 */
	@Transactional
	public List<Orders> getAllOrders(long id) {
		return orderRepository.findAllOrders(id);
	}

	/**
	 * It will save the saved order
	 * 
	 * @param cartProducts
	 * @param employee
	 */
	@Transactional
	public void saveOrder(List<CartProducts> cartProducts, Employee employee) {

		if (cartProducts.size() > 0) {

			// delete the cartProduct by its id
			cartRepositoryImplement.deleteCart(cartProducts.get(0).getCart().getId());

			// save oder into Orders table
			Orders orders = orderRepository.saveOrder(
					new Orders(cartProducts.get(0).getCart().getCustomer(), new Date(), employee, "pending"));

			// insert cartProduct into OrderedProduct
			for (int i = 0; i < cartProducts.size(); i++) {
				orderRepository.insertOrderedProduct(new OrderedProduct(orders, cartProducts.get(i).getProducts(),
						cartProducts.get(i).getQuantity()));
			}
		}
	}

	/**
	 * Its will place order 
	 * 1) It check the orderedPreoduct requirement is less than
	 * stock 
	 * 2) it will delete the cart and CartProduct 
	 * 3) Create the order
	 * 4) Insert Products into OrderedProduct 
	 * 5) Decrease the quantity from stock
	 * 6) Check the payment method is cash then update the EmployeeCashDrawer
	 * 7) Update the employee Cash drawer
	 * 
	 * @param cartProducts
	 * @param cashDrawerId
	 * @param modeOfPayment
	 * @param employee
	 * @return
	 */
	public String placeOrder(List<CartProducts> cartProducts, long cashDrawerId, String modeOfPayment,
			Employee employee) {
		List<Products> products = new ArrayList<>();
		for (int i = 0; i < cartProducts.size(); i++) {
			int requiredQuantity = cartProducts.get(i).getQuantity();
			Products product = productRepositoryImplement.getProductById(cartProducts.get(i).getProducts().getId());
			int actualQuantity = product.getStock();
			products.add(product);

			//if product stock  is less tha required quantity --cancel the placed ordered
			if (requiredQuantity > actualQuantity) {
				return cartProducts.get(i).getProducts().getName() + "'s quantity should be less than "
						+ actualQuantity;
			}
		}

		if (cartProducts.size() > 0)
			
			//delete he cartProducts
			cartRepositoryImplement.deleteCart(cartProducts.get(0).getCart().getId());
		
		// initialize total price to 10 because of tax
		int totalPrice = 10;
		
		//create new order
		Orders orders = orderRepository.saveOrder(new Orders(cartProducts.get(0).getCart().getCustomer(), new Date(),
				employee, modeOfPayment, "complete"));
		
		for (int i = 0; i < cartProducts.size(); i++) {
			
			//insert products into OrderedProduct table
			orderRepository.insertOrderedProduct(
					new OrderedProduct(orders, cartProducts.get(i).getProducts(), cartProducts.get(i).getQuantity()));
			totalPrice += cartProducts.get(i).getQuantity() * cartProducts.get(i).getProducts().getPrice();
			
			//update the products
			products.get(i).setStock(products.get(i).getStock() - cartProducts.get(i).getQuantity());
			productRepositoryImplement.updateProductById(products.get(i));
		}
		
		//get EmployeeCashDrawer by cashDrawerId and update it
		EmployeeCashDrawer employeeCashDrawer = employeeCashDrawerRepositoryImplment.getCashDrawerById(cashDrawerId);
		
		//if modeOfPayment cash then only update the endCash
		if ("cash".equals(modeOfPayment))
			employeeCashDrawer.setEndCash(employeeCashDrawer.getEndCash() + totalPrice);
		
		employeeCashDrawer.setEndTime(new Date());
		employeeCashDrawerRepositoryImplment.updateCashDrawerOfEmployee(employeeCashDrawer);
		return null;

	}
}
