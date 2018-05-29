package com.nagarro.pos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nagarro.pos.dao.CartRepository;
import com.nagarro.pos.hibernate.util.HibernateUtils;
import com.nagarro.pos.model.Cart;
import com.nagarro.pos.model.CartProducts;
import com.nagarro.pos.model.Customer;
import com.nagarro.pos.model.OrderedProduct;
import com.nagarro.pos.model.Products;

/**
 * 
 * @author nishantgarg
 *
 */
@Repository
public class CartRepositoryImplement implements CartRepository {

	@Override
	public List<CartProducts> findAllByCartByCustomer(Customer customer) {

		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		List<CartProducts> cartproducts = new ArrayList<>();
		// transaction begins
		session.beginTransaction();

		// create the query
		String hql = "FROM CartProducts c where c.cart.customer = :customer";
		Query query = session.createQuery(hql);
		query.setParameter("customer", customer);
		cartproducts = query.list();
		session.getTransaction().commit();

		// close session
		session.close();
		return cartproducts;
	}

	@Override
	public Cart addProductToCart(Customer customer) {

		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();

		// begin transaction
		session.beginTransaction();
		Cart cart = new Cart(customer);
		session.save(cart);
		session.getTransaction().commit();

		// close session
		session.close();
		return cart;
	}

	@Override
	public Cart getCartByCustomer(Customer customer) {
		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		session.beginTransaction();

		// create query
		String hql = "FROM Cart c where c.customer = :customer";
		Query query = session.createQuery(hql);
		List<Cart> cart = new ArrayList<>();
		query.setParameter("customer", customer);
		cart = query.list();

		session.getTransaction().commit();
		session.close();
		// if cart size is one when return value else return null
		if (cart.size() == 1)
			return cart.get(0);

		return null;
	}

	@Override
	public Cart addCartInCartProduct(Cart cart, Products products) {
		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		session.beginTransaction();

		// query query
		String hql = "Update CartProducts c  set  c.quantity = (c.quantity +1) where c.cart = :cart and c.products = :products";
		Query query = session.createQuery(hql);
		query.setParameter("products", products);
		query.setParameter("cart", cart);

		int result = query.executeUpdate();

		// if updated row is 0 then we insert new CartProdcut into table
		if (result == 0) {
			CartProducts c = new CartProducts(cart, products, 1);
			session.save(c);
		}

		session.getTransaction().commit();
		session.close();
		return null;
	}

	@Override
	public Cart removeCartProductById(long id) {

		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		session.beginTransaction();

		// create query
		Query query = session.createQuery("delete from CartProducts c where c.id=:id");
		query.setParameter("id", id);

		session.getTransaction().commit();
		session.close();
		return null;
	}

	@Override
	public CartProducts getCartProductByCartIdAndProductId(Cart cart, Products products) {

		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		List<CartProducts> cartproducts = new ArrayList<>();
		session.beginTransaction();

		// create query
		String hql = "FROM CartProducts c where c.cart = :cart and c.products = :products";
		Query query = session.createQuery(hql);
		query.setParameter("cart", cart);
		query.setParameter("products", products);
		cartproducts = query.list();

		session.getTransaction().commit();
		session.close();

		// if cartproduct size is one then written else null
		if (cartproducts.size() == 1)
			return cartproducts.get(0);

		return null;
	}

	@Override
	public int deleteCart(long id) {

		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		session.beginTransaction();

		// create query
		Query query = session.createQuery("delete from CartProducts c where c.cart.id =:id");
		query.setParameter("id", id);
		
		int deleted = query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		return deleted;
	}

	@Override
	public int updateQuantity(long cartProductId, int quantity) {
		
		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		session.beginTransaction();
		
		//create query
		String hql = "Update CartProducts c  set  c.quantity = :quantity where c.id = :cartProductId";
		Query query = session.createQuery(hql);
		query.setParameter("quantity", quantity);
		query.setParameter("cartProductId", cartProductId);
		int updated = query.executeUpdate();
		
	
		session.getTransaction().commit();
		session.close();
		return updated;
	}

	@Override
	public void insertProductTocart(CartProducts cartProducts) {
		
		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		session.beginTransaction();
		
		// create query
		String hql = "Update CartProducts c  set  c.quantity =(c.quantity+:quantity) where c.cart = :cart and c.products = :products";
		Query query = session.createQuery(hql);
		query.setParameter("quantity", cartProducts.getQuantity());
		query.setParameter("cart", cartProducts.getCart());
		query.setParameter("products", cartProducts.getProducts());
		int result = query.executeUpdate();
		
		//if cartProduct is not there then we will save the prodduct into the cart
		if (result == 0)
			session.save(cartProducts);

		session.getTransaction().commit();
		session.close();
	}

}
