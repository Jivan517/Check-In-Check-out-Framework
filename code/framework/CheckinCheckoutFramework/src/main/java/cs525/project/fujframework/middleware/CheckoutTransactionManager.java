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
public class CheckoutTransactionManager extends TransactionManager {
	private CommandManager command;

	/**
	 * 
	 */
	public CheckoutTransactionManager() {
		command = new CommandManagerImpl();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.middleware.TransactionManager#
	 * calculateRentalFee(java.util.List)
	 */
	@Override
	protected List<CheckoutRecordEntry> calculateRentalFeeOrOverdueFine(
			List<CheckoutRecordEntry> checkoutRecordEntries) {
		return null;
	}

}
