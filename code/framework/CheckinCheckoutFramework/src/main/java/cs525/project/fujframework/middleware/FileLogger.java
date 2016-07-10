package cs525.project.fujframework.middleware;

import cs525.project.fujframework.utils.ConfigProperties;
import cs525.project.fujframework.utils.ConfigPropertiesImpl;

public class FileLogger extends LoggerDecorator {

	private ConfigProperties config;

	public FileLogger(Logger logger) {
		super(logger);
		config = new ConfigPropertiesImpl("logger.properties");

	}

	@Override
	public String warn(String message) {
		String fileName = config.readProperty("logFile");
		return super.warn(message);
	}

	@Override
	public String debug(String message) {
		System.out.println(message);
		return super.debug(message);

	}

	@Override
	public String info(String message) {
		System.out.println(message);
		return super.info(message);
	}

	@Override
	public String error(String message) {
		System.out.println(message);
		return super.error(message);
	}
}
