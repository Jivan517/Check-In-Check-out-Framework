/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */

package cs525.project.fujframework.middleware;

import java.util.List;

import cs525.project.fujframework.core.CheckoutRecordEntry;
import cs525.project.fujframework.core.Product;

/**
 * provides an interface for entry to all the command execution
 * 
 * @author Jivan Nepali
 * 
 * @since 1.0.0
 *
 */
public interface CommandManager {

	/**
	 * persists customer in the database
	 * 
	 * @param customer
	 *            the customer that has to be persisted
	 * @return boolean success or failure
	 */
	boolean saveCustomer(Customer customer);

	/**
	 * persists the product in the database
	 * 
	 * @param product
	 *            the product that has to be persisted
	 * @return boolean success or failure
	 */
	boolean saveProduct(Product product);

	/**
	 * persists the system user in the database
	 * 
	 * @param user
	 *            the system user that has to be persisted
	 * @return boolean success or failure
	 */
	boolean saveSysUser(SysUser user);

	/**
	 * persists the list of checkout record entries in the database
	 * 
	 * @param checkoutRecordEntries
	 *            the checkout record entries that needs to be persisted
	 * @return boolean success or failure
	 */
	boolean saveCheckoutRecords(List<CheckoutRecordEntry> checkoutRecordEntries);

	/**
	 * updates the checkout record entries with the return information
	 * 
	 * @param checkInEntries
	 *            the entries for checking back in
	 * @return boolean success or failure
	 */
	boolean checkBackRecordsIn(List<CheckoutRecordEntry> checkInEntries);
}
