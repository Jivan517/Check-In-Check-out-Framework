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
 * provides concrete command for check in record save operation
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 *
 */
public class CheckinCommand implements Command {

	private CheckoutRecordFacade facade;
	private CheckoutRecordEntry checkoutRecordEntry;

	public CheckinCommand(CheckoutRecordEntry checkoutRecordEntry) {
		this.facade = new CheckoutRecordFacadeImpl();
		this.checkoutRecordEntry = checkoutRecordEntry;
	}

	@Override
	public boolean execute() {
		int affectedRows = facade.checkInRecord(checkoutRecordEntry);
		return affectedRows == 1 ? true : false;
	}

	@Override
	public boolean undo() {
		int affectedRows = facade.undoCheckIn(checkoutRecordEntry);
		return affectedRows == 1 ? true : false;
	}

}
