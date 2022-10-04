package com.academy.shopping.model.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

// 회원정보 중, 비밀번호를 평문으로 넣지말자
// 암호화 시켜 넣을 건데 16진수 값의 해쉬값으로 변환하자.. 

@Component
public class HashManager {
	public static String getConvertedPassword(String password) {
		//위의 문자열을 사람이 알아볼 수 없는 해쉬값으로 변환해보자
		StringBuffer sb=null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash=digest.digest(password.getBytes("UTF-8")); // 쪼개기
			
			// 쪼개진 데이터 크기만큼 반복문 돌리면서 하나씩 개별적으로 16진수 해쉬 값으로 변환한 후,
			// 하나의 문자열에 모아놓자!
			sb= new StringBuffer(); // String str = null << 이렇게 만들면 개닦임;;
			for(int i=0; i<hash.length; i++) {
				String hex=Integer.toHexString(0xff&hash[i]); // 16진수값으로 변환
				if(hex.length()==1) {
					sb.append("0"); // b-> 0b
				}
				sb.append(hex); // 그냥 누적
			}
			System.out.println(sb.toString());
			System.out.println(sb.toString().length());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	
}
