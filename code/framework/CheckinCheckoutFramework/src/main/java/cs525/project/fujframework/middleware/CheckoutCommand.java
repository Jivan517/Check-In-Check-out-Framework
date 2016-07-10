/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

import cs525.project.fujframework.core.CheckoutRecordEntry;
import cs525.project.fujframework.core.CheckoutRecordFacade;
import cs525.project.fujframework.core.CheckoutRecordFacadeImpl;

/**
 * provides concrete command for checkout record save operation
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 *
 */
public class CheckoutCommand implements Command {

	private CheckoutRecordFacade facade;
	private CheckoutRecordEntry checkoutRecordEntry;

	public CheckoutCommand(CheckoutRecordEntry checkoutRecordEntry) {
		this.facade = new CheckoutRecordFacadeImpl();
		this.checkoutRecordEntry = checkoutRecordEntry;
	}

	@Override
	public boolean execute() {

		return facade.saveCheckoutRecord(checkoutRecordEntry);
	}

	@Override
	public boolean undo() {
		return facade.removeCheckoutRecord(checkoutRecordEntry);
	}

}
