package com.nagarro.pos.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nagarro.pos.model.Employee;
import com.nagarro.pos.model.EmployeeCashDrawer;

/**
 * it will interact with database and handle query related to
 * {@link EmployeeCashDrawer}
 * 
 * @author nishantgarg
 *
 */
@Repository
public interface EmployeeCashDrawerRepository {

	/**
	 * This written {@link List <{@link EmployeeCashDrawer}> on the basis of
	 * {@link Employee}'s id
	 * 
	 * @param employeeId
	 * @return {@link List <{@link EmployeeCashDrawer}>
	 */
	public List<EmployeeCashDrawer> getEmployeeCashDrawer(long employeeId);

	/**
	 * This will set the cashDrawer for the Employe when the user is logged in
	 * 
	 * @param employeeCashDrawer
	 * @return {@link EmployeeCashDrawer}
	 */
	public EmployeeCashDrawer setCashDrawerOfEmployee(EmployeeCashDrawer employeeCashDrawer);

	/**
	 * This will update the {@link EmployeeCashDrawer} when the order is placed
	 * 
	 * @param employeeCashDrawer
	 * @return {@link EmployeeCashDrawer}
	 */
	public EmployeeCashDrawer updateCashDrawerOfEmployee(EmployeeCashDrawer employeeCashDrawer);

	/**
	 * This will return the {@link EmployeeCashDrawer} by its id
	 * 
	 * @param id
	 * @return {@link EmployeeCashDrawer}
	 */
	public EmployeeCashDrawer getCashDrawerById(long id);
}
