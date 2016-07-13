/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.core;

import java.sql.ResultSet;

import cs525.project.fujframework.core.dataaccess.DbAction;
import cs525.project.fujframework.core.dataaccess.DbActionImpl;
import cs525.project.fujframework.middleware.SysUser;
import cs525.project.fujframework.utils.DbHelper;

/**
 * facade pattern implementation of SysUser
 * 
 * @author paudelumesh
 *
 * @version 1.0.0
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.SysUserFacade#saveSysUser(cs525.project.
	 * fujframework.middleware.SysUser)
	 */
	@Override
	public int saveSysUser(SysUser sysUser) {
		if (sysUser.getSysuserId() > 0)
			return this.dbaction.update(DbHelper.getUpdateQuery(sysUser));
		return this.dbaction.Create(DbHelper.getInsertQuery(sysUser));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.SysUserFacade#removeSysUser(cs525.project
	 * .fujframework.middleware.SysUser)
	 */
	@Override
	public int removeSysUser(SysUser sysUser) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("DELETE FROM sysuser WHERE sysUserId=" + sysUser.getPersonId());
		return this.dbaction.delete(queryBuilder.toString());
	}

	@Override
	public ResultSet getAllUsers(Class<?> tableName) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT * FROM " + tableName.getSimpleName());
		return this.dbaction.read(queryBuilder.toString());
	}

}
