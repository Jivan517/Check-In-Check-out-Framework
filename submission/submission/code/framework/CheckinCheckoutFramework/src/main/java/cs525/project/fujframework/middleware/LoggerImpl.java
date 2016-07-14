/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * concrete implementation for logger
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 *
 */
public class LoggerImpl implements Logger {

	public LoggerImpl() {

	}

	@Override
	public String warn(String message) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		return "WARN[" + dateFormat.format(date) + "] " + message;
	}

	@Override
	public String debug(String message) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		return "DEBUG[" + dateFormat.format(date) + "] " + message;
	}

	@Override
	public String info(String message) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		return "INFO[" + dateFormat.format(date) + "] " + message;
	}

	@Override
	public String error(String message) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		return "ERROR[" + dateFormat.format(date) + "] " + message;
	}

}
