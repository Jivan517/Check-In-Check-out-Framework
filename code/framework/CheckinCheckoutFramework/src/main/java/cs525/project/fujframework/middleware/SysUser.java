/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */
package cs525.project.fujframework.middleware;

import cs525.project.fujframework.core.Address;

/**
 * System User implemenatation
 * 
 * @author paudelumesh
 *
 * @version 1.0.0
 */
public class SysUser implements Person {
	private int sysuserId;
	private String firstName;
	private String lastName;
	private String middleName;
	private Address address;
	private String email;

	/**
	 * @return the sysuserId
	 */
	public int getSysuserId() {
		return sysuserId;
	}

	/**
	 * @param sysuserId
	 *            the sysuserId to set
	 */
	public void setSysuserId(int sysuserId) {
		this.sysuserId = sysuserId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.Person#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.core.Person#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.core.Person#setLastName(java.lang.String)
	 */
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.core.Person#getLastName()
	 */
	@Override
	public String getLastName() {
		return lastName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.Person#setMiddleName(java.lang.String)
	 */
	@Override
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.core.Person#getMiddleName()
	 */
	@Override
	public String getMiddleName() {
		return middleName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.core.Person#setAddress(cs525.project.
	 * fujframework.core.Address)
	 */
	@Override
	public void setAddress(Address address) {
		this.address = address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.core.Person#getAddress()
	 */
	@Override
	public Address getAddress() {
		return address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.middleware.Person#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.middleware.Person#getEmail()
	 */
	@Override
	public String getEmail() {
		return email;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.middleware.Person#setPersonId(int)
	 */
	@Override
	public void setPersonId(int personId) {
		this.sysuserId = personId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.middleware.Person#getPersonId()
	 */
	@Override
	public int getPersonId() {
		return sysuserId;
	}

}
