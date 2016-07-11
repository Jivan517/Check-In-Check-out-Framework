/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.middleware;

import cs525.project.fujframework.core.Address;

/**
 * defines the interface for managing concrete classes of person instance
 * 
 * @author paudelumesh
 *
 * @version 1.0.0
 */
public interface Person {
	/**
	 * sets the person id
	 * @param personId
	 */
	public void setPersonId(int personId);

	/**
	 * returns the person id
	 * @return int
	 */
	public int getPersonId();

	/**
	 * sets the first name
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName);

	/**
	 * returns the first name
	 * 
	 * @return String
	 */
	public String getFirstName();

	/**
	 * sets the last name
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName);

	/**
	 * returns the last name
	 * 
	 * @return string
	 */
	public String getLastName();

	/**
	 * sets the middle name
	 * 
	 * @param middleName
	 */
	public void setMiddleName(String middleName);

	/**
	 * returns the middle name
	 * 
	 * @return string
	 */
	public String getMiddleName();

	/**
	 * sets the address object
	 * 
	 * @param address
	 */
	public void setAddress(Address address);

	/**
	 * returns the address object
	 * 
	 * @return Address
	 */
	public Address getAddress();

	/**
	 * sets an email of the person
	 * 
	 * @param email
	 */
	public void setEmail(String email);

	/**
	 * returns an email of the person
	 * 
	 * @return email as string
	 */
	public String getEmail();
}
