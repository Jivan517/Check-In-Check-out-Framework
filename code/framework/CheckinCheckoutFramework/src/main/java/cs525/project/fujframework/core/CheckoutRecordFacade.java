/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.core;

import java.sql.ResultSet;

/**
 * provides a higher level interface for checkout record subsystems using
 * "facade pattern"
 * 
 * @author paudelumesh
 *
 * @version 1.0.0
 */
public interface CheckoutRecordFacade {
	/**
	 * saves the checkout record into database
	 * 
	 * @param checkoutRecordEntry
	 * @return int
	 */
	public int saveCheckoutRecord(CheckoutRecordEntry checkoutRecordEntry);

	/**
	 * removes the checkout record from database
	 * 
	 * @param checkoutRecordEntry
	 * @return int
	 */
	public int removeCheckoutRecord(CheckoutRecordEntry checkoutRecordEntry);

	/**
	 * saves the check in record
	 * 
	 * @param checkoutRecordEntry
	 * @return boolean
	 */
	public int checkInRecord(CheckoutRecordEntry checkoutRecordEntry);

	/**
	 * rolls back the previous checkin request
	 * 
	 * @param checkoutRecordEntry
	 * @return boolean
	 */
	public int undoCheckIn(CheckoutRecordEntry checkoutRecordEntry);

	/**
	 * 
	 * @param customerId
	 * @param tableName
	 *            className
	 * @return resultSet of all checkout entries of customers
	 */
	public ResultSet getAllCheckoutRecordsByCustomer(int customerId, Class<?> tableName);

	/**
	 * 
	 * @param customerId
	 * @param userId
	 * @param tableName
	 *            className
	 * @return resultSet of all checkout entries of customers
	 */
	public ResultSet getAllCheckoutRecordsByCustomerAndUser(int customerId, int userId, Class<?> tableName);
}
