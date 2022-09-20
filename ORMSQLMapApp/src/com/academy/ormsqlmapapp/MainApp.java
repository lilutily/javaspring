package com.academy.ormsqlmapapp;

import com.academy.ormsqlmapapp.model.board.BoardDAO;
import com.academy.ormsqlmapapp.model.board.HibernateBoardDAO;
import com.academy.ormsqlmapapp.model.domain.Board;

public class MainApp {
	public static void main(String[] args) {
		BoardDAO boardDAO = new HibernateBoardDAO();
//		boardDAO.selectAll();
		
		/*
		 * Board board = new Board(); board.setTitle("hibernate로 넣기");
		 * board.setWriter("망냥냥"); board.setContent("나니"); boardDAO.insert(board);
		 */
		
		/*
		 * Board board = new Board(); board.setBoard_id(12);
		 * board.setTitle("hibernate수정"); board.setWriter("망냥"); board.setContent("냥");
		 * boardDAO.update(board);
		 */
		
		Board board = new Board();
		board.setBoard_id(12);
		boardDAO.delete(board);
	}
}
