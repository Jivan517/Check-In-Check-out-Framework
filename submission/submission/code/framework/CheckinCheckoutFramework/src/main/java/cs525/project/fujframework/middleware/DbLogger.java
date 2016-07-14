/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

/**
 * provides a class to implement the logging in database
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 *
 */
public class DbLogger extends LoggerDecorator {

	public DbLogger(Logger logger) {
		super(logger);
	}

}
