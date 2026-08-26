package config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * MyBatis를 이용해 데이터베이스 연결(세션)을 관리하는 클래스입니다.
 * 싱글톤 패턴을 사용하여 DB 연결 설정을 한 번만 로드하고 재사용합니다.
 */
public class DBManager {
	// 1. 유일한 인스턴스를 관리합니다.
	private static DBManager instance = new DBManager();

	// DB와 대화하는 공장 역할을 하는 객체입니다.
	private SqlSessionFactory sqlSessionFactory;
	
	private DBManager() {
		// MyBatis 설정 파일(mybatis-config.xml)의 위치를 지정합니다.
		String resource = "config/mybatis-config.xml";
		InputStream inputStream;
		try {
			// 설정 파일을 읽어와서 '공장(SqlSessionFactory)'을 짓습니다.
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// 파일 로딩 중 오류 발생 시 출력
			e.printStackTrace();
		}
	}
	
	/**
	 * 실제로 DB 작업을 수행할 수 있는 세션(SqlSession) 객체를 하나 생성해서 반환합니다.
	 * @return DB 쿼리를 실행할 수 있는 SqlSession 객체
	 */
	public SqlSession getSession() {
		// openSession(true)로 설정하면 쿼리 실행 후 자동으로 commit(DB 반영)됩니다.
		return sqlSessionFactory.openSession(true);
	}
	/**
	 * 싱글톤 인스턴스를 반환합니다.
	 */
	public static DBManager getInstance() {
		if(instance == null)
			instance = new DBManager();
		return instance;
	}
}