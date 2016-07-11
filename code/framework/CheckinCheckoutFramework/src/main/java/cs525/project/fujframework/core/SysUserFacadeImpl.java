/**
 * 
 */
package cs525.project.fujframework.core;

import cs525.project.fujframework.core.dataaccess.DbAction;
import cs525.project.fujframework.core.dataaccess.DbActionImpl;
import cs525.project.fujframework.middleware.SysUser;

/**
 * @author paudelumesh
 *
 */
public class SysUserFacadeImpl implements SysUserFacade {
	private DbAction dbaction;
	StringBuilder queryBuilder;
	
	/**
	 * 
	 */
	public SysUserFacadeImpl() {
		this.dbaction = new DbActionImpl();
	}

	/* (non-Javadoc)
	 * @see cs525.project.fujframework.core.SysUserFacade#saveSysUser(cs525.project.fujframework.middleware.SysUser)
	 */
	@Override
	public int saveSysUser(SysUser sysUser) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("");
		return this.dbaction.Create(queryBuilder.toString());
	}

	/* (non-Javadoc)
	 * @see cs525.project.fujframework.core.SysUserFacade#removeSysUser(cs525.project.fujframework.middleware.SysUser)
	 */
	@Override
	public int removeSysUser(SysUser sysUser) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("");
		return this.dbaction.delete(queryBuilder.toString());
	}

}
