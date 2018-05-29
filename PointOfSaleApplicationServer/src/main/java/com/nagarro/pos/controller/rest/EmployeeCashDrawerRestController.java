package com.nagarro.pos.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.pos.constant.ConfigurationConstant;
import com.nagarro.pos.model.EmployeeCashDrawer;
import com.nagarro.pos.service.EmployeeCashDrawerService;
import com.nagarro.pos.service.VerificationService;

/**
 * It will invoke when '/api/cashDrawer' has been invoke and it will control
 * query related to cashDrawer
 * 
 * @author nishantgarg
 *
 */
@RequestMapping("/api/cashDrawer")
@CrossOrigin(origins = ConfigurationConstant.CORS)
@RestController
public class EmployeeCashDrawerRestController {

	@Autowired
	private EmployeeCashDrawerService employeeCashDrawerService;

	@Autowired
	private VerificationService verificationService;

	/**
	 * It will return the list of employeeCashDrawer on the basis of user
	 * 
	 * @param id
	 * @param httpServletRequest
	 * @return {@link List <{@link EmployeeCashDrawer}>
	 */
	@GetMapping("/")
	public List<EmployeeCashDrawer> getAllCashDrawerOfEmployee(HttpServletRequest httpServletRequest) {

		String token = httpServletRequest.getHeader("token");
		// System.out.println("in controller " + token);
		if (token == null || verificationService.verifyingEmployee(token) == null) {
			return null;
		}
		EmployeeCashDrawer employeeCashDrawer = verificationService.verifyingEmployee(token);
		return employeeCashDrawerService.getAllCashDrawerOfEmployee(employeeCashDrawer.getEmployees().getId());
	}

	/**
	 * It will insert the {@link EmployeeCashDrawer} for the employee
	 * 
	 * @param employeeCashDrawer
	 * @return {@link EmployeeCashDrawer}
	 */
	@PostMapping("/")
	public EmployeeCashDrawer setCashDrawerOfEmployee(@RequestBody EmployeeCashDrawer employeeCashDrawer,
			HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("token");
		if (token == null || verificationService.verifyingEmployee(token) == null) {
			return null;
		}
		return employeeCashDrawerService.setCashDrawerOfEmployee(employeeCashDrawer);
	}

	/**
	 * It will update {@link EmployeeCashDrawer} for the employee
	 * 
	 * @param employeeCashDrawer
	 * @return
	 */
	@PutMapping("/")
	public EmployeeCashDrawer updateCashDrawerOfEmployee(@RequestBody EmployeeCashDrawer employeeCashDrawer,
			HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("token");
		if (token == null || verificationService.verifyingEmployee(token) == null) {
			return null;
		}
		return employeeCashDrawerService.updateCashDrawerOfEmployee(employeeCashDrawer);
	}
}
