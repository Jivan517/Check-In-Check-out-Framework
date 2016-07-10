/**
 * 
 */
package cs525.project.fujframework.core;

/**
 * defines a higher level interface for customer related subsystems using
 * "facade pattern"
 * 
 * @author paudelumesh
 *
 * @version 1.0.0
 */
public interface CustomerFacade {

	/**
	 * saves the customer into database taking the request using command pattern
	 * 
	 * @param customer
	 * @return boolean
	 */
	public boolean saveCustomer(Customer customer);

	/**
	 * removes the customer from database takes the request using command
	 * pattern
	 * 
	 * @param customer
	 * @return boolean
	 */
	public boolean removeCustomer(Customer customer);
}
