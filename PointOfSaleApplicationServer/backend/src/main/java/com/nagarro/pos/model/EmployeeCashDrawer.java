package com.nagarro.pos.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * POJO for {@link EmployeeCashDrawer}
 * @author nishantgarg
 *
 */
@Entity
public class EmployeeCashDrawer {

	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;

	private double endCash;

	@DateTimeFormat(pattern = "dd/MM/YY mm:hh")
	private Date endTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private double startCash;

	@DateTimeFormat(pattern = "dd/MM/YY mm:hh")
	private Date startTime;

	/**
	 * 
	 */
	public EmployeeCashDrawer() {
	}

	/**
	 * @param employee
	 * @param endCash
	 * @param endTime
	 * @param startCash
	 * @param startTime
	 */
	public EmployeeCashDrawer(Employee employee, double endCash, Date endTime, double startCash, Date startTime) {
		this.employee = employee;
		this.endCash = endCash;
		this.endTime = endTime;
		this.startCash = startCash;
		this.startTime = startTime;
	}

	/**
	 * @param employees
	 * @param id
	 * @param endTime
	 * @param startTime
	 * @param startCash
	 * @param endCash
	 * @param orders
	 */
	public EmployeeCashDrawer(Employee employees, long id, Date endTime, Date startTime, double startCash,
			double endCash/* , List<Orders> orders */) {
		this.employee = employees;
		this.id = id;
		this.endTime = endTime;
		this.startTime = startTime;
		this.startCash = startCash;
		this.endCash = endCash;
		// this.orders = orders;
	}

	/**
	 * @return the employees
	 */
	public Employee getEmployees() {
		return employee;
	}

	/**
	 * @return the endCash
	 */
	public double getEndCash() {
		return endCash;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	// /**
	// * @return the orders
	// */
	// public List<Orders> getOrders() {
	// return orders;
	// }

	/**
	 * @return the startCash
	 */
	public double getStartCash() {
		return startCash;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param employees
	 *            the employees to set
	 */
	public void setEmployees(Employee employees) {
		this.employee = employees;
	}

	/**
	 * @param endCash
	 *            the endCash to set
	 */
	public void setEndCash(double endCash) {
		this.endCash = endCash;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	// /**
	// * @param orders
	// * the orders to set
	// */
	// public void setOrders(List<Orders> orders) {
	// this.orders = orders;
	// }

	/**
	 * @param startCash
	 *            the startCash to set
	 */
	public void setStartCash(double startCash) {
		this.startCash = startCash;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        String format = formatter.format(this.endTime);
        String fff = formatter.format(this.startTime);
		return "{\"endCash\":"+this.endCash+",\"endTime\":\""+format+"\",\"id\":"+this.id+",\"startCash\":"+this.startCash+",\"startTime\":\""+fff+"\",\"employees\":{\"id\":"+this.employee.getId()+",\"password\":\""+this.employee.getPassword()+"\",\"username\":\""+this.employee.getUsername()+"\"}}";
	}
}
