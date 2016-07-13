/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.core;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import cs525.project.fujframework.core.dataaccess.DbAction;
import cs525.project.fujframework.core.dataaccess.DbActionImpl;
import cs525.project.fujframework.middleware.ConsoleLogger;
import cs525.project.fujframework.utils.DbHelper;

/**
 * facade implementation of CheckoutRecord
 * 
 * @author paudelumesh
 *
 * @version 1.0.0
 */
public class CheckoutRecordFacadeImpl implements CheckoutRecordFacade {
	private DbAction dbaction;
	StringBuilder queryBuilder;
	StringBuilder updateProductQuery;

	/**
	 * 
	 */
	public CheckoutRecordFacadeImpl() {
		this.dbaction = new DbActionImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.CheckoutRecordFacade#saveCheckoutRecord(
	 * cs525.project.fujframework.core.CheckoutRecordEntry)
	 */
	@Override
	public int saveCheckoutRecord(CheckoutRecordEntry checkoutRecordEntry) {
		System.out.println("QUERY: " + DbHelper.getInsertQuery(checkoutRecordEntry));
		return this.dbaction.Create(DbHelper.getInsertQuery(checkoutRecordEntry));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.CheckoutRecordFacade#removeCheckoutRecord
	 * (cs525.project.fujframework.core.CheckoutRecordEntry)
	 */
	@Override
	public int removeCheckoutRecord(CheckoutRecordEntry checkoutRecordEntry) {
		queryBuilder = new StringBuilder();
		queryBuilder.append(
				"DELETE FROM checkoutrecord WHERE customerId=" + checkoutRecordEntry.getCheckoutRecordEntryId());
		return this.dbaction.delete(queryBuilder.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.CheckoutRecordFacade#checkInRecord(cs525.
	 * project.fujframework.core.CheckoutRecordEntry)
	 */
	@Override
	public int checkInRecord(CheckoutRecordEntry checkoutRecordEntry) {
		queryBuilder = new StringBuilder();
		updateProductQuery = new StringBuilder();

		queryBuilder.append("UPDATE checkoutrecordentry SET isReturned=b'1', returnedDate='"
				+ LocalDate.now().toString() + "', rentalFine=" + checkoutRecordEntry.getRentalFine()
				+ " WHERE checkoutRecordEntryId=" + checkoutRecordEntry.getCheckoutRecordEntryId());

		updateProductQuery.append("UPDATE car SET quantity = quantity + " + checkoutRecordEntry.getQuantity()
				+ " WHERE productId=" + checkoutRecordEntry.getProductRefId());

		System.out.println("UPDATE QUERY: " + queryBuilder.toString());
		System.out.println("UPDATE PRODUCT QUERY: " + updateProductQuery.toString());

		this.dbaction.update(updateProductQuery.toString());
		return this.dbaction.update(queryBuilder.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.CheckoutRecordFacade#undoCheckIn(cs525.
	 * project.fujframework.core.CheckoutRecordEntry)
	 */
	@Override
	public int undoCheckIn(CheckoutRecordEntry checkoutRecordEntry) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("UPDATE checkoutrecord SET isReturned='false', returnedDate=NULL" + " WHERE customerId="
				+ checkoutRecordEntry.getCheckoutRecordEntryId());
		return this.dbaction.update(queryBuilder.toString());
	}

	@Override
	public ResultSet getAllCheckoutRecordsByCustomer(int customerId, Class<?> tableName, Class<?> joinTableName) {

		queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT a.*, b.make, b.model, b.name, b.rentalFeePerDay, b.overduefinePerDay, b.plate FROM "
				+ tableName.getSimpleName() + " a INNER JOIN " + joinTableName.getSimpleName()
				+ " b ON a.customerRefId = " + customerId + " AND a.productRefId=b.productId");
		return this.dbaction.read(queryBuilder.toString());
	}

	@Override
	public ResultSet getAllCheckoutRecordsByCustomerAndUser(int customerId, int userId, Class<?> tableName) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT * FROM " + tableName.getSimpleName() + " WHERE customerRefId = " + customerId
				+ " and personRefId = " + userId);
		return this.dbaction.read(queryBuilder.toString());
	}

	@Override
	public ResultSet getAllCheckoutRecords(Class<?> tableName) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT * FROM " + tableName.getSimpleName());
		return this.dbaction.read(queryBuilder.toString());
	}

}
