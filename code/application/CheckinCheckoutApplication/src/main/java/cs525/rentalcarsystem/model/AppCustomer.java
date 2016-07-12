/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.model;

import cs525.project.fujframework.core.Customer;

/**
 * This class is Application User which extends fujframework customer class
 * 
 * @author Fish
 * @since 1.0.0
 *
 */
public class AppCustomer extends Customer {

	private String phone;

	public void setPhone(String phoneNumber) {
		this.phone = phoneNumber;
	}

	public String getPhone() {
		return this.phone;
	}

}
