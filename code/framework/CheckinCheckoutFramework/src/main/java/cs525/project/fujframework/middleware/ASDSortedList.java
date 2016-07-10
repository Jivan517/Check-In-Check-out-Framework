/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.middleware;

/**
 * This is Aggregate interface of ASDSortedList which uses instance of
 * SortedListIterator for creating Iterator
 * 
 * @author Fish
 * 
 * @since 1.0.0
 */
public interface ASDSortedList {
	/**
	 * 
	 * @param @{Predicate<T>}
	 *            which is implemented by user application
	 * @return @{SortedListIterator} type
	 */
	public SortedListIterator createIterator(Predicate<String> functor);
}
