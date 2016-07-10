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
public abstract class TransactionManager {
	/**
	 * 
	 * @param checkoutRecordEntry
	 */
	public final void proceedTransaction(List<CheckoutRecordEntry> checkoutRecordEntries) {
		List<CheckoutRecordEntry> rentalFeeFine = calculateRentalFeeOrOverdueFine(checkoutRecordEntries);
		processCheckoutRecord(checkoutRecordEntries);
		printBill(rentalFeeFine);
	}

	protected abstract List<CheckoutRecordEntry> calculateRentalFeeOrOverdueFine(
			List<CheckoutRecordEntry> checkoutRecordEntries);

	protected abstract void processCheckoutRecord(List<CheckoutRecordEntry> checkoutRecordEntries);

	protected void printBill(List<CheckoutRecordEntry> rentalFeeFine) {
		StringBuilder builder = new StringBuilder();
		builder.append("---------------------------------------");
		builder.append("========Rental System Bill=========");
		for (CheckoutRecordEntry checkoutRecordEntry : rentalFeeFine) {
			builder.append("Your total Rental Fee is : " + checkoutRecordEntry.getRentalFee());
			builder.append("Your total Rental Fine is : " + checkoutRecordEntry.getRentalFine());
		}
		builder.append("---------------------------------------");
	}
}
