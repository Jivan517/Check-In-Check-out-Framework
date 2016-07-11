/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.middleware;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * This is implementation of @{ASDSortedList} interface (Aggregate) to access
 * and sort persons list The user has to implement @{Predicate <T>} predicate
 * interface for sorting person list based on first name and last name
 * 
 * @author Fish
 * 
 * @version 1.0.0
 *
 */

public class ASDSortedListImp implements ASDSortedList {
	private Comparator<Person> nameComparator= Comparator.comparing(Person ::getFirstName)
			                                           .thenComparing(Person::getLastName) ;
	private List<Person> persons;
	/**
     * @{ASDSortedListImp} constructor with list of @{Person} as a parameter
     * which is specified by application user 
     * @param functor
     */
	public ASDSortedListImp(List<Person> persons) {
		this.persons = getSortedPerson(persons);
	}

	@Override
	public SortedListIterator createIterator(Predicate<String> functor) {
		return new SortedListIteratorImp(functor);
	}
    
	/**
	 * This method returns list of sorted persons
	 * @param persons
	 * @return
	 */
	private List<Person> getSortedPerson(List<Person> persons){
		return persons.stream().sorted(nameComparator).collect(Collectors.toList());
	}
	
	/**
	 * 
	 * This Inner class is implements {@SortedListIterator} which does the
	 * traversing list of persons
	 *
	 */
	private class SortedListIteratorImp implements SortedListIterator {

		private Predicate predicate;
		private int index;
        /**
         * @{SortedListIteratorImp} constructor with @{Predicate <T>} as a parameter
         * which is specified by application user 
         * @param functor
         */
		public SortedListIteratorImp(Predicate<String> functor) {
			predicate = functor;
		}

		@Override
		public boolean hasNext() {
			if (index >= persons.size()) {
				return false;
			}
			return true;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Object next() {
			if (this.hasNext()) {
				String firstName = persons.get(index).getFirstName();
				String lastName = persons.get(index).getLastName();
				index++;
				if ( predicate.apply(firstName)|| predicate.apply(lastName) ) {
					return persons.get(index);
				} else {
					return next();
				}
			}
			return null;
		}

		@Override
		public Object currentItem() {
			return persons.get(index);
		}
		

	}

}
