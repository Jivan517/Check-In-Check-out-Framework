/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

import cs525.project.fujframework.core.CheckoutRecordFacade;

/**
 * provides concrete command for checkout record save operation
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 *
 */
public class CheckoutCommand implements Command {

	private CheckoutRecordFacade checkoutRecord;

	public CheckoutCommand(CheckoutRecordFacade facade) {
		this.checkoutRecord = facade;
	}

	@Override
	public boolean execute() {

		return false;
	}

	@Override
	public boolean undo() {
		return false;
	}

}
