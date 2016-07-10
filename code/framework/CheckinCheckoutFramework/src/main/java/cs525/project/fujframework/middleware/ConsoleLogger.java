package cs525.project.fujframework.middleware;

public class ConsoleLogger extends LoggerDecorator {

	public ConsoleLogger(Logger logger) {
		super(logger);
	}

	@Override
	public String warn(String message) {
		System.out.println(message);
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
