/**
 * 
 */
package cs525.project.fujframework.core;

import cs525.project.fujframework.core.dataaccess.DbAction;
import cs525.project.fujframework.core.dataaccess.DbActionImpl;
import cs525.project.fujframework.middleware.Customer;
import cs525.project.fujframework.utils.DbHelper;

/**
 * @author paudelumesh
 *
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
		return this.dbaction.Create(DbHelper.getInsertQuery(customer));
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

}
