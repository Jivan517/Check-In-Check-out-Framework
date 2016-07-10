package cs525.project.fujframework.middleware;

public abstract class LoggerDecorator implements Logger {

	protected Logger loggerToBeDecorated;

	public LoggerDecorator(Logger logger) {
		this.loggerToBeDecorated = logger;
	}

	@Override
	public String warn(String message) {
		return loggerToBeDecorated.warn(message);
	}

	@Override
	public String debug(String message) {
		return loggerToBeDecorated.debug(message);
	}

	@Override
	public String info(String message) {
		return loggerToBeDecorated.info(message);
	}

	@Override
	public String error(String message) {
		return loggerToBeDecorated.error(message);
	}

}
