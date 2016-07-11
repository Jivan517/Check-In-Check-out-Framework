package cs525.rentalcarsystem.backend;

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
	boolean isAdmin;

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isAdmin() {
		return isAdmin;
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
