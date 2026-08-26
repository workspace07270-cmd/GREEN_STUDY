package mybatis1.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * MyBatis를 이용해 데이터베이스 연결을 관리하는 클래스입니다.
 * 직접 JDBC를 다루는 대신, MyBatis 설정을 읽어와서 DB와 대화할 수 있는 세션(SqlSession)을 열어줍니다.
 */
public class DBManager {
	private static DBManager instance = new DBManager();

	// DB와 대화하는 공장을 관리합니다.
	private SqlSessionFactory sqlSessionFactory;
	
	private DBManager() {
		// MyBatis 설정 파일의 위치를 알려줍니다.
		String resource = "mybatis1/config/mybatis-config.xml";
		InputStream inputStream;
		try {
			// 설정 파일을 읽어와서 '공장(SqlSessionFactory)'을 짓습니다.
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 실제로 DB와 대화를 시작하기 위한 세션(SqlSession)을 하나 꺼냅니다.
	 * @return DB 작업을 수행할 수 있는 SqlSession 객체
	 */
	public SqlSession getSession() {
		// true를 넣으면 쿼리 실행 후 자동으로 DB에 반영(commit)됩니다.
		return sqlSessionFactory.openSession(true);
	}
	
	public static DBManager getInstance() {
		if(instance == null)
			instance = new DBManager();
		return instance;
	}
}