package com.nagarro.pos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nagarro.pos.dao.ProductRepository;
import com.nagarro.pos.hibernate.util.HibernateUtils;
import com.nagarro.pos.model.Products;

@Repository
public class ProductRepositoryImplement implements ProductRepository{

	@Override
	public List<Products> getAllProducts() {
		
		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		List<Products> products = new ArrayList<>();
		session.beginTransaction();
		
		//create query
		String hql = "FROM Products";
		Query query = session.createQuery(hql);

		products = query.list();
		session.getTransaction().commit();
		session.close();
		return products;
	}

	@Override
	public Products getProductById(long id) {
		
		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		List<Products> products = new ArrayList<>();
		session.beginTransaction();
		
		//create query
		String hql = "FROM Products p where p.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		products = query.list();
		session.getTransaction().commit();
		session.close();
		
		//if prooduct.size == return product else return null
		if(products.size()==1)
			return products.get(0);
		
		return null;
	}

	@Override
	public Products updateProductById(Products products) {
		
		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		session.beginTransaction();
		
		//update products
		session.update(products);
		
		session.getTransaction().commit();
		session.close();
		return products;
	}

	@Override
	public List<Products> getAllProductsByFilter(String filter,long id) {
		
		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		List<Products> products = new ArrayList<>();
		session.beginTransaction();
		
		//create query
		String hql = "FROM Products p where p.id = :id or p.name like :filter or p.description like :filter  ";		
		Query query = session.createQuery(hql);
		query.setParameter("filter", "%"+filter+"%");
		query.setParameter("id", id);
		products = query.list();
		
		session.getTransaction().commit();
		session.close();
		return products;
	}

}
