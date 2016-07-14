/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.utils;

import java.util.HashMap;

/**
 * Standalone version of a session context. Intended to provide a simple
 * cache for the app during execution.
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 *
 */
public class SessionCache {

	// private
	private static SessionCache instance = new SessionCache();
	private HashMap<Object, Object> context;

	// public interface
	public static SessionCache getInstance() {
		return instance;

	}

	public void add(Object name, Object value) {
		if (context != null) {
			context.put(name, value);
		}
	}

	public Object get(Object name) {
		if (context == null) {
			return null;
		}
		return context.get(name);
	}

	public void remove(Object name) {
		context.remove(name);
	}

	private SessionCache() {
		context = new HashMap<Object, Object>();
		context.put(BusinessConstants.LOGGED_IN, Boolean.FALSE);
	}
}
