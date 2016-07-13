/**
 * Copyright 2016 the original author or authors.
 * 
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.middleware;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cs525.project.fujframework.core.CheckoutRecordEntry;
import cs525.project.fujframework.core.Customer;
import cs525.project.fujframework.core.CustomerFacade;
import cs525.project.fujframework.core.CustomerFacadeImpl;
import cs525.project.fujframework.core.Product;
import cs525.project.fujframework.core.ProductFacade;
import cs525.project.fujframework.core.ProductFacadeImpl;

/**
 * template method pattern concrete implementation
 * 
 * @author paudelumesh
 *
 * @version 1.0.0
 */
public class CheckoutTransactionManager extends TransactionManager {
	private CommandManager command;
	private ProductFacade productFacade;
	private CustomerFacade customerFacade;
	private Notifier notifier;

	/**
	 * 
	 */
	public CheckoutTransactionManager() {
		command = new CommandManagerImpl();
		productFacade = new ProductFacadeImpl();
		customerFacade = new CustomerFacadeImpl();
		notifier = new Notifier(new EmailNotificationStrategy());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.middleware.TransactionManager#
	 * processCheckoutRecord(java.util.List)
	 */
	@Override
	protected void processCheckoutRecord(List<CheckoutRecordEntry> checkoutRecordEntries) {
		command.saveCheckoutRecords(checkoutRecordEntries);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.middleware.TransactionManager#
	 * calculateRentalFee(java.util.List)
	 */
	@Override
	protected List<CheckoutRecordEntry> calculateRentalFeeOrOverdueFine(List<CheckoutRecordEntry> checkoutRecordEntries,
			Class<?> productClass) {
		for (CheckoutRecordEntry checkoutRecordEntry : checkoutRecordEntries) {
			ResultSet rs = productFacade.getProductById(checkoutRecordEntry.getProductRefId(), productClass);

			double fee = 0;
			try {
				while (rs.next()) {
					fee = rs.getDouble("rentalFeePerDay");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			double rentalFee = checkoutRecordEntry.getQuantity() * fee;
			checkoutRecordEntry.setRentalFee(rentalFee);
		}
		return checkoutRecordEntries;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.middleware.TransactionManager#sendNotification
	 * (java.util.List)
	 */
	@Override
	protected void sendNotification(List<CheckoutRecordEntry> checkoutRecordEntries, Class<?> productClass) {
		double totalFee = 0;
		int customerId = checkoutRecordEntries.get(0).getCustomerRefId();
		LocalDate dueDate = checkoutRecordEntries.get(0).getDueDate();
		ResultSet rs = customerFacade.getCustomerById(customerId);
		Customer customer = new Customer();
		try {
			while (rs.next()) {
				customer.setEmail(rs.getString("email"));
				customer.setPersonId(rs.getInt("customerId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Set<String> productNames = new TreeSet<String>();

		for (CheckoutRecordEntry checkoutRecordEntry : checkoutRecordEntries) {
			totalFee += checkoutRecordEntry.getRentalFine();
			productNames.add(getProductName(checkoutRecordEntries.get(0).getProductRefId(), productClass));
		}

		StringBuilder message = new StringBuilder();
		message.append("You rented the following item(s) \n");
		for (String productName : productNames) {
			message.append(productName + "\n");
		}
		message.append("Due date for the rented item(s) is " + dueDate + "\n");
		message.append("Your total fee for the rented item(s) is " + totalFee + "\n");

		notifier.notifyPerson(message.toString(), customer);
	}

	private String getProductName(int productId, Class<?> prodClass) {
		ResultSet rs = productFacade.getProductById(productId, prodClass);

		String name = "";
		try {
			while (rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return name;
	}

}
