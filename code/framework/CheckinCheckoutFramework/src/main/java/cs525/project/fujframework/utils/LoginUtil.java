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
		Object value = SessionCache.getInstance().get(BusinessConstants.LOGGED_IN);
		Boolean flag = false;
		if (value != null)
			flag = true;
		System.out.println("LoggedIn: " + flag);
		return flag;
	}

	/**
	 * checks whether the logged in user is admin or not
	 * 
	 * @return
	 */
	public static boolean isAdmin() {
		Object value = SessionCache.getInstance().get(BusinessConstants.ADMIN);
		Boolean flag = false;
		if (value != null)
			flag = true;
		System.out.println("IsADMIN: " + flag);
		return flag;
	}

	public static boolean isStaff() {
		Object value = SessionCache.getInstance().get(BusinessConstants.STAFF);
		Boolean flag = false;
		if (value != null)
			flag = true;
		System.out.println("IsStaff: " + flag);
		return flag;
	}

	public static String getLoggedInUsername() {
		Object value = SessionCache.getInstance().get(BusinessConstants.STAFF);
		if (value != null)
			return value.toString();
		return null;
	}

}
