package com.academy.bootspa.model.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.bootspa.exception.BoardException;
import com.academy.bootspa.model.domain.Board;

@Repository
public class HibernateBoardDAO implements BoardDAO {
	
	@Autowired
	private HibernateBoardRepository boardRepository;
	
	@Override
	public List selectAll() {
		return boardRepository.findAll();
	}

	@Override
	public Board select(int board_id) {
		return boardRepository.findById(board_id).get();
	}

	@Override
	public void insert(Board board) throws BoardException {
		Board result=boardRepository.save(board); // 알아서 board와 연결된 table insert 시킴
		if(result==null) {
			throw new BoardException("hibernate 등록실패");
		}
	}

	@Override
	public void update(Board board) throws BoardException {
		Board result=boardRepository.save(board);
		if(result==null) {
			throw new BoardException("hibernate 수정실패");
		}
		
	}

	@Override
	public void delete(Board board) throws BoardException {
		try {
			boardRepository.delete(board);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException("Hibernate 삭제실패", e);
		}
	}

}
