package cs525.rentalcarsystem.backend;

import cs525.project.fujframework.core.Product;

public class Car implements Product {

	private int productId;
	private String name;
	private String model;
	private String description;
	private String plate;
	private String color;
	private String make;
	private int quantity;
	private int year;
	private double overdueFinePerDay;
	private double rentalFeePerDay;
	private int mileage;

	public Car() {

	}

	public Car(String name, String model, String description, String plate, String color, String make, int quantity,
			int year, double overdueFinePerDay, double rentalFeePerDay, int mileage) {
		super();
		this.name = name;
		this.setModel(model);
		this.setDescription(description);
		this.setPlate(plate);
		this.setColor(color);
		this.setMake(make);
		this.setQuantity(quantity);
		this.setYear(year);
		this.overdueFinePerDay = overdueFinePerDay;
		this.rentalFeePerDay = rentalFeePerDay;
		this.setMileage(mileage);
	}

	@Override
	public double getOverDueFinePerDay() {
		return this.overdueFinePerDay;
	}

	@Override
	public int getProductId() {
		return this.productId;
	}

	@Override
	public String getProductName() {
		return this.name;
	}

	@Override
	public double getRentalFeePerDay() {
		return this.rentalFeePerDay;
	}

	@Override
	public void setOverDueFinePerDay(double overdueFinePerDay) {
		this.overdueFinePerDay = overdueFinePerDay;

	}

	@Override
	public void setProductId(int productId) {
		this.productId = productId;

	}

	@Override
	public void setProductName(String name) {
		this.name = name;

	}

	@Override
	public void setRentalFeePerDay(double rentalFee) {
		this.rentalFeePerDay = rentalFee;

	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
