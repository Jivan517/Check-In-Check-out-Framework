/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */

package cs525.project.fujframework.middleware;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cs525.project.fujframework.utils.ConfigProperties;
import cs525.project.fujframework.utils.ConfigPropertiesImpl;

/**
 * provides a file logger class to write the log message in a log file specified
 * in logger.properties
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 */
public class FileLogger extends LoggerDecorator {

	private ConfigProperties config;

	private FileOutputStream logWriter;

	/**
	 * constructor
	 * 
	 * @param logger
	 */
	public FileLogger(Logger logger) {
		super(logger);
		config = new ConfigPropertiesImpl("logger.properties");
		String fileName = config.readProperty("log.file");

		// it will create new file if not exists and append the log
		File logFile = new File(fileName);
		if (!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			logWriter = new FileOutputStream(logFile, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String warn(String message) {
		logToFile(message);
		return super.warn(message);
	}

	@Override
	public String debug(String message) {
		logToFile(message);
		return super.debug(message);

	}

	@Override
	public String info(String message) {
		logToFile(message);
		return super.info(message);
	}

	@Override
	public String error(String message) {
		logToFile(message);
		return super.error(message);
	}

	private void logToFile(String message) {
		if (logWriter != null) {
			try {
				logWriter.write(message.getBytes());
				logWriter.flush();
				logWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
