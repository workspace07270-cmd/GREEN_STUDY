package jdbc;
//데이터추가
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCMain2 {
	public static final String DB_USER="root";
	public static final String DB_PASSWD = "12345678";
	
	public static void main(String[] args) {
		//DriverClass Loading
		//1. DB 접속
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/car_db?"
					+ "user="+ DB_USER+ "& password="+ DB_PASSWD);	
			System.out.println("DB 접속완료");
			
		//2. SQL을 실행할 객체 Statement 생성
		try (Statement stmt = conn.createStatement()){
		
		//3.SQL문 생성
		String sql = "insert into cars(brand,model,year,mileage,price,registered_at) "
				+ "values('현대','소렌토',2023,15000,25000000,'2026-01-01 00:00:00')";
		//4. SQL문 실행
		int rows = stmt.executeUpdate(sql);
		System.out.println(rows + "건 적용되었습니다.");
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}