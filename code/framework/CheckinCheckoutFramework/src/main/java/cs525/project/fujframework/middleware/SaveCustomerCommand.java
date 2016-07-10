/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

import cs525.project.fujframework.core.CustomerFacade;
import cs525.project.fujframework.core.CustomerFacadeImpl;

/**
 * concrete command for customer save operation
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 *
 */
public class SaveCustomerCommand implements Command {

	private Customer customer;
	private CustomerFacade facade;

	public SaveCustomerCommand(Customer customer) {
		this.customer = customer;
		this.facade = new CustomerFacadeImpl();
	}

	@Override
	public boolean execute() {
		return facade.saveCustomer(customer);
	}

	@Override
	public boolean undo() {
		return facade.removeCustomer(customer);
	}

}
