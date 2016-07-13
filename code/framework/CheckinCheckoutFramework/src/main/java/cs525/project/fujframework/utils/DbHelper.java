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

import cs525.project.fujframework.core.Address;

/**
 * helper class for to genereate insertion query and the update query using
 * object on the fly
 * 
 * @author paudelumesh
 *
 * @version 1.0.0
 */
public class DbHelper {
	/**
	 * generates the insertion query
	 * 
	 * @param object
	 * 
	 * @return insertion query as string
	 */
	public static String getInsertQuery(Object object) {

		List<String> fields = new ArrayList<>();
		List<String> values = new ArrayList<>();

		List<Field> fieldsCollection = ReflectionUtil.getAllFields(new ArrayList<Field>(), object.getClass());
		for (Field field : fieldsCollection) {
			// may sometimes need to make the modifier accessible
			field.setAccessible(true);
			Object value = null;
			try {

				value = field.get(object);

				if (field.getName().contains("Id") && !field.getName().contains("RefId")) {
					// left empty
				} else if (field.getType().isAssignableFrom(String.class)) {
					fields.add(field.getName());
					values.add("'" + value + "'");
				} else if (field.getType().isAssignableFrom(Address.class)) {
					// left empty
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
	 * generates the update query
	 * 
	 * @param object
	 * 
	 * @return update query as string
	 */
	public static String getUpdateQuery(Object object) {
		StringBuilder query = new StringBuilder();
		int idValue = 0;
		query.append("UPDATE " + object.getClass().getSimpleName() + " SET ");
		List<Field> fieldsCollection = ReflectionUtil.getAllFields(new ArrayList<Field>(), object.getClass());
		List<String> fields = new ArrayList<>();

		String primaryKeyField = object.getClass().getSimpleName() + "Id";
		for (Field field : fieldsCollection) {
			// may sometimes need to make the modifier accessible
			field.setAccessible(true);
			Object value = null;
			try {

				value = field.get(object);
				if (field.getName().equals(object.getClass().getSimpleName() + "Id")
						|| (field.getName().contains("Id") && !field.getName().contains("RefId"))) {
					idValue = (int) value;
					primaryKeyField = field.getName();
				} else if (field.getType().isAssignableFrom(String.class)) {
					fields.add(field.getName() + " = '" + value + "'");
				} else if (field.getType().isAssignableFrom(Address.class)) {
					// left empty
				} else {
					if (value == null) {
						fields.add(field.getName() + " = NULL");
					} else {
						fields.add(field.getName() + " = " + value.toString());
					}
				}

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}

		String fieldQuery = fields.stream().collect(Collectors.joining(","));
		query.append(fieldQuery);
		query.append(" WHERE " + primaryKeyField + "= " + idValue);
		return query.toString();
	}

}
