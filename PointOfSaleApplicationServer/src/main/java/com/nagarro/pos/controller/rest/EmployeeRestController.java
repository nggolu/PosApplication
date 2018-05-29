package com.nagarro.pos.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.pos.constant.ConfigurationConstant;
import com.nagarro.pos.model.Employee;
import com.nagarro.pos.model.EmployeeCashDrawer;
import com.nagarro.pos.model.ResponseMessage;
import com.nagarro.pos.service.EmployeeService;
import com.nagarro.pos.service.VerificationService;

/**
 * It will invoke when '/api/employee' is invoke and it control the query
 * related to employee
 * 
 * @author nishantgarg
 *
 */
@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = ConfigurationConstant.CORS)
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private VerificationService verificationService;

	/**
	 * it will authenticate user and return response with token to user
	 * 
	 * @param employee
	 * @param price
	 * @return {@link ResponseMessage}
	 */
	@PostMapping("/login")
	public ResponseMessage getEmployeeByUsernameAndPassword(@RequestBody Employee employee,
			@RequestParam double price) {
		// get token form service if user is authenticate else token is null
		String token = employeeService.getEmployeeByUsernameAndPassword(employee, price);

		// based on token it will send response
		ResponseMessage res = new ResponseMessage();
		if (token == null) {
			res.setErrorMessage("email and password is wrong");
			res.setStatus(401);
		} else {
			res.setToken(token);
			res.setStatus(200);
		}
		return res;
	}

	/**
	 * logout the user after checking that user is login or not and after that
	 * logout the user
	 * 
	 * @param price
	 * @param httpServletRequest
	 * @return
	 */
	@PostMapping("/logOut")
	public ResponseMessage logOutUser(@RequestParam double price, HttpServletRequest httpServletRequest) {

		ResponseMessage res = new ResponseMessage();
		String token = httpServletRequest.getHeader("token");

		// if token is null means user is not logedIn
		if (token == null) {
			res.setStatus(401);
			res.setErrorMessage("Get Login first");
			return res;
		}

		// if token is not valid then user is not authorized
		EmployeeCashDrawer employeeCashDrawer = verificationService.verifyingEmployee(token);
		if (employeeCashDrawer == null) {
			res.setStatus(401);
			res.setErrorMessage("user is not authorized");
			return res;
		}
		// check the ending price written by user if enter wrong then
		EmployeeCashDrawer empCashDrawer = employeeService.logOutUser(employeeCashDrawer, price);
		if (empCashDrawer == null) {
			res.setStatus(401);
			res.setErrorMessage("please enter corredt amount");
			return res;
		}
		// else logout the user
		res.setStatus(200);
		res.setSuccessMessage("See you again");
		return res;
	}

}
