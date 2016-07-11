/**
 * 
 */
package cs525.project.fujframework.core;

/**
 * provides the higher-level interface for product subsystems
 * 
 * @author paudelumesh
 * 
 * @version 1.0.0
 */
public interface ProductFacade {
	/**
	 * saves the product into database
	 * 
	 * @param product
	 * @return int
	 */
	public int saveProduct(Product product);

	/**
	 * removes the product from database
	 * 
	 * @param product
	 * @return int
	 */
	public int removeProduct(Product product);

	/**
	 * returns the product based on product id
	 * 
	 * @param productId
	 * @return the product object
	 */
	public Product getProductById(int productId);
}
