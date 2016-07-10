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
	private Command currentCommand = null;

	@Override
	public boolean saveCustomer(Customer customer) {

		currentCommand = new SaveCustomerCommand(customer);

		if (currentCommand.execute()) {
			// commands.push(currentCommand);
			return true;
		}
		return false;
	}

	@Override
	public boolean saveProduct(Product product) {

		currentCommand = new SaveProductCommand(product);
		if (currentCommand.execute()) {
			// commands.push(currentCommand);
			return true;
		}
		return false;
	}

	@Override
	public boolean saveSysUser(SysUser user) {

		currentCommand = new SaveUserCommand(user);
		if (currentCommand.execute()) {
			// commands.push(currentCommand);
			return true;
		}

		return false;
	}

	@Override
	public boolean saveCheckoutRecords(List<CheckoutRecordEntry> checkoutRecordEntries) {

		commands.clear();
		for (CheckoutRecordEntry entry : checkoutRecordEntries) {
			currentCommand = new CheckoutCommand(entry);
			if (currentCommand.execute()) {
				commands.push(currentCommand);
			} else {
				// rollback
				while (commands.size() > 0) {
					currentCommand = commands.pop();
					currentCommand.undo();

				}
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean checkBackRecordsIn(List<CheckoutRecordEntry> checkInEntries) {

		commands.clear();
		for (CheckoutRecordEntry entry : checkInEntries) {
			currentCommand = new CheckinCommand(entry);
			if (currentCommand.execute()) {
				commands.push(currentCommand);
			} else {

				// rollback
				while (commands.size() > 0) {
					currentCommand = commands.pop();
					currentCommand.undo();

				}
				return false;
			}
		}

		return true;
	}
}
