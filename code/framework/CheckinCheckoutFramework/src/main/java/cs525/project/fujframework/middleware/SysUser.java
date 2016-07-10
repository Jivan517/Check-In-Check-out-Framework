/**
 * 
 */
package cs525.project.fujframework.middleware;

import cs525.project.fujframework.core.Address;

/**
 * @author paudelumesh
 *
 */
public class SysUser implements Person {
	private String firstName;
	private String lastName;
	private String middleName;
	private Address address;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.Person#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.core.Person#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.core.Person#setLastName(java.lang.String)
	 */
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.core.Person#getLastName()
	 */
	@Override
	public String getLastName() {
		return lastName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.Person#setMiddleName(java.lang.String)
	 */
	@Override
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.core.Person#getMiddleName()
	 */
	@Override
	public String getMiddleName() {
		return middleName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.core.Person#setAddress(cs525.project.
	 * fujframework.core.Address)
	 */
	@Override
	public void setAddress(Address address) {
		this.address = address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.core.Person#getAddress()
	 */
	@Override
	public Address getAddress() {
		return address;
	}

}