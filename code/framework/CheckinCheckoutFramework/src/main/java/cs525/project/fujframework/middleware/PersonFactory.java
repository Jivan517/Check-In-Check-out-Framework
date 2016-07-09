/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

/**
 * factory class for creating person related object(s)
 * 
 * @author Jivan Nepali
 * 
 * @since 1.0.0
 *
 */
public interface PersonFactory {

	PersonFactory createPerson(String type);
}
