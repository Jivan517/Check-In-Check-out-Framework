/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.core.dataaccess;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
/**
 *  Database configuration properties 
 * @author Fish
 *
 */

public class DbConfigProperties {
	private static final String PROPERTIES = "dbconfig.properties";
	private static final String PROPS = PROPERTIES;
		//System.getProperty("user.dir") + "/" + PROPERTIES;
	private static Properties props;
	
	static {
		readProps();
	}
	/**
	 * 
	 * @param key
	 * @return props
	 */
	public String getProperty(String key) {
		System.out.println(props);
		return props.getProperty(key);
		
	}
	/**
	 * 
	 */
	private static void readProps() {
		readProps(PROPS);
		
	}
	
	/**
	 * This method allows a client of this properties configurator
	 * to point to a different location for the properties file.
	 * @param propsLoc
	 */
	public static void readProps(String loc) {
		System.out.println(loc);
		Properties ret = new Properties();
            URL url = DbConfigProperties.class.getClassLoader().
                    getResource(loc);
            
            try {
                ret.load(url.openStream()); 
            } catch(IOException e) {
                //LOG.warning("Unable to read properties file for Ebazaar");
            } finally {
                props = ret;
            }

	}
	
	
	
	
}
