package com.nagarro.pos.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * POJO of {@link Orders}
 * 
 * @author nishantgarg
 *
 */
@Entity
public class Orders {

	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@DateTimeFormat(pattern = "dd/MM/YY mm:hh")
	private Date date;

	@ManyToOne(optional = false)
	private Employee employee;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String modeOfPayment;

	private String status;

	/**
	 * 
	 */
	public Orders() {
	}

	/**
	 * @param customer
	 * @param date
	 * @param employee
	 * @param status
	 */
	public Orders(Customer customer, Date date, Employee employee, String status) {
		this.customer = customer;
		this.date = date;
		this.employee = employee;
		this.status = status;
	}

	/**
	 * @param customer
	 * @param date
	 * @param employee
	 * @param modeOfPayment
	 * @param status
	 */
	public Orders(Customer customer, Date date, Employee employee, String modeOfPayment, String status) {
		this.customer = customer;
		this.date = date;
		this.employee = employee;
		this.modeOfPayment = modeOfPayment;
		this.status = status;
	}

	/**
	 * @param date
	 * @param employee
	 * @param customer
	 * @param status
	 * @param modeOfPayment
	 * @param id
	 * @param orderedProduct
	 */
	public Orders(Date date, Employee employee, Customer customer, String status, String modeOfPayment, long id,
			List<OrderedProduct> orderedProduct) {
		this.date = date;
		this.employee = employee;
		this.customer = customer;
		this.status = status;
		this.modeOfPayment = modeOfPayment;
		this.id = id;
		// this.orderedProducts = orderedProduct;
	}

	/**
	 * @param date
	 * @param id
	 * @param orderedProduct
	 * @param employee
	 */
	public Orders(Date date, long id, List<OrderedProduct> orderedProduct, Employee employee) {
		this.date = date;
		this.id = id;
		// orderedProducts = orderedProduct;
		this.employee = employee;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the modeOfPayment
	 */
	public String getModeOfPayment() {
		return modeOfPayment;
	}

	/**
	 * // * @return the orderedProduct //
	 */
	// public List<OrderedProduct> getOrderedProduct() {
	// return orderedProducts;
	// }

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @param employee
	 *            the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param modeOfPayment
	 *            the modeOfPayment to set
	 */
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	// /**
	// * @param orderedProduct
	// * the orderedProduct to set
	// */
	// public void setOrderedProduct(List<OrderedProduct> orderedProduct) {
	// orderedProducts = orderedProduct;
	// }

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
