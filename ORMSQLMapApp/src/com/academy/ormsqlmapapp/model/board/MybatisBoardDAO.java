package com.academy.ormsqlmapapp.model.board;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.academy.ormsqlmapapp.hibernate.ConfigManager;
import com.academy.ormsqlmapapp.model.domain.Board;

public class MybatisBoardDAO implements BoardDAO {
	ConfigManager configManager;
	@Override
	public List selectAll() {
		return null;
	}

	@Override
	public Board select(int board_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Board board) {
		// TODO Auto-generated method stub
		
	}

}
