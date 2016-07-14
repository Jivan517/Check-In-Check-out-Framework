/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.middleware;

/**
 * SortedListIterator/Iterator interface Allows clients to access and traverse
 * elements
 * 
 * 
 * @author Fish
 * 
 */
public interface SortedListIterator {
	/**
	 * traverse the element and returns true if the given element has next
	 * element
	 * 
	 * @return
	 */
	public boolean hasNext();

	/**
	 * This method check if the element has next element and returns it if it
	 * has nexxt element
	 * 
	 * @return Next Object
	 */
	public Object next();

	/**
	 * 
	 * @return current object
	 */
	public Object currentItem();
}
