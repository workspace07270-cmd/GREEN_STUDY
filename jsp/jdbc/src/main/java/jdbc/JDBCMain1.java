package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//데이터확인
public class JDBCMain1 {
	public static final String DB_USER="root";
	public static final String DB_PASSWD = "12345678";
	
	public static void main(String[] args) {
		//DriverClass Loading
		//1. DB 접속
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/car_db?"
					+ "user="+ DB_USER+ "&password="+ DB_PASSWD);	
			System.out.println("DB 접속완료");
			
		//2. SQL을 실행할 객체 Statement 생성
		try (Statement stmt = conn.createStatement()){
		
		//3.SQL문 생성
		String sql = "select * from cars";
		//4. SQL문 실행
		try(ResultSet rs = stmt.executeQuery(sql)){
			//5.데이터 확인
		while(rs.next()) {
			System.out.println(rs.getInt(1)
					+ " | " + rs.getString(2)
					+ " | " + rs.getString(3)
					+ " | " + rs.getInt(4)
					+ " | " + rs.getInt(5)
					+ " | " + rs.getInt(6)
					+ " | " + rs.getString(7));
				System.out.println(rs.getInt("car_id")
						  +","+ rs.getString("model"));
				}	
			}
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}