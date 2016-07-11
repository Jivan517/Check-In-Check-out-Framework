/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.core;

import cs525.project.fujframework.core.dataaccess.DbAction;
import cs525.project.fujframework.core.dataaccess.DbActionImpl;
import cs525.project.fujframework.utils.DbHelper;

/**
 * @author paudelumesh
 *
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
	public Product getProductById(int productId) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT * FROM product where productId = " + productId);
		return (Product) this.dbaction.read(queryBuilder.toString());
	}

}
