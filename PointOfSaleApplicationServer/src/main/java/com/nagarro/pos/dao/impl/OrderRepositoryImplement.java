package com.nagarro.pos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nagarro.pos.dao.OrderRepository;
import com.nagarro.pos.hibernate.util.HibernateUtils;
import com.nagarro.pos.model.Cart;
import com.nagarro.pos.model.Employee;
import com.nagarro.pos.model.OrderedProduct;
import com.nagarro.pos.model.Orders;

/**
 * 
 * @author nishantgarg
 *
 */
@Repository
public class OrderRepositoryImplement implements OrderRepository {

	@Override
	public List<Orders> findAllOrders(long id) {
		
		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		List<Orders> orders = new ArrayList<>();
		List<Employee> emp = new ArrayList<>();
		session.beginTransaction();
		
		//create query
		String hql = "FROM Orders o where o.employee.id = :id  order by o.date desc";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		orders = query.list();

		session.getTransaction().commit();
		session.close();
		return orders;
		
	}

	@Override
	public Orders saveOrder(Orders orders) {

		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		session.beginTransaction();
		
		//save order
		session.save(orders);
		session.getTransaction().commit();
		session.close();
		return orders;
	}

	@Override
	public void insertOrderedProduct(OrderedProduct orderedProduct) {
		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		session.beginTransaction();
		
		// save orderProduct
		session.save(orderedProduct);
		session.getTransaction().commit();
		session.close();
			
	}

	@Override
	public void deleteSavedOrder(long id) {
		
		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		session.beginTransaction();
		
		//create query
		Query query = session.createQuery("delete from Orders o where o.id=:id");
		query.setParameter("id", id);
		
		session.getTransaction().commit();
		session.close();
	}
}
