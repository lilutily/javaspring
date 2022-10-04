package com.academy.shopping.model.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.academy.shopping.exception.EmailException;
// 우리회사 운영서버가 아닌 구글의 서버를 빌려서 메일을 보내자!
public class MailSender {
	String host="smtp.gmail.com"; // 구글이 운영하는 메일보내주는 서버 주소
	String user="asdf@gmail.com"; // 본인의 구글계정
	String password="tsqrgkgzhguqgpdg1234"; // 구글 앱 비밀번호 or 로그인 비밀번호
	Properties props=new Properties(); // map의 자식객체 (MVC 패턴 만들때 설정파일 해석시 사용)
	
	public void send(String content) throws EmailException { // 서비스에게 전달할려고
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session=Session.getDefaultInstance(props, new Authenticator() { // javax.mail.Session
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		}); 
		
		// 메일 보낼 내용 구성하기
		Message message=new MimeMessage(session);
		
		// 보내는 사람 정보
		try {
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("qkqhgifrn@naver.com"));
			message.setSubject("고객님의 주문이 완료되었습니다");
			message.setContent(content,"text/html;charset=utf-8");
			
			Transport.send(message); // 전송되는 시점
			
			System.out.println("메일 발송 성공");
		} catch (AddressException e) {
			e.printStackTrace();
			throw new EmailException("메일 전송 실패", e);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new EmailException("메일 전송 실패", e);
		}
	}
}
