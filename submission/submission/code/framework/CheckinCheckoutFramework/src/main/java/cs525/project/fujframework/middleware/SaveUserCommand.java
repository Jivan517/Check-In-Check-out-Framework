/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

import cs525.project.fujframework.core.SysUserFacade;
import cs525.project.fujframework.core.SysUserFacadeImpl;

/**
 * concrete command for user save operation
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 *
 */
public class SaveUserCommand implements Command {

	private SysUserFacade facade;
	private SysUser user;

	public SaveUserCommand(SysUser user) {
		this.user = user;
		this.facade = new SysUserFacadeImpl();
	}

	@Override
	public boolean execute() {
		int affectedRows = facade.saveSysUser(user);
		return affectedRows == 1 ? true : false;
	}

	@Override
	public boolean undo() {
		int affectedRows = facade.removeSysUser(user);
		return affectedRows == 1 ? true : false;
	}

}
