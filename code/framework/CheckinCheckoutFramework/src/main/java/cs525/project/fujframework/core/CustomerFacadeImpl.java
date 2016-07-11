/**
 * 
 */
package cs525.project.fujframework.core;

import cs525.project.fujframework.core.dataaccess.DbAction;
import cs525.project.fujframework.core.dataaccess.DbActionImpl;
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

	/**
	 * 
	 */
	public CustomerFacadeImpl() {
		this.dbaction = new DbActionImpl();
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

		int personId = getRecentlyAddedCustomer();
		Address address = customer.getAddress();
		address.setPersonId(personId);
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
		queryBuilder.append("DELETE FROM customer WHERE customerId=" + customer.getPersonId());
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

	private int getRecentlyAddedCustomer() {
		queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT customerId FROM customer ORDER BY customerId LIMIT 1");
		return (Integer) this.dbaction.read(queryBuilder.toString());
	}

}
