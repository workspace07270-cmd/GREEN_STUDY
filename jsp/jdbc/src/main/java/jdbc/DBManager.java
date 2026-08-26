package jdbc;

/**
 * Compatibility wrapper so code in the `jdbc` package that calls DBManager
 * can continue to use the simple name. This delegates to the real
 * config.DBManager singleton.
 */
public class DBManager {
	public static config.DBManager getInstance() {
		return config.DBManager.getInstance();
	}
}
