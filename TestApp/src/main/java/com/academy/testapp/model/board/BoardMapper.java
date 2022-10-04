package com.academy.testapp.model.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.testapp.model.demain.Board;

// DAO가 이 인터페이스를 통해 xml의 쿼리를 접근할 수 있다
// 아래의 객체 메서드명은 아무거나 주지말고 mybatis의 mapper id와 일치해야한다
// ex) <insert id="lily"> , <select ="selectAll"> ~~~ public List selectAll() ~~~~ public void lily

@Mapper // SqlSessionTemplate 을 대신함 int result=sqlSession.Template.insert();
public interface BoardMapper { // BoardMapper에 만들어준걸 생성해야됨
	public List selectAll();
	public Board select(int board_id);
	public int insert(Board board);
	public int update(Board board);
	public int delete(Board board);
}
