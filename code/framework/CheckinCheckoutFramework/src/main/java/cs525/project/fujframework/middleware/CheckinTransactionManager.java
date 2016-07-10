/**
 * 
 */
package cs525.project.fujframework.middleware;

import java.util.List;

import cs525.project.fujframework.core.CheckoutRecordEntry;

/**
 * @author paudelumesh
 *
 */
public class CheckinTransactionManager extends TransactionManager {
	private CommandManager command;

	/**
	 * 
	 */
	public CheckinTransactionManager() {
		command = new CommandManagerImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.middleware.TransactionManager#
	 * calculateRentalFeeOrOverdueFine(java.util.List)
	 */
	@Override
	protected List<CheckoutRecordEntry> calculateRentalFeeOrOverdueFine(
			List<CheckoutRecordEntry> checkoutRecordEntries) {

		return null;
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

}
