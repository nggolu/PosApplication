package com.nagarro.pos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nagarro.pos.dao.EmployeeRepository;
import com.nagarro.pos.hibernate.util.HibernateUtils;
import com.nagarro.pos.model.Employee;

/**
 * 
 * @author nishantgarg
 *
 */
@Repository
public class EmployeeRepositoryImplement implements EmployeeRepository {

	@Override
	public Employee getEmployeeByEmailAndPassword(Employee employee) {
		
		// get session from Hibernate utils
		Session session =HibernateUtils.getSession().openSession();
		session.beginTransaction();
		
		//create query
		String hql = "FROM Employee e where e.username = :username and e.password = :password";
		Query query = session.createQuery(hql);
		query.setParameter("username", employee.getUsername());
		query.setParameter("password", employee.getPassword());
		List<Employee> emps  =  query.list();
		
		session.getTransaction().commit();
		session.close();
		
		//if selected user is one then return else return null
		if(emps.size()==1)return emps.get(0);
		return null;
		
	}

}
