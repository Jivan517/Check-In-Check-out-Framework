/**
 * 
 */
package cs525.project.fujframework.core;

/**
 * provides a higher level interface for checkout record subsystems using
 * "facade pattern"
 * 
 * @author paudelumesh
 *
 * @version 1.0.0
 */
public interface CheckoutRecordFacade {
	/**
	 * saves the checkout record into database
	 * 
	 * @param checkoutRecordEntry
	 * @return int
	 */
	public int saveCheckoutRecord(CheckoutRecordEntry checkoutRecordEntry);

	/**
	 * removes the checkout record from database
	 * 
	 * @param checkoutRecordEntry
	 * @return int
	 */
	public int removeCheckoutRecord(CheckoutRecordEntry checkoutRecordEntry);

	/**
	 * saves the check in record
	 * 
	 * @param checkoutRecordEntry
	 * @return boolean
	 */
	public boolean checkInRecord(CheckoutRecordEntry checkoutRecordEntry);

	/**
	 * rolls back the previous checkin request
	 * 
	 * @param checkoutRecordEntry
	 * @return boolean
	 */
	public boolean undoCheckIn(CheckoutRecordEntry checkoutRecordEntry);
}
