/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.core.dataaccess;

import java.sql.ResultSet;

/**
 * This @{DbAction} class used for performing database actions
 * by communicating with @{SimpleConnectionPool} 
 * @author Fish
 *
 */
public interface DbAction {
	/**
	 * This method Performs insert query based passed query statement
	 * @param query
	 * @return integer
	 */
	public int Create(String query);

	/**
	 * This method Performs read query based passed query statement
	 * @param query
	 * @return Object 
	 */
	public ResultSet read(String query);

	/**
	 * This method Performs delete query based passed query statement
	 * @param query
	 * @return integer
	 */
	public int delete(String query);

	/**
	 * This method Performs update query based passed query statement
	 * @param query
	 * @return integer
	 */
	public int update(String query);

}
