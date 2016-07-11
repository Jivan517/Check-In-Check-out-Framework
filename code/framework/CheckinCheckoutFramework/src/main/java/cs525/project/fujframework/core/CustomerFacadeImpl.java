/**
 * 
 */
package cs525.project.fujframework.core;

import cs525.project.fujframework.core.dataaccess.DbAction;
import cs525.project.fujframework.core.dataaccess.DbActionImpl;
import cs525.project.fujframework.middleware.Customer;

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
		queryBuilder = new StringBuilder();
		String fullName = customer.getFirstName() + " " + customer.getMiddleName() + " " + customer.getLastName();
		queryBuilder.append("INSERT INTO customer(customerId, customerName, address)");
		queryBuilder.append(" values(" + customer.getPersonId() + "," + fullName + "," + customer.getAddress());
		return this.dbaction.Create(queryBuilder.toString());
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
		queryBuilder.append("");
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
