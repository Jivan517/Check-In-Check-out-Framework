/**
 * 
 */
package cs525.project.fujframework.core;

/**
 * defines an address entity
 * 
 * @author paudelumesh
 *
 * @version 1.0.0
 */
public class Address {
	private String streetAddress;
	private String city;
	private int zipCode;
	private String state;

	/**
	 * creates an address object
	 * 
	 * @param streetAddress
	 * @param city
	 * @param zipCode
	 * @param state
	 */
	public Address(String streetAddress, String city, int zipCode, String state) {
		super();
		this.streetAddress = streetAddress;
		this.city = city;
		this.zipCode = zipCode;
		this.state = state;
	}

	/**
	 * @return the streetAddress
	 */
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the zipCode
	 */
	public int getZipCode() {
		return zipCode;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

}
