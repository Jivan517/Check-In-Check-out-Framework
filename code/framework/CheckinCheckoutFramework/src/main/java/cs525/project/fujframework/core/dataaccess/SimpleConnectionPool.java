/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.core.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cs525.project.fujframework.middleware.ConsoleLogger;
import cs525.project.fujframework.middleware.Logger;
import cs525.project.fujframework.middleware.LoggerImpl;
import cs525.project.fujframework.utils.DbConfig;

/**
 * singleton class for managing the connection pool
 * 
 * @author Fish
 * 
 * @version 1.0.0
 *
 */
public class SimpleConnectionPool {
	private static SimpleConnectionPool instance;

	private SimpleConnectionPool() {
	}

	/**
	 * This method returns single instance of @{SimpleConnectionPool} using if
	 * it is not already created.
	 * 
	 * @return instance
	 */
	public static SimpleConnectionPool getInstance() {
		if (instance == null) {
			instance = new SimpleConnectionPool();
		}
		return instance;
	}

	/**
	 * This method return connection object
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		DbConfig config = new DbConfig();
		Connection con = null;
		Logger consoleLogger = new ConsoleLogger(new LoggerImpl());
		consoleLogger.debug("inside getConnection" + config.getDriver());
		try {
			Class.forName(config.getDriver());

			consoleLogger.debug("URL:" + config.getDbUrl() + " UserName: " + config.getUserName() + " Password: "
					+ config.getDbPassword());
			con = DriverManager.getConnection(config.getDbUrl(), config.getUserName(), config.getDbPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
