/**
 * 
 */
package cs525.project.fujframework.middleware;

import java.util.List;

import cs525.project.fujframework.core.CheckoutRecordEntry;

/**
 * Implementation of template method pattern for transaction processing steps
 * 
 * @author paudelumesh
 *
 * @version 1.0.0
 */
public abstract class TransactionManager {
	/**
	 * 
	 * @param checkoutRecordEntry
	 */
	public final void proceedTransaction(List<CheckoutRecordEntry> checkoutRecordEntries, Class<?> productClass) {
		List<CheckoutRecordEntry> rentalFeeFine = calculateRentalFeeOrOverdueFine(checkoutRecordEntries, productClass);
		processCheckoutRecord(checkoutRecordEntries);
		// sendNotification(checkoutRecordEntries, productClass);
		printBill(rentalFeeFine);
	}

	/**
	 * calculates the rental fee or overdue fine whichever is applicable
	 * 
	 * @param checkoutRecordEntries
	 * @param productClass
	 * @return list of updated checkout record entries
	 */
	protected abstract List<CheckoutRecordEntry> calculateRentalFeeOrOverdueFine(
			List<CheckoutRecordEntry> checkoutRecordEntries, Class<?> productClass);

	/**
	 * processes the checkout record entries to insert it into database
	 * 
	 * @param checkoutRecordEntries
	 */
	protected abstract void processCheckoutRecord(List<CheckoutRecordEntry> checkoutRecordEntries);

	/**
	 * sends the notification to customers
	 * 
	 * @param checkoutRecordEntries
	 * @param productClass
	 */
	protected abstract void sendNotification(List<CheckoutRecordEntry> checkoutRecordEntries, Class<?> productClass);

	/**
	 * prints the bill by going through each of the checkout record entries
	 * 
	 * @param rentalFeeFine
	 */
	protected void printBill(List<CheckoutRecordEntry> checkoutRecordEntries) {
		StringBuilder builder = new StringBuilder();
		builder.append("--------------------------------------- \n");
		builder.append("========Rental System Bill========= \n");
		for (CheckoutRecordEntry checkoutRecordEntry : checkoutRecordEntries) {
			builder.append("Your total Rental Fee is : " + checkoutRecordEntry.getRentalFee() + "\n");
			builder.append("Your total Rental Fine is : " + checkoutRecordEntry.getRentalFine() + "\n");
		}
		builder.append("---------------------------------------");

		System.out.println(builder.toString());
	}
}
