/**
 * 
 */
package cs525.project.fujframework.core;

import java.sql.ResultSet;

import cs525.project.fujframework.core.dataaccess.DbAction;
import cs525.project.fujframework.core.dataaccess.DbActionImpl;

/**
 * @author paudelumesh
 *
 */
public class CheckoutRecordFacadeImpl implements CheckoutRecordFacade {
	private DbAction dbaction;
	
	/**
	 * 
	 */
	public CheckoutRecordFacadeImpl() {
		this.dbaction = new DbActionImpl();
	}

	/* (non-Javadoc)
	 * @see cs525.project.fujframework.core.CheckoutRecordFacade#saveCheckoutRecord(cs525.project.fujframework.core.CheckoutRecordEntry)
	 */
	@Override
	public int saveCheckoutRecord(CheckoutRecordEntry checkoutRecordEntry) {
		String query = "";
		return dbaction.Create(query);
	}

	/* (non-Javadoc)
	 * @see cs525.project.fujframework.core.CheckoutRecordFacade#removeCheckoutRecord(cs525.project.fujframework.core.CheckoutRecordEntry)
	 */
	@Override
	public int removeCheckoutRecord(CheckoutRecordEntry checkoutRecordEntry) {
		String query = "";
		return dbaction.delete(query);
	}

	/* (non-Javadoc)
	 * @see cs525.project.fujframework.core.CheckoutRecordFacade#checkInRecord(cs525.project.fujframework.core.CheckoutRecordEntry)
	 */
	@Override
	public boolean checkInRecord(CheckoutRecordEntry checkoutRecordEntry) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see cs525.project.fujframework.core.CheckoutRecordFacade#undoCheckIn(cs525.project.fujframework.core.CheckoutRecordEntry)
	 */
	@Override
	public boolean undoCheckIn(CheckoutRecordEntry checkoutRecordEntry) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
