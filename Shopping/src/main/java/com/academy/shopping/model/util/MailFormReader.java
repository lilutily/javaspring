package com.academy.shopping.model.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;

// 한줄씩 html 태그를 넣지말고, 파일을 대상으로 스트림으로 읽어와 한줄씩 누적하여 문자열로 반환하는 객체
public class MailFormReader {
	FileReader fr; // 문자기반의 파일을 대상으로 한 입력스트림
	BufferedReader br;
	
	@Setter
	@Getter
	private String path;
	

	public String getStringFromMailForm(String content) {
		StringBuffer sb=new StringBuffer();
		try {
			fr=new FileReader(path);
			br=new BufferedReader(fr);
			while(true) {
				String msg=br.readLine();
				if(msg==null)break;
				sb.append(msg); // 읽어들인 문자열을 버퍼에 추가
			}
			System.out.println("읽어들인 결과 : " + sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fr!=null) {
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
}
