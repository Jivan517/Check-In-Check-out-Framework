package cs525.project.fujframework.utils;

public class DbConfig {
	private ConfigProperties config;

	public DbConfig() {
		this.config = new ConfigPropertiesImpl("dbconfig.properties");
	}

	public String getDbUrl() {
		return config.readProperty("dburl");
	}
	public int getMaxConnection(){
		return Integer.parseInt(config.readProperty("max_connections"));
	}
	
	public String getDriver(){
		return config.readProperty("driver");
	}
	public String getUserName(){
		return config.readProperty("dbuser");
	}
	public String getDbPassword(){
		return config.readProperty("dbpassword");
	}
}
