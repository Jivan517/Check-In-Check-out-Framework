/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */
package cs525.rentalcarsystem.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import cs525.project.fujframework.core.CheckoutRecordEntry;
import cs525.project.fujframework.core.CheckoutRecordFacade;
import cs525.project.fujframework.core.CheckoutRecordProtectionProxy;
import cs525.rentalcarsystem.model.KeyValuePair;

/**
 * @author OWNER
 *
 */
public class HomeController {

	private CheckoutRecordFacade facade;

	public HomeController() {
		this.facade = new CheckoutRecordProtectionProxy();
	}

	public List<KeyValuePair<LocalDate, Integer>> getCheckoutDates() {

		List<KeyValuePair<LocalDate, Integer>> dates = new ArrayList<>();

		ResultSet rs = facade.getAllCheckoutRecords(CheckoutRecordEntry.class);
		try {
			while (rs.next()) {

				dates.add(new KeyValuePair<>(rs.getDate("checkoutDate").toLocalDate(), rs.getInt("quantity")));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return dates;

	}
}
