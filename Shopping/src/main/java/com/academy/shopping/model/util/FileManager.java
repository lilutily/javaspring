package com.academy.shopping.model.util;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.academy.shopping.exception.FileException;
import com.academy.shopping.exception.UploadException;
import com.academy.shopping.model.domain.Product;

@Component
public class FileManager {
	
	
	public static String getExt(String path) {
		// d:aaaaafd.jpg
		int index=path.lastIndexOf("."); // 가장 마지막 .의 인덱스 구하기
		String ext=path.substring(index+1, path.length());
		return ext;
	}
	// 파일과 관련된 유용한 기능을 구현한 객채
	public String save(Product product, String savePath) throws UploadException {
		MultipartFile multi =  product.getPhoto();
		// 파일저장
		String ext=getExt(multi.getOriginalFilename());
		long time=System.currentTimeMillis();
		try {
			multi.transferTo(new File(savePath+"/"+time+"."+ext));
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new UploadException("업로드 실패",e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UploadException("업로드 실패",e);
		}
		// 시, 분, 초 밀리세컨드
		
		
		System.out.println(multi.getOriginalFilename());
		
		return time+"."+ext;
	}
	
	public File saveExcel(String path, MultipartFile excel) {
		
		// 1) 서버에 올라온 엑셀을 읽어보자(업로드 완료)
		File file=null;
		try {
			excel.transferTo(file=new File(path+"/"+excel.getOriginalFilename()));
			System.out.println(file.getAbsolutePath());
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
		
	}
	
	// 파일삭제
	
	public void removeFile(String path) throws FileException{
		File file= new File(path); // 지정한 경로의 파일에 대한 객체를 생성
		boolean result=file.delete();
		if(result==false) {
			throw new FileException("파일삭제실패");
		}
	}
	/*
	 * public static void main(String[] args) { String
	 * String ext=getExt("d://adb....da.d..png"); long time=System.currentTimeMillis();
	 * System.out.println(time+"."+ext); //
	 * System.out.println(getExt("d://adb....da.d..png")); }
	 */
}
