package com.academy.bootspa.model.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.bootspa.model.domain.Board;

//SqlSessionTemplate을 사용하지않고 개발가능

@Mapper
public interface MybatisBoardMapper {
	public List selectAll();
	public Board select(int board_id);
	public int insert(Board board);
	public int update(Board board);
	public int delete(Board board);

}
