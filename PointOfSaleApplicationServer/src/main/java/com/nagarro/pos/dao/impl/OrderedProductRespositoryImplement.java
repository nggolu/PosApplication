package com.nagarro.pos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nagarro.pos.dao.OrderedProductRepository;
import com.nagarro.pos.hibernate.util.HibernateUtils;
import com.nagarro.pos.model.Employee;
import com.nagarro.pos.model.OrderedProduct;

/**
 * 
 * @author nishantgarg
 *
 */
@Repository
public class OrderedProductRespositoryImplement implements OrderedProductRepository{

	@Override
	public List<OrderedProduct> getOrderDetailsByOrderId(long id,Employee employee) {
		
		// get session from Hibernate utils
		Session session =HibernateUtils.getSession().openSession();
		session.beginTransaction();
		
		//create query
		String hql = "FROM OrderedProduct o where o.orders.id = :id and o.orders.employee = :employee ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		query.setParameter("employee", employee);
		List<OrderedProduct> orderProduct  =  query.list();
		
		session.getTransaction().commit();
		session.close();
		return orderProduct;
	}
	
	@Override
	public List<OrderedProduct> getSaveOrdersById(Employee employee) {
		
		// get session from Hibernate utils
		Session session =HibernateUtils.getSession().openSession();
		session.beginTransaction();
		
		//create query
		String hql = "FROM Orders o where o.employee = :employee and o.status=:status";
		Query query = session.createQuery(hql);
		query.setParameter("employee", employee);
		query.setParameter("status", "pending");
		List<OrderedProduct> orderProduct  =  query.list();
		
		session.getTransaction().commit();
		session.close();		
		return orderProduct;
	}

	

	@Override
	public void deleteSavedProductOrder(long id) {
		
		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		session.beginTransaction();
		
		//create query
		Query query = session.createQuery("delete from OrderedProduct c where c.id=:id");
//		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		int updated = query.executeUpdate();
		
		session.getTransaction().commit();
		session.close();		
		return ;
		
	}

}
