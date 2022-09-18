package com.aca.web0812.map;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.web0812.domain.HotSpot;
import com.aca.web0812.model.HotSpotDAO;

// 클라이언트의 등록 요청을 처리하는 서블릿
public class RegistServlet extends HttpServlet{
	// 클라이언트가 비동기로 요청을 하면, 화면전체를 바꾸려는 것이 아니라 
	// 데이터만을 교환하기 위함이므로, 기존 동기방식처럼 html 페이지 전체를 보내지 말고
	// 데이터만을 응답정보로 보내는게 올바르다
	HotSpotDAO hotSpotDAO;
	
	public void init() throws ServletException {
		hotSpotDAO = new HotSpotDAO();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 받기
		request.setCharacterEncoding("utf-8");
		
		String lati = request.getParameter("lati");
		String longi = request.getParameter("longi");
		String icon = request.getParameter("icon");
		String content = request.getParameter("content");
		
		System.out.println("lati : "+lati);
		System.out.println("longi : "+longi);
		System.out.println("icon : "+icon);
		System.out.println("content : "+content);
		
		// 4개의 파라미터를 하나로 모아서 전달할 DTO 생성
		HotSpot hotSpot = new HotSpot();
		hotSpot.setLati(Float.parseFloat(lati));
		hotSpot.setLongi(Float.parseFloat(longi));
		hotSpot.setIcon(icon);
		hotSpot.setContent(content);
		
		int result = hotSpotDAO.insert(hotSpot);
		// 응답을 어떻게? html로 보내지 말고 insert한 결과가 성공인지 실패인지 메시지를 띄우자
		// 즉 복합된 결과를 보내고자 할때는 xml이나 json이 많이 쓰인다.
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out =response.getWriter();
		
		// 성공여부 코드 + 메시지
		//out.print() 안에 인수로 넣은 스트링은 json 오브젝트는 아니고 , 그냥 json 표기법을 지킨 String이다.
		// 따라서 이 문자열을 전송받은 클라이언트는 반드시 파싱하고 사용해야한다.
		String resData=null;
		if(result ==0) {
		    resData = "{";
			resData+=" \"code\":0, ";
			resData+=" \"msg\":\"데이터등록실패\"";
			resData+="}";
			out.print(resData);
		}
		else {
			resData = "{";
			resData+=" \"code\":1, ";
			resData+=" \"msg\":\"데이터등록성공\"";
			resData+="}";
			out.print(resData);
		}
	}
}
