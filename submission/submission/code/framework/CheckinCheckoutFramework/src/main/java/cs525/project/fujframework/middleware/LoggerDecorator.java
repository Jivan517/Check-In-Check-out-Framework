/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

/**
 * provides a decorator class for logging, uses decorator pattern to implement
 * logging in different places - console, file or database
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 *
 */
public abstract class LoggerDecorator implements Logger {

	//logger instance 
	protected Logger loggerToBeDecorated;

	public LoggerDecorator(Logger logger) {
		this.loggerToBeDecorated = logger;
	}

	@Override
	public String warn(String message) {
		return loggerToBeDecorated.warn(message);
	}

	@Override
	public String debug(String message) {
		return loggerToBeDecorated.debug(message);
	}

	@Override
	public String info(String message) {
		return loggerToBeDecorated.info(message);
	}

	@Override
	public String error(String message) {
		return loggerToBeDecorated.error(message);
	}

}
