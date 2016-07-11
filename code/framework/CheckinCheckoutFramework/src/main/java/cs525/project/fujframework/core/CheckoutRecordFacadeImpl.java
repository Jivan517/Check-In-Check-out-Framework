/**
 * 
 */
package cs525.project.fujframework.core;

import java.sql.ResultSet;
import java.time.LocalDate;

import cs525.project.fujframework.core.dataaccess.DbAction;
import cs525.project.fujframework.core.dataaccess.DbActionImpl;
import cs525.project.fujframework.utils.DbHelper;

/**
 * @author paudelumesh
 *
 */
public class CheckoutRecordFacadeImpl implements CheckoutRecordFacade {
	private DbAction dbaction;
	StringBuilder queryBuilder;

	/**
	 * 
	 */
	public CheckoutRecordFacadeImpl() {
		this.dbaction = new DbActionImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.CheckoutRecordFacade#saveCheckoutRecord(
	 * cs525.project.fujframework.core.CheckoutRecordEntry)
	 */
	@Override
	public int saveCheckoutRecord(CheckoutRecordEntry checkoutRecordEntry) {
		return this.dbaction.Create(DbHelper.getInsertQuery(checkoutRecordEntry));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.CheckoutRecordFacade#removeCheckoutRecord
	 * (cs525.project.fujframework.core.CheckoutRecordEntry)
	 */
	@Override
	public int removeCheckoutRecord(CheckoutRecordEntry checkoutRecordEntry) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("DELETE FROM checkoutrecord WHERE customerId=" + checkoutRecordEntry.getRecordId());
		return this.dbaction.delete(queryBuilder.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.CheckoutRecordFacade#checkInRecord(cs525.
	 * project.fujframework.core.CheckoutRecordEntry)
	 */
	@Override
	public int checkInRecord(CheckoutRecordEntry checkoutRecordEntry) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("UPDATE checkoutrecord SET isReturned=true, returnedDate=" + LocalDate.now()
				+ " WHERE customerId=" + checkoutRecordEntry.getRecordId());
		return this.dbaction.update(queryBuilder.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.CheckoutRecordFacade#undoCheckIn(cs525.
	 * project.fujframework.core.CheckoutRecordEntry)
	 */
	@Override
	public int undoCheckIn(CheckoutRecordEntry checkoutRecordEntry) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("UPDATE checkoutrecord SET isReturned=false, returnedDate=NULL" + " WHERE customerId="
				+ checkoutRecordEntry.getRecordId());
		return this.dbaction.update(queryBuilder.toString());
	}

}
