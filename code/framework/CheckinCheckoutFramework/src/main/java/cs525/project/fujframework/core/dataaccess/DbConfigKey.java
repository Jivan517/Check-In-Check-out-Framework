package cs525.project.fujframework.core.dataaccess;

public enum DbConfigKey {
	DB_URL("dburl"), 
	MAX_CONNECTIONS("max_connections"),
	DB_USER("dbuser"),
	DB_PASSWORD("dbpassword"),
	DRIVER("driver");
	
	private String val;
	private DbConfigKey(String val) {
		this.val = val;
	}
	public String getVal() {
		return val;
	}
}
