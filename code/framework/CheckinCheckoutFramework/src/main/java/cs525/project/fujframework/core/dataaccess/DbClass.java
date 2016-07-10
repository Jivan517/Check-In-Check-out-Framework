/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
 
package cs525.project.fujframework.core.dataaccess;

import java.sql.ResultSet;

/**
 * @author Fish
 *
 */
public interface DbClass {
	 public void populateEntity(ResultSet resultSet) ;
	  /**
	   *  Method to get the database url
	   * @return
	   */
	    public String getDbUrl();
	    /**
	     *  Method to get the database query
	     * @return
	     */
	    public String getQuery();
	    /**
	     * Method to get database query parameters
	     * @return
	     */
	    public Object[] getQueryParams();
	    /**
	     * Methods that returns query parameter type (Read,Delete..)
	     * @return
	     */
	    public int[] getParamTypes();

}
