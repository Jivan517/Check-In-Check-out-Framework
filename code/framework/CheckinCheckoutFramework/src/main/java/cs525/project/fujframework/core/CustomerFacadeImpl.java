/**
 * 
 */
package cs525.project.fujframework.core;

import java.sql.ResultSet;
import java.sql.SQLException;

import cs525.project.fujframework.core.dataaccess.DbAction;
import cs525.project.fujframework.core.dataaccess.DbActionImpl;
import cs525.project.fujframework.middleware.ConsoleLogger;
import cs525.project.fujframework.middleware.Logger;
import cs525.project.fujframework.middleware.LoggerImpl;
import cs525.project.fujframework.utils.DbHelper;

/**
 * facade pattern implementation of customer
 * 
 * @author paudelumesh
 *
 * @version 1.0.0
 */
public class CustomerFacadeImpl implements CustomerFacade {
	private DbAction dbaction;
	StringBuilder queryBuilder;
	private Logger logger;

	/**
	 * 
	 */
	public CustomerFacadeImpl() {
		this.dbaction = new DbActionImpl();
		this.logger = new ConsoleLogger(new LoggerImpl());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.CustomerFacade#saveCustomer(cs525.project
	 * .fujframework.middleware.Customer)
	 */
	@Override
	public int saveCustomer(Customer customer) {
		String tableName = customer.getClass().getSimpleName();
		int personId = getRecentlyAddedCustomer(tableName);
		if (personId > 0) {
			Address address = customer.getAddress();
			address.setPersonRefId(personId);
			address.setIsCustomer(true);
			return this.dbaction.Create(DbHelper.getInsertQuery(address));
		}
		return 0;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.core.CustomerFacade#removeCustomer(cs525.
	 * project.fujframework.middleware.Customer)
	 */
	@Override
	public int removeCustomer(Customer customer) {
		queryBuilder = new StringBuilder();
		String tableName = customer.getClass().getSimpleName();
		queryBuilder.append("DELETE FROM " + tableName + " WHERE customerId=" + customer.getPersonId());
		return this.dbaction.delete(queryBuilder.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.core.CustomerFacade#getCustomerById(int)
	 */
	@Override
	public ResultSet getCustomerById(int customerId) {

		// TODO: Generalize the table name value
		String tableName = "AppCustomer";
		queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT * FROM " + tableName + " where customerId = " + customerId);
		return this.dbaction.read(queryBuilder.toString());
	}

	private int getRecentlyAddedCustomer(String tableName) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT * FROM " + tableName + " ORDER BY customerId DESC LIMIT 1");

		logger.info(queryBuilder.toString());
		ResultSet result = this.dbaction.read(queryBuilder.toString());
		int customerId = 0;
		try {

			while (result.next()) {
				customerId = result.getInt("customerId");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customerId;
	}

	@Override
	public ResultSet getAllCustomers(Class<?> tableName) {

		queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT * FROM " + tableName.getSimpleName());
		System.out.println(queryBuilder.toString());
		return this.dbaction.read(queryBuilder.toString());
	}

}
