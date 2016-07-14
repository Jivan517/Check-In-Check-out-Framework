/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

/**
 * provides an interface for factory method
 * 
 * @author paudelumesh
 *
 * @version 1.0.0
 */
public interface PersonFactory {
	/**
	 * creates concrete implementation of person
	 * 
	 * @param type
	 *            the type of Person
	 * @return PersonFactory an instance of PersonFactory
	 * 
	 */
	public Person createPerson(String type);
}
