package com.academy.springdb.model.domain;

import java.util.List;

import lombok.Data;

@Data
public class News {
	private int news_id;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	
	// collection으로 자식을 담으려면 복수개의 comments를 담을 무언가가 필요하다
	List<Comments> commentsList;
}
