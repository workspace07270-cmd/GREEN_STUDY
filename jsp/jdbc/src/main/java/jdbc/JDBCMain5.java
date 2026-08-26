package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBManager;

public class JDBCMain5 {

	public static void main(String[] args) {
		String sql = "select * from students";
		// 1. PreparedStatement 생성
		try(PreparedStatement pstmt = 
				DBManager.getInstance().getConn().prepareStatement(sql)){
			// 2. SQL 실행
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					System.out.println(rs.getString(1) + "/" + rs.getString(2)
					+ "/" + rs.getString(3) + "/" + rs.getDouble(4));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}






