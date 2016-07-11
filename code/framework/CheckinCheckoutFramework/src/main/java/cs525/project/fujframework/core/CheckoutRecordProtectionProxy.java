/**
 * 
 */
package cs525.project.fujframework.core;

import cs525.project.fujframework.utils.LoginUtil;

/**
 * This @{CheckOutRecordProtectionProxy} class implements @{CheckoutRecordFacade} interface
 * acts like protection Proxy to provide access control for appropriate user.
 * @author Fish
 * @version 1.0.0
 *
 */
public class CheckoutRecordProtectionProxy implements CheckoutRecordFacade {
	
    private CheckoutRecordFacade checkOutRecordFacade;
    private boolean isAdmin=false;
    public CheckoutRecordProtectionProxy() {
    	this.checkOutRecordFacade = new CheckoutRecordFacadeImpl();
    	isAdmin = LoginUtil.isAdmin();
    
	}
	/* (non-Javadoc)
	 * @see cs525.project.fujframework.core.CheckoutRecordFacade#saveCheckoutRecord(cs525.project.fujframework.core.CheckoutRecordEntry)
	 */
	@Override
	public int saveCheckoutRecord(CheckoutRecordEntry checkoutRecordEntry) {
		if(isAdmin){
			return checkOutRecordFacade.saveCheckoutRecord(checkoutRecordEntry);
		}
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see cs525.project.fujframework.core.CheckoutRecordFacade#removeCheckoutRecord(cs525.project.fujframework.core.CheckoutRecordEntry)
	 */
	@Override
	public int removeCheckoutRecord(CheckoutRecordEntry checkoutRecordEntry) {
		if(isAdmin){
			return checkOutRecordFacade.removeCheckoutRecord(checkoutRecordEntry);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see cs525.project.fujframework.core.CheckoutRecordFacade#checkInRecord(cs525.project.fujframework.core.CheckoutRecordEntry)
	 */
	@Override
	public int checkInRecord(CheckoutRecordEntry checkoutRecordEntry) {
		if(isAdmin){
			return checkOutRecordFacade.checkInRecord(checkoutRecordEntry);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see cs525.project.fujframework.core.CheckoutRecordFacade#undoCheckIn(cs525.project.fujframework.core.CheckoutRecordEntry)
	 */
	@Override
	public int undoCheckIn(CheckoutRecordEntry checkoutRecordEntry) {
		if(isAdmin){
			return checkOutRecordFacade.undoCheckIn(checkoutRecordEntry);
		}
		return 0;
	}

}
