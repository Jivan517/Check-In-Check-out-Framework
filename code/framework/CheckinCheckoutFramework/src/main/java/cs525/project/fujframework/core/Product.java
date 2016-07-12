/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.core;

/**
 * an interface to define the methods for concrete products that user needs to
 * supply
 * 
 * @author paudelumesh
 *
 * @version 1.0.0
 */
public interface Product {
	/**
	 * sets the product id for a product
	 * 
	 * @param productId
	 */
	public void setProductId(int productId);

	/**
	 * returns the product id
	 * 
	 * @return product id as a integer
	 */
	public int getProductId();

	/**
	 * sets the product name
	 * 
	 * @param productName
	 */
	public void setProductName(String productName);

	/**
	 * returns the product name
	 * 
	 * @return name as a string
	 */
	public String getProductName();

	/**
	 * sets the rental fee per day
	 * 
	 * @param rentalFee
	 */
	public void setRentalFeePerDay(double rentalFee);

	/**
	 * returns the rental fee per day
	 * 
	 * @return fee per day as double
	 */
	public double getRentalFeePerDay();

	/**
	 * sets the due fine per day
	 * 
	 * @param rentalFee
	 */
	public void setOverDueFinePerDay(double rentalFee);

	/**
	 * returns the fine rental per day
	 * 
	 * @return fine per day as double
	 */
	public double getOverDueFinePerDay();

}
