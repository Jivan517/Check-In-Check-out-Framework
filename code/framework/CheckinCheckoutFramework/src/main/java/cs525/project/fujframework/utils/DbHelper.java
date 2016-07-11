/**
 * Copyright 2016 the original author or authors.
 * 
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author paudelumesh
 *
 */
public class DbHelper {
	/**
	 * 
	 * @param object
	 * @return
	 */
	public static String getInsertQuery(Object object) {

		List<String> fields = new ArrayList<>();
		List<String> values = new ArrayList<>();

		for (Field field : object.getClass().getDeclaredFields()) {
			// may sometimes need to make the modifier accessible
			field.setAccessible(true);
			Object value = null;
			try {

				value = field.get(object);

				if (field.getType().isAssignableFrom(String.class)) {
					fields.add(field.getName());
					values.add("'" + value + "'");
				} else {
					fields.add(field.getName());
					if (value == null)
						values.add("NULL");
					else
						values.add(value.toString());
				}

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}

		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO " + object.getClass().getSimpleName() + "(");

		String fieldQuery = fields.stream().collect(Collectors.joining(","));
		String valueQuery = values.stream().collect(Collectors.joining(","));

		query.append(fieldQuery);
		query.append(") VALUES(");
		query.append(valueQuery);
		query.append(")");

		return query.toString();
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	public static String getUpdateQuery(Object object) {
		StringBuilder query = new StringBuilder();
		int idValue = 0;
		query.append("UPDATE " + object.getClass().getSimpleName() + " SET ");

		for (Field field : object.getClass().getDeclaredFields()) {
			// may sometimes need to make the modifier accessible
			field.setAccessible(true);
			Object value = null;
			try {

				value = field.get(object);
				if (field.getName().equals(object.getClass().getSimpleName() + "Id"))
					idValue = (int) value;

				if (field.getType().isAssignableFrom(String.class)) {
					query.append(field.getName() + " = '" + value + "'");
				} else {
					if (value == null)
						query.append(field.getName() + " = NULL");
					else
						query.append(field.getName() + " = " + value.toString());
				}

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
		query.append(" WHERE " + object.getClass().getSimpleName() + "Id = " + idValue);
		return query.toString();
	}

}
