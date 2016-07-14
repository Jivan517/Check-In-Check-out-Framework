/**
 * 
 */
package cs525.project.fujframework.middleware;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
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
public class CheckinTransactionManager extends TransactionManager {
	private CommandManager command;
	private ProductFacade productFacade;
	private CustomerFacade customerFacade;
	private Notifier notifier;

	/**
	 * 
	 */
	public CheckinTransactionManager() {
		command = new CommandManagerImpl();
		productFacade = new ProductFacadeImpl();
		customerFacade = new CustomerFacadeImpl();
		notifier = new Notifier(new EmailNotificationStrategy());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.middleware.TransactionManager#
	 * calculateRentalFeeOrOverdueFine(java.util.List)
	 */
	@Override
	protected List<CheckoutRecordEntry> calculateRentalFeeOrOverdueFine(List<CheckoutRecordEntry> checkoutRecordEntries,
			Class<?> productClass) {
		/*
		 * for (CheckoutRecordEntry checkoutRecordEntry : checkoutRecordEntries)
		 * { long loanDays =
		 * java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(),
		 * checkoutRecordEntry.getDueDate()); ResultSet rs =
		 * productFacade.getProductById(checkoutRecordEntry.getProductRefId(),
		 * productClass); double fine = 0; try { while (rs.next()) { fine =
		 * rs.getDouble("overdueFinePerDay"); } } catch (SQLException e) {
		 * e.printStackTrace(); }
		 * 
		 * double rentalFine = loanDays * fine;
		 * checkoutRecordEntry.setRentalFine(rentalFine); }
		 */
		return checkoutRecordEntries;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.middleware.TransactionManager#
	 * processCheckoutRecord(java.util.List)
	 */
	@Override
	protected void processCheckoutRecord(List<CheckoutRecordEntry> checkoutRecordEntries) {
		command.checkBackRecordsIn(checkoutRecordEntries);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.middleware.TransactionManager#sendNotification
	 * (java.util.List)
	 */
	@Override
	protected void sendNotification(List<CheckoutRecordEntry> checkoutRecordEntries, Class<?> prodClass) {
		double totalFine = 0;
		int customerId = checkoutRecordEntries.get(0).getCustomerRefId();
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

		for (CheckoutRecordEntry checkoutRecordEntry : checkoutRecordEntries) {
			totalFine += checkoutRecordEntry.getRentalFine();
		}

		StringBuilder message = new StringBuilder();
		message.append("Your total fine for the rented items is " + totalFine);

		notifier.notifyPerson(message.toString(), customer);
	}

}
