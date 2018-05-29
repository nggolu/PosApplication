package com.nagarro.pos.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.pos.controller.rest.EmployeeCashDrawerRestController;
import com.nagarro.pos.dao.impl.EmployeeCashDrawerRepositoryImplment;
import com.nagarro.pos.model.EmployeeCashDrawer;

/**
 * It will handle all the related to {@link EmployeeCashDrawer} which is comming
 * from {@link EmployeeCashDrawerRestController}
 * 
 * @author nishantgarg
 *
 */
@Service
public class EmployeeCashDrawerService {

	@Autowired
	private EmployeeCashDrawerRepositoryImplment employeeCashDrawerRepositoryImplment;

	/**
	 * It will return all the {@link EmployeeCashDrawer} based on the employee's id
	 * 
	 * @param id
	 * @return {@link List <{@link EmployeeCashDrawer}>
	 */
	@Transactional
	public List<EmployeeCashDrawer> getAllCashDrawerOfEmployee(long id) {

		return employeeCashDrawerRepositoryImplment.getEmployeeCashDrawer(id);
	}

	/**
	 * It will set the {@link EmployeeCashDrawer} when user is logged in
	 * 
	 * @param employeeCashDrawer
	 * @return {@link EmployeeCashDrawer}
	 */
	@Transactional
	public EmployeeCashDrawer setCashDrawerOfEmployee(EmployeeCashDrawer employeeCashDrawer) {
		return employeeCashDrawerRepositoryImplment.setCashDrawerOfEmployee(employeeCashDrawer);
	}

	/**
	 * It will update the {@link EmployeeCashDrawer} when order is placed
	 * @param employeeCashDrawer
	 * @return {@link EmployeeCashDrawer}
	 */
	@Transactional
	public EmployeeCashDrawer updateCashDrawerOfEmployee(EmployeeCashDrawer employeeCashDrawer) {
		return employeeCashDrawerRepositoryImplment.updateCashDrawerOfEmployee(employeeCashDrawer);
	}

}
