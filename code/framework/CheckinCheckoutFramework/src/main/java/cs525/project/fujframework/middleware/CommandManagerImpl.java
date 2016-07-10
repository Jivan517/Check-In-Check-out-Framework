/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

import java.util.List;
import java.util.Stack;

import cs525.project.fujframework.core.CheckoutRecordEntry;
import cs525.project.fujframework.core.Product;

/**
 * provides a class implementing CommandManager interface for entry to all the
 * command execution
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 *
 */
public class CommandManagerImpl implements CommandManager {

	/**
	 * stack for the recently executed operations which can be used during roll
	 * back process
	 */
	private Stack<Command> commands = new Stack<Command>();

	@Override
	public boolean saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveSysUser(SysUser user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveCheckoutRecords(List<CheckoutRecordEntry> checkoutRecordEntries) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkBackRecordsIn(List<CheckoutRecordEntry> checkInEntries) {
		// TODO Auto-generated method stub
		return false;
	}
}
