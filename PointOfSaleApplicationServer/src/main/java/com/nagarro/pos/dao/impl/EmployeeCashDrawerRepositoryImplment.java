package com.nagarro.pos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nagarro.pos.dao.EmployeeCashDrawerRepository;
import com.nagarro.pos.hibernate.util.HibernateUtils;
import com.nagarro.pos.model.EmployeeCashDrawer;

@Repository
public class EmployeeCashDrawerRepositoryImplment implements EmployeeCashDrawerRepository {

	@Override
	public List<EmployeeCashDrawer> getEmployeeCashDrawer(long employeeId) {

		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		List<EmployeeCashDrawer> employeeCashDrawer = new ArrayList<>();
		session.beginTransaction();

		// create query
		String hql = "FROM EmployeeCashDrawer e where e.employee.id = :employeeId order by e.endTime desc";
		Query query = session.createQuery(hql);
		query.setParameter("employeeId", employeeId);

		employeeCashDrawer = query.list();
		session.getTransaction().commit();
		session.close();
		return employeeCashDrawer;
	}

	@Override
	public EmployeeCashDrawer setCashDrawerOfEmployee(EmployeeCashDrawer employeeCashDrawer) {

		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		session.beginTransaction();

		// save emplloyeeCashDrawer
		session.save(employeeCashDrawer);
		session.getTransaction().commit();
		session.close();
		return employeeCashDrawer;
	}

	@Override
	public EmployeeCashDrawer updateCashDrawerOfEmployee(EmployeeCashDrawer employeeCashDrawer) {

		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		session.beginTransaction();

		// update employeeCashDrawer
		session.update(employeeCashDrawer);
		session.getTransaction().commit();
		session.close();
		return null;
	}

	@Override
	public EmployeeCashDrawer getCashDrawerById(long id) {

		// get session from Hibernate utils
		Session session = HibernateUtils.getSession().openSession();
		List<EmployeeCashDrawer> employeeCashDrawer = new ArrayList<>();
		session.beginTransaction();

		// create query
		String hql = "FROM EmployeeCashDrawer e where e.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		employeeCashDrawer = query.list();
		session.getTransaction().commit();
		session.close();

		// if employeeCashDrawer size is one then writte cashDrawer else return null
		if (employeeCashDrawer.size() == 1)
			return employeeCashDrawer.get(0);

		return null;
	}

}
