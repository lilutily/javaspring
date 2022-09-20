package com.academy.ormsqlmapapp.model.board;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.academy.ormsqlmapapp.hibernate.ConfigManager;
import com.academy.ormsqlmapapp.model.domain.Board;

public class HibernateBoardDAO implements BoardDAO {
	ConfigManager configManager= ConfigManager.getInstance();
	@Override
	public List selectAll() {
		// hibernate에서는 쿼리수행 역할을 수행하는 객체를 Session 이라 한다
		Session session=configManager.getSession();
		Transaction transaction=session.beginTransaction();
		
		// 업무
		List list=session.createCriteria(Board.class).list();
		System.out.println("게시물 결과는 "+list);
		transaction.commit();
		return list;
	}

	@Override
	public Board select(int board_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Board board) {
		Session session= configManager.getSession();
		Transaction transaction= session.beginTransaction();
		int result=(int)session.save(board);
		System.out.println("방금 입력된 레코드의 pk는 "+result);
		transaction.commit();
	}

	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub
		Session session=configManager.getSession();
		
		Transaction transaction= session.beginTransaction();
		session.update(board);
		transaction.commit();
	}

	@Override
	public void delete(Board board) {
		Session session=configManager.getSession();
		
		Transaction transaction = session.beginTransaction();
		session.delete(board);
		transaction.commit();
	}

}
