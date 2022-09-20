package com.academy.springmvcsimple.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Data;
@Data
public class Pager {
	private int totalRecord; // 총 레코드 수
	private int pageSize=10; // 한 페이지당 보여질 레코드 수
	private int totalPage; // 총 페이지 수
	private int blockSize=10; // 블럭당 보여줄 페이지 수
	private int currentPage=1;
	private int firstPage;
	private int lastPage;
	private int curPos; // 페이지 당 시작 index (List의) 0,10,20
	private int num; // 페이지당 시작번호 26, 16, 6
	
	
	public void init(List list, HttpServletRequest request) {
		totalRecord=list.size();
		totalPage=	(int)Math.ceil((float)(totalRecord)/pageSize);
		if(request.getParameter("currentPage")!=null) {
			// 파라미터가 넘어온다면(페이지 링크번호를 눌렀다면)
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		} 
		
		firstPage=currentPage-(currentPage-1)%blockSize;
		lastPage=firstPage+(blockSize-1);
		curPos=(currentPage-1)*pageSize;
		num=totalRecord-curPos;
	}
}
