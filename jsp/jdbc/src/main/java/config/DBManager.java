package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DB 접속 정보를 가지고 있는 객체
// 언제든지 필요할 떄마다 Connection을 전달
// Connection을 받은 곳에서 Statement 생성해서 DB 제어
public class DBManager {
	private static DBManager instance = new DBManager();
	private Connection conn;
	
	private DBManager() {
		try {
			// conn에 DB 접속 정보 생성
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db",
					"root","12345678");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
		return conn;
	}
	
	public static DBManager getInstance() {
		if(instance == null)
			instance = new DBManager();
		return instance;
	}
	
	
}
