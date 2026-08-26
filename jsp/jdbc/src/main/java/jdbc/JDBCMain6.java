package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import config.DBManager;

public class JDBCMain6 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 이름 일부 : ");
		String name = sc.nextLine();

		String sql = "select * from students where name like ?";

		// 검색어가 비어있으면 모든 레코드를 조회하도록 '%' 사용
		String param = (name == null || name.trim().isEmpty()) ? "%" : ("%" + name.trim() + "%");

		try (PreparedStatement pstmt = DBManager.getInstance().getConn().prepareStatement(sql)) {
			pstmt.setString(1, param);
			try (ResultSet rs = pstmt.executeQuery()) {
				boolean found = false;
				while (rs.next()) {
					found = true;
					System.out.println(rs.getString(1) + "/" + rs.getString(2) + "/" + rs.getString(3) + "/" + rs.getDouble(4));
				}
				if (!found) {
					System.out.println("검색 결과가 없습니다.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}
