package com.nagarro.pos.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.pos.controller.rest.EmployeeRestController;
import com.nagarro.pos.dao.impl.EmployeeCashDrawerRepositoryImplment;
import com.nagarro.pos.dao.impl.EmployeeRepositoryImplement;
import com.nagarro.pos.model.Employee;
import com.nagarro.pos.model.EmployeeCashDrawer;

/**
 * It will handle all the query related to {@link Employee} which is coming from
 * {@link EmployeeRestController}
 * 
 * @author nishantgarg
 *
 */
@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepositoryImplement employeeRepositoryImplement;

	@Autowired
	private EmployeeCashDrawerRepositoryImplment employeeCashDrawerRepositoryImplment;

	@Autowired
	private VerificationService verificationService;

	/**
	 * check user by its name id and password
	 * 
	 * @param employee
	 * @param price
	 * @return {@link String}
	 */
	@Transactional
	public String getEmployeeByUsernameAndPassword(Employee employee, double price) {

		Employee emp = employeeRepositoryImplement.getEmployeeByEmailAndPassword(employee);
		EmployeeCashDrawer empCashDrawer = null;
		if (emp != null) {
			empCashDrawer = employeeCashDrawerRepositoryImplment
					.setCashDrawerOfEmployee(new EmployeeCashDrawer(emp, price, new Date(), price, new Date()));
		} else {
			return null;
		}
		
		//generate the token with the EmployeeCashDrawer and sent to user as token
		String encryption = null;
		try {
			encryption = verificationService.encrypt(empCashDrawer.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encryption;
	}
	
	/**
	 * logout the use if he/she is already logged in
	 * @param employeeCashDrawer
	 * @param price
	 * @return {@link EmployeeCashDrawer}
	 */
	public EmployeeCashDrawer logOutUser(EmployeeCashDrawer employeeCashDrawer, double price) {
		
		EmployeeCashDrawer empCashDrawer = employeeCashDrawerRepositoryImplment
				.getCashDrawerById(employeeCashDrawer.getId());
		
		//check price written is by employee 
		System.err.println(price + " " + empCashDrawer.getEndCash());
		if (price == empCashDrawer.getEndCash()) {
			return empCashDrawer;
		}
		return null;
	}

}
