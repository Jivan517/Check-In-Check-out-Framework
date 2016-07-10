package cs525.project.fujframework.middleware;

import java.util.List;

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
	private List<Person> persons;
	/**
     * @{ASDSortedListImp} constructor with list of @{Person} as a parameter
     * which is specified by application user 
     * @param functor
     */
	public ASDSortedListImp(List<Person> persons) {
		this.persons = persons;
	}

	@Override
	public SortedListIterator CreateIterator(Predicate<String> functor) {
		return new SortedListIteratorImp(functor);
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

		@Override
		public Object next() {
			if (this.hasNext()) {
				String element = "";// persons.get(index);
				if ( predicate.apply(element) ) {
					return element;
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
