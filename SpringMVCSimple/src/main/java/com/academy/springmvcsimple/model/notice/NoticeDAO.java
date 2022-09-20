package com.academy.springmvcsimple.model.notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.academy.springmvcsimple.domain.Notice;
import com.academy.springmvcsimple.mybatis.MybatisConfigManager;

// MVC 중 Model 영역인 DAO를 정의한다
@Repository
public class NoticeDAO {
	MybatisConfigManager configManager=MybatisConfigManager.getInstance(); // 쿼리문을 수행시켜주니까
	
	public int insert(Notice notice) {
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();
		result=sqlSession.insert("Notice.insert", notice);
		sqlSession.commit();
		configManager.closeSqlSession(sqlSession);
		return result;
	}
	
	// 모두 가져오기
	public List selectAll() {
		List list=null;
		SqlSession sqlSession=configManager.getSqlSession();
		list=	sqlSession.selectList("Notice.selectAll");
		configManager.closeSqlSession(sqlSession);
		return list;
	}
	// 한건 가져오기
	public Notice select(int notice_id) {
		Notice notice=null;
		SqlSession sqlSession=configManager.getSqlSession();
		notice= sqlSession.selectOne("Notice.select", notice_id);
		configManager.closeSqlSession(sqlSession);
		return notice;
	}
	// 한건 삭제하기
	public int delete(int notice_id) {
		int result=0;
		SqlSession sqlSession=configManager.getSqlSession();
		result=sqlSession.delete("Notice.delete",notice_id);
		sqlSession.commit();
		configManager.closeSqlSession(sqlSession);
		return result;
	}
	// 한건 수정하기
	public int update(Notice notice) {
		int result=0;
		SqlSession sqlSession=configManager.getSqlSession();
		result=sqlSession.update("Notice.update", notice);
		sqlSession.commit();
		configManager.closeSqlSession(sqlSession);
		return result;
	}
}
