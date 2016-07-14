package cs525.rentalcarsystem.model;

import cs525.project.fujframework.core.Address;
import cs525.project.fujframework.middleware.SysUser;

/**
 * Copyright 2016 the original author or authors.
 * 
 * Licensed under the MIT License (MIT);
 */

/**
 * @author paudelumesh
 *
 * @version 1.0.0
 */
public class ApplicationUser extends SysUser {
	String userName;
	String password;
	String phone;
	boolean isAdmin;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param isAdmin
	 *            the isAdmin to set
	 */
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isAdmin() {
		return isAdmin;
	}
	
	

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.middleware.SysUser#setAddress(cs525.project.
	 * fujframework.core.Address)
	 */
	@Override
	public void setAddress(Address address) {
		super.setAddress(null);
	}

}
