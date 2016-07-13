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
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			queryBuilder.append("UPDATE checkoutrecord SET isReturned='true', returnedDate='"
					+ format.parse(LocalDate.now().toString()) + "' WHERE customerId="
					+ checkoutRecordEntry.getCheckoutRecordEntryId());
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
		queryBuilder
				.append("SELECT * FROM " + tableName.getSimpleName() + " a INNER JOIN " + joinTableName.getSimpleName()
						+ " b ON a.customerRefId = " + customerId + " AND a.carRefId=b.productId");
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
