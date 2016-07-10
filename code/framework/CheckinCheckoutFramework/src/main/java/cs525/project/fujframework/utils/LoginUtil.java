/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */
package cs525.project.fujframework.utils;

/**
 * provides login util methods
 * 
 * @author Jivan Nepali
 *
 * @version 1.0.0
 */
public class LoginUtil {

	/**
	 * checks whether the user session is active or not
	 * 
	 * @return
	 */
	public static boolean isLoggedIn() {
		return (Boolean) SessionCache.getInstance().get(BusinessConstants.LOGGED_IN);
	}

	/**
	 * checks whether the logged in user is admin or not
	 * 
	 * @return
	 */
	public static boolean isAdmin() {
		return (Boolean) SessionCache.getInstance().get(BusinessConstants.ADMIN);
	}

}
