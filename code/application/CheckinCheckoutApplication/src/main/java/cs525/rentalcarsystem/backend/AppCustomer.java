/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.backend;
import cs525.project.fujframework.middleware.Customer;
/**
 * This class is Application User which extends fujframework customer class
 * @author Fish
 * @since 1.0.0
 *
 */
public class AppCustomer extends Customer {
	
	private String phoneNumber;
	
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

}
