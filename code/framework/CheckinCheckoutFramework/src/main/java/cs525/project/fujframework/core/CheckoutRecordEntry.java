/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.core;

import java.time.LocalDate;

/**
 * this object holds the checkout record details
 * 
 * @author paudelumesh
 *
 * @version 1.0.0
 */
public class CheckoutRecordEntry {
	private int checkoutRecordEntryId;
	private int productRefId;
	private int customerRefId;
	private int personRefId;
	private int quantity;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private LocalDate returnedDate;
	private boolean isReturned;
	private double rentalFee;
	private double rentalFine;

	/**
	 * @return the productRefId
	 */
	public int getProductRefId() {
		return productRefId;
	}

	/**
	 * @param productRefId
	 *            the productRefId to set
	 */
	public void setProductRefId(int productRefId) {
		this.productRefId = productRefId;
	}

	/**
	 * @return the customerRefId
	 */
	public int getCustomerRefId() {
		return customerRefId;
	}

	/**
	 * @param customerRefId
	 *            the customerRefId to set
	 */
	public void setCustomerRefId(int customerRefId) {
		this.customerRefId = customerRefId;
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

	/**
	 * @return the checkoutRecordEntryId
	 */
	public int getCheckoutRecordEntryId() {
		return checkoutRecordEntryId;
	}

	/**
	 * @param checkoutRecordEntryId
	 *            the checkoutRecordEntryId to set
	 */
	public void setCheckoutRecordEntryId(int checkoutRecordEntryId) {
		this.checkoutRecordEntryId = checkoutRecordEntryId;
	}

	public int getPersonRefId() {
		return personRefId;
	}

	public void setPersonRefId(int personRefId) {
		this.personRefId = personRefId;
	}

}
