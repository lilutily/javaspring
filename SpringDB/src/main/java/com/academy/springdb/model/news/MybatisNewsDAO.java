package com.academy.springdb.model.news;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.springdb.exception.NewsException;
import com.academy.springdb.model.domain.News;
@Repository
public class MybatisNewsDAO implements NewsDAO{
	
	// mybatis -spring의 쿼리수행 객체	// jdbc-> jdbcTemplate
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("News.selectAll");
	}

	@Override
	public News select(int news_id) {
		return sqlSessionTemplate.selectOne("News.select", news_id);
	}

	@Override
	public void insert(News news) throws NewsException {
		int result=sqlSessionTemplate.insert("News.insert",news);
		if(result==0) {
			throw new NewsException("mybatis를 이용한 등록실패");
		}
		
	}

	@Override
	public void update(News news) throws NewsException {
		int result= sqlSessionTemplate.update("News.update",news);
		if(result==0) {
			throw new NewsException("mybatis를 이용한 수정실패");
		}
	}

	@Override
	public void delete(int news_id) throws NewsException {
		int result=sqlSessionTemplate.delete("News.delete", news_id);
		if(result==0) {
			throw new NewsException("mybatis를 이용한 삭제실패");
		}
	}

}
