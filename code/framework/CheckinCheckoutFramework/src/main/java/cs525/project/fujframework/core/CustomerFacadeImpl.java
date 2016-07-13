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
		this.dbaction.Create(DbHelper.getInsertQuery(customer));

		String tableName = customer.getClass().getSimpleName();

		logger.info("customer saved in " + tableName);
		int personId = getRecentlyAddedCustomer(tableName);

		Address address = customer.getAddress();
		address.setPersonId(personId);

		logger.info("PersonID " + personId);
		address.setIsCustomer(true);
		return this.dbaction.Create(DbHelper.getInsertQuery(address));

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
	public Customer getCustomerById(int customerId) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT * FROM customer where customerId = " + customerId);
		return (Customer) this.dbaction.read(queryBuilder.toString());
	}

	private int getRecentlyAddedCustomer(String tableName) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT * FROM " + tableName + " ORDER BY customerId LIMIT 1");

		ResultSet result = this.dbaction.read(queryBuilder.toString());
		Customer customer = (Customer) result;
		return customer.getPersonId();
	}

}
