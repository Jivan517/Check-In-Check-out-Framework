/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

/**
 * provides an interface for getting logger instance
 * 
 * @author Jivan Nepali
 * @version 1.0.0
 *
 */
public interface Logger {

	/**
	 * logs warning message
	 * 
	 * @param message
	 * @return
	 */
	String warn(String message);

	/**
	 * logs debug message
	 * 
	 * @param message
	 * @return
	 */
	String debug(String message);

	/**
	 * logs info message
	 * 
	 * @param message
	 * @return
	 */
	String info(String message);

	/**
	 * logs error message
	 * 
	 * @param message
	 * @return
	 */
	String error(String message);
}
