/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.core;

import java.sql.ResultSet;

import cs525.project.fujframework.core.dataaccess.DbAction;
import cs525.project.fujframework.core.dataaccess.DbActionImpl;
import cs525.project.fujframework.utils.DbHelper;

/**
 * facade pattern implementation of product
 * 
 * @author paudelumesh
 * 
 * @version 1.0.0
 */
public class ProductFacadeImpl implements ProductFacade {
	private DbAction dbaction;
	StringBuilder queryBuilder;

	/**
	 * 
	 */
	public ProductFacadeImpl() {
		this.dbaction = new DbActionImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.ProductFacade#saveProduct(cs525.project.
	 * fujframework.core.Product)
	 */
	@Override
	public int saveProduct(Product product) {
		if (product.getProductId() > 0)
			return this.dbaction.update(DbHelper.getUpdateQuery(product));
		return this.dbaction.Create(DbHelper.getInsertQuery(product));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.ProductFacade#removeProduct(cs525.project
	 * .fujframework.core.Product)
	 */
	@Override
	public int removeProduct(Product product) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("DELETE FROM product WHERE productId=" + product.getProductId());
		return this.dbaction.delete(queryBuilder.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.core.ProductFacade#getProductById(int)
	 */
	@Override
	public ResultSet getProductById(int productId, Class<?> tableName) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT * FROM " + tableName.getSimpleName() + " where productId = " + productId);
		return this.dbaction.read(queryBuilder.toString());
	}

	@Override
	public ResultSet getAllProduct(Class<?> tableName) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT * FROM " + tableName.getSimpleName());
		return this.dbaction.read(queryBuilder.toString());
	}

}
