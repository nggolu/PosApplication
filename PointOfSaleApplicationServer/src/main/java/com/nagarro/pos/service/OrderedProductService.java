package com.nagarro.pos.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.pos.controller.rest.OrderRestController;
import com.nagarro.pos.dao.impl.CartRepositoryImplement;
import com.nagarro.pos.dao.impl.OrderRepositoryImplement;
import com.nagarro.pos.dao.impl.OrderedProductRespositoryImplement;
import com.nagarro.pos.model.Cart;
import com.nagarro.pos.model.CartProducts;
import com.nagarro.pos.model.Employee;
import com.nagarro.pos.model.OrderedProduct;

/**
 * it will handle all the request related to {@link OrderedProduct} which is
 * coming from {@link OrderRestController}
 * 
 * @author nishantgarg
 *
 */
@Service
public class OrderedProductService {

	@Autowired
	private OrderedProductRespositoryImplement orderedProductRespositoryImplement;

	@Autowired
	private OrderRepositoryImplement orderRepositoryImplement;

	@Autowired
	private CartRepositoryImplement cartRepositoryImplement;

	/**
	 * Get the list of OrderDetails by employe id
	 * 
	 * @param id
	 * @param employee
	 * @return {@link List <{@link OrderedProduct}>
	 */
	@Transactional
	public List<OrderedProduct> getOrderDetailsByOrderId(long id, Employee employee) {
		return orderedProductRespositoryImplement.getOrderDetailsByOrderId(id, employee);
	}

	/**
	 * Get all the saved orders by employee
	 * 
	 * @param employee
	 * @return {@link List <{@link OrderedProduct}>
	 */
	@Transactional
	public List<OrderedProduct> getSaveOrdersById(Employee employee) {

		return orderedProductRespositoryImplement.getSaveOrdersById(employee);
	}

	/**
	 * save the oder To cart
	 * 
	 * @param orderedProducts
	 */
	@Transactional
	public void saveOrderToCart(List<OrderedProduct> orderedProducts) {

		// deleting row from orderDetails by its id
		for (int i = 0; i < orderedProducts.size(); i++) {
			orderedProductRespositoryImplement.deleteSavedProductOrder(orderedProducts.get(i).getId());
		}

		
		if (orderedProducts.size() > 0) {
			
			//delete order By OrdersId
			orderRepositoryImplement.deleteSavedOrder(orderedProducts.get(0).getOrderId().getId());

			//find cart by customer and if cart is not present then create new cart
			Cart cart = cartRepositoryImplement.getCartByCustomer(orderedProducts.get(0).getOrderId().getCustomer());
			if (cart == null) {
				cart = cartRepositoryImplement.addProductToCart(orderedProducts.get(0).getOrderId().getCustomer());
			}
			
			//insert all product into cart 
			for (int i = 0; i < orderedProducts.size(); i++) {
				cartRepositoryImplement.insertProductTocart(new CartProducts(cart, orderedProducts.get(i).getProducts(),
						orderedProducts.get(i).getQuantity()));
			}
		}

	}

}
