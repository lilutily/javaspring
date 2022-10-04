package com.academy.testapp.model.board;

import java.util.List;

import com.academy.testapp.model.demain.Board;

public interface BoardDAO { // exception을 만들어야됨 일시키는 사람이 알수없으니까..
	public List selectAll();
	public Board select(int board_id);
	public void insert(Board board);
	public void update(Board board);
	public void delete(Board board);

}
