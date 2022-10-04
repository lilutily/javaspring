package com.academy.bootspa.model.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.academy.bootspa.exception.BoardException;
import com.academy.bootspa.model.domain.Board;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	@Qualifier("hibernateBoardDAO") // mybatis나 hibernate로 변경
	private BoardDAO boardDAO;
	@Override
	public List selectAll() {
		return boardDAO.selectAll();
	}

	@Override
	public Board select(int board_id) {
		return boardDAO.select(board_id);
	}

	@Override
	public void insert(Board board) throws BoardException {
		boardDAO.insert(board);
		
	}

	@Override
	public void update(Board board) throws BoardException {
		boardDAO.update(board);
		
	}

	@Override
	public void delete(Board board) throws BoardException {
		boardDAO.delete(board);
		
	}

}
