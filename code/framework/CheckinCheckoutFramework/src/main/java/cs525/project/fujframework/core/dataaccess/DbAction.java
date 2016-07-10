/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.core.dataaccess;
/**
 * 
 * @author Fish
 *
 */
public interface DbAction {
	/**
	 * 
	 * @param query
	 * @return
	 */
	public int Create(String query);

	/**
	 * 
	 * @param query
	 * @return
	 */
	public Object read(String query);

	/**
	 * 
	 * @param query
	 * @return
	 */
	public int delete(String query);

	/**
	 * 
	 * @param query
	 * @return
	 */
	public int update(String query);

}
