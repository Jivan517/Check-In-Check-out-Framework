/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */
package cs525.rentalcarsystem.model;

import java.util.List;

/**
 * @author OWNER
 *
 */
public class CheckoutCart {

	private int customerId;
	private List<Car> cars;

	public CheckoutCart() {

	}

	public CheckoutCart(int customerId, List<Car> cars) {
		super();
		this.setCustomerId(customerId);
		this.setCars(cars);
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

}
