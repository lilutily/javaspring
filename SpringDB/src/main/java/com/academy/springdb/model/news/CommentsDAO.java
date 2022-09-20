package com.academy.springdb.model.news;

import java.util.List;

import com.academy.springdb.model.domain.Comments;

public interface CommentsDAO {
	public List selectAll(); // 모두 가져오기
	public List selectByNewsId(int news_id); // 해당 뉴스기사에 소속된 댓글 목록
	public Comments select(int comments_id); // 코멘트 한건 가져오기
	public void insert(Comments comments);
	public void update(Comments commnets);
	public void deleteByNewsId(int news_id);
	public void delete(int comments_id);
}
