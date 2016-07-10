package cs525.project.fujframework.middleware;

public class DbLogger extends LoggerDecorator {

	public DbLogger(Logger logger) {
		super(logger);
	}

}
