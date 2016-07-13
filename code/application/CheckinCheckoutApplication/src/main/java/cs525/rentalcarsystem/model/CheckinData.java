/**
 * Copyright 2016 the original author or authors.
 * 
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.model;

/**
 * @author paudelumesh
 *
 */
public class CheckinData {
	private String name;
	private String model;
	private double rentalFeePerDay;
	private double rentalFinePerDay;
	private double rentalFee;
	private String dueDate;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the rentalFeePerDay
	 */
	public double getRentalFeePerDay() {
		return rentalFeePerDay;
	}

	/**
	 * @param rentalFeePerDay
	 *            the rentalFeePerDay to set
	 */
	public void setRentalFeePerDay(double rentalFeePerDay) {
		this.rentalFeePerDay = rentalFeePerDay;
	}

	/**
	 * @return the rentalFinePerDay
	 */
	public double getRentalFinePerDay() {
		return rentalFinePerDay;
	}

	/**
	 * @param rentalFinePerDay
	 *            the rentalFinePerDay to set
	 */
	public void setRentalFinePerDay(double rentalFinePerDay) {
		this.rentalFinePerDay = rentalFinePerDay;
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
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CheckinData [name=" + name + ", model=" + model + ", rentalFeePerDay=" + rentalFeePerDay
				+ ", rentalFinePerDay=" + rentalFinePerDay + ", rentalFee=" + rentalFee + ", dueDate=" + dueDate + "]";
	}

}
