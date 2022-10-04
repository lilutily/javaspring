package com.academy.shopping.model.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.academy.shopping.exception.ProductException;
import com.academy.shopping.exception.UploadException;
import com.academy.shopping.model.category.SubCategoryDAO;
import com.academy.shopping.model.domain.Product;
import com.academy.shopping.model.domain.SubCategory;
import com.academy.shopping.model.util.ExcelParser;
import com.academy.shopping.model.util.FileManager;

@Service
public class ProductServiceImpl implements ProductService {

	// 하위 카테고리 목록을 가져올 DAO
	@Autowired
	private SubCategoryDAO subCategoryDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private FileManager fileManager;

	@Autowired
	private ExcelParser excelParser;

	@Override
	public List selectAll() {

		return productDAO.selectAll();
	}

	@Override
	public List selectByTopId(int topcategory_id) {
		List<SubCategory> subCategoryList = subCategoryDAO.selectByTopCategoryId(topcategory_id); // 서브 카테고리 들의 목록을 반환

		List productList = new ArrayList(); // 서브카테고리 마다 소속된 상품들을 누적시킬 리스트

		for (int i = 0; i < subCategoryList.size(); i++) {
			SubCategory subCategory = subCategoryList.get(i);
			List<Product> list = productDAO.selectBySubyId(subCategory.getSubcategory_id());
			for (Product product : list) { // 풀어서
				productList.add(product); // 넣기
			}
		}
		return productList;
	}

	@Override
	public List selectBySubyId(int product_id) {
		return productDAO.selectBySubyId(product_id);
	}

	@Override
	public Product select(int product_id) {
		return productDAO.select(product_id);
	}

	@Override
	// 등록완성 = DB에 넣기 + 파일저장
	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(Product product, String savePath) throws UploadException, ProductException {
		String filename = fileManager.save(product, savePath); // 파일저장
		product.setProduct_img(filename); // 새롭게 생성된 파일명 저장
		productDAO.insert(product); // db에 저장
	}

	@Transactional(propagation = Propagation.REQUIRED) // 트랜잭션 대상임 (하나라도 실패하면 안됨)
	public void registByExcel(File file, String ori, String dest) { // 파일, 원본, 저장 위치
		// 액셀을 간접적으로 해석하여 insert DAO에게 시킬것임..
		// 2003년 이후 최신 버전에서의 전담객체 XSSF~
		List<Product> productList = excelParser.getParseResult(file);
		
		for (Product product : productList) {
			// 이미지를 서버에 저장하기 (스프링과 상관없이 개발자의 javaSE 능력으로 해결해야된다
//			System.out.println("생성할 파일명 " + path+"/"+product.getProduct_img());
			
			FileInputStream fis= null;
			FileOutputStream fos=null;
			try {
				// 파일을 대상으로 한 출력 스트림
				fis=new FileInputStream(ori+"/"+product.getProduct_img());
				
				long time=System.currentTimeMillis();
				String ext= fileManager.getExt(product.getProduct_img()); // 원본의 경로
				String filename=time+"."+ext; // 최종적으로 결정된 파일명
				
				// 기존 DTO에 생성된 파일명을 대체
				product.setProduct_img(filename);
				fos=new FileOutputStream(dest+"/"+filename); // 개발자가 파일명을 생성해야함 empty 파일생성
				
				int data=-1;
				while(true) {
					data=fis.read(); // 1byte 읽어오기
					if(data==-1) break; // 멈추는 조건
					fos.write(data); // 1byte 출력
				}
				System.out.println("파일복사 완료");
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(fos!=null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(fis!=null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			  productDAO.insert(product); // 레코드 한건 넣기
			  try { 
				  Thread.sleep(500); 
			 } catch (InterruptedException e) {
				 e.printStackTrace(); 
			 }
			 
			
		}
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Product product, String dest) {
		// 파일 삭제
		fileManager.removeFile(dest);
		
		productDAO.delete(product); // db에서 삭제
	}

}
