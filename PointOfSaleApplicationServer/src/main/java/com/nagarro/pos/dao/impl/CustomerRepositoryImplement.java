package com.nagarro.pos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nagarro.pos.dao.CustomerRepository;
import com.nagarro.pos.hibernate.util.HibernateUtils;
import com.nagarro.pos.model.Customer;
/**
 * 
 * @author nishantgarg
 *
 */
@Repository
public class CustomerRepositoryImplement implements CustomerRepository{

	@Override
	public List<Customer> getCustomerByFilter(String filter) {
		
		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		session.beginTransaction();
		List<Customer> customers = new ArrayList<>();
		
		//create Query for filter
		String hql = "from Customer c where c.name like :searchFilter or c.email like :searchFilter or c.phoneNumber like :searchFilter";
		Query query = (Query) session.createQuery(hql);
		query.setParameter("searchFilter", "%"+filter+"%");
		customers = query.list();
		
		
		session.getTransaction().commit();
		session.close();
		return customers;
		
	}

	@Override
	public Customer addCustomer(Customer customer) {
		
		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		session.beginTransaction();
		
		//save the customer
		session.save(customer);
		session.getTransaction().commit();
		session.close();
		return customer;
	}

	@Override
	public Customer getCoustomerById(long id) {
		
		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		session.beginTransaction();
		List<Customer> customers = new ArrayList<>();
		
		//create query
		String hql = "from Customer c where c.id = :id";
		Query query = (Query) session.createQuery(hql);
		query.setParameter("id", id);
		customers = query.list();
		session.getTransaction().commit();
		session.close();
		
		//if selected customer is one then we will return the list
		if(customers.size()==1)return customers.get(0);
		
		return null;
		
	}

}
