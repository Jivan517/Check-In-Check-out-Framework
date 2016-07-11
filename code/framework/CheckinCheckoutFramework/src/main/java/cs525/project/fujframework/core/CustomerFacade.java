/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.core;

/**
 * defines a higher level interface for customer related subsystems using
 * "facade pattern"
 * 
 * @author paudelumesh
 *
 * @version 1.0.0
 */
public interface CustomerFacade {

	/**
	 * saves the customer into database taking the request using command pattern
	 * 
	 * @param customer
	 * @return int
	 */
	public int saveCustomer(Customer customer);

	/**
	 * removes the customer from database takes the request using command
	 * pattern
	 * 
	 * @param customer
	 * @return int
	 */
	public int removeCustomer(Customer customer);

	/**
	 * returns the customer based on customer id
	 * 
	 * @param customerId
	 * @return the customer object
	 */
	public Customer getCustomerById(int customerId);
}
