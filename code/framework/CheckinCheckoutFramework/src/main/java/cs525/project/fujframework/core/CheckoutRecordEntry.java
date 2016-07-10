/**
 * 
 */
package cs525.project.fujframework.core;

import java.time.LocalDate;

/**
 * @author paudelumesh
 *
 */
public class CheckoutRecordEntry {
	private int productId;
	private int customerId;
	private int quantity;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private LocalDate returnedDate;
	private boolean isReturned;
	private double rentalFee;
	private double rentalFine;

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the checkoutDate
	 */
	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	/**
	 * @param checkoutDate
	 *            the checkoutDate to set
	 */
	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	/**
	 * @return the dueDate
	 */
	public LocalDate getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the returnedDate
	 */
	public LocalDate getReturnedDate() {
		return returnedDate;
	}

	/**
	 * @param returnedDate
	 *            the returnedDate to set
	 */
	public void setReturnedDate(LocalDate returnedDate) {
		this.returnedDate = returnedDate;
	}

	/**
	 * @return the isReturned
	 */
	public boolean isReturned() {
		return isReturned;
	}

	/**
	 * @param isReturned
	 *            the isReturned to set
	 */
	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}

	/**
	 * @return the rentalFee
	 */
	public double getRentalFee() {
		return rentalFee;
	}

	/**
	 * @param rentalFee
	 *            the rentalFee to set
	 */
	public void setRentalFee(double rentalFee) {
		this.rentalFee = rentalFee;
	}

	/**
	 * @return the rentalFine
	 */
	public double getRentalFine() {
		return rentalFine;
	}

	/**
	 * @param rentalFine
	 *            the rentalFine to set
	 */
	public void setRentalFine(double rentalFine) {
		this.rentalFine = rentalFine;
	}

}
