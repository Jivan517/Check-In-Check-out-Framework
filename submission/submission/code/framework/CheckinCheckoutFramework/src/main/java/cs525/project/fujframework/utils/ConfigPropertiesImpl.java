package cs525.project.fujframework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigPropertiesImpl implements ConfigProperties {

	private Properties prop = new Properties();
	private InputStream input = null;

	public ConfigPropertiesImpl(String fileName) {
		try {
			input = new FileInputStream(fileName);
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String readProperty(String propertyName) {
		return prop.getProperty(propertyName);
	}

}
