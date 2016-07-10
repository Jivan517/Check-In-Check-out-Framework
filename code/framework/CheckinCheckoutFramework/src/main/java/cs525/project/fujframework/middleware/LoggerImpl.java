package cs525.project.fujframework.middleware;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class LoggerImpl implements Logger {
	private Properties prop = new Properties();
	private InputStream input = null;

	public LoggerImpl() {
		try {
			input = new FileInputStream("logger.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		// get the property value and print it out
		System.out.println(prop.getProperty("database"));
		System.out.println(prop.getProperty("dbuser"));
		System.out.println(prop.getProperty("dbpassword"));
	}

	@Override
	public String warn(String message) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		return "WARN[" + dateFormat.format(date) + "] " + message;
	}

	@Override
	public String debug(String message) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		return "DEBUG[" + dateFormat.format(date) + "] " + message;
	}

	@Override
	public String info(String message) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		return "INFO[" + dateFormat.format(date) + "] " + message;
	}

	@Override
	public String error(String message) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		return "ERROR[" + dateFormat.format(date) + "] " + message;
	}

}
