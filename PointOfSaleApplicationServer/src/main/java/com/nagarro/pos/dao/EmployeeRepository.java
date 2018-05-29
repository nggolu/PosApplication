package com.nagarro.pos.dao;

import org.springframework.stereotype.Repository;

import com.nagarro.pos.model.Employee;

/**
 * it will interact with database and handle query related to {@link Employee}
 * 
 * @author nishantgarg
 *
 */
@Repository
public interface EmployeeRepository {

	/**
	 * this will get Employee by email and Password
	 * 
	 * @param employee
	 * @return
	 */
	public Employee getEmployeeByEmailAndPassword(Employee employee);
}
