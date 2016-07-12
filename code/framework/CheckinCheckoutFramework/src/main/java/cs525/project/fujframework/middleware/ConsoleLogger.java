/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

/**
 * provides a class to implement the console logging features
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 *
 */
public class ConsoleLogger extends LoggerDecorator {

	public ConsoleLogger(Logger logger) {
		super(logger);
	}

	@Override
	public String warn(String message) {
		logToConsole(message);
		return super.warn(message);
	}

	@Override
	public String debug(String message) {
		logToConsole(message);
		return super.debug(message);

	}

	@Override
	public String info(String message) {
		logToConsole(message);
		return super.info(message);
	}

	@Override
	public String error(String message) {
		logToConsole(message);
		return super.error(message);
	}

	private void logToConsole(String message) {
		System.out.println(message);
	}
}
