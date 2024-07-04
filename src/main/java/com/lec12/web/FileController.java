package com.lec12.web;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@RequestMapping(value = "/file_upload", method = RequestMethod.POST)
	public String ctlFileUpload(Model model, @RequestParam("ufile") MultipartFile file) {
		
		//파일명, 크기, 확장자
		String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
		long size = file.getSize(); //파일 사이즈
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
		String uniqueName =  UUID.randomUUID().toString().split("-")[0];
		
		//경로
		String uploadFolder = "C:\\IT\\S3917_J11\\workspace_sts3\\uploads";
		String filePath = uploadFolder+"\\" + uniqueName + fileExtension;
		
		FileVO fvo = new FileVO();
		fvo.setFpath(filePath);
//		fvo.setFseq(0); 			//시퀀스 nextval
		fvo.setFsize(size);
		fvo.setOriginName(fileRealName);
		fvo.setSeq(1);				// 실제 사용될 게시글 번호 (현재는 하드코딩 중)
		fvo.setSysName(uniqueName + fileExtension );
		
		System.out.println(fvo.toString());
		
		try {
			file.transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "lec12_file/file_upload_result";
	}
	
//	public static void main(String[] args) {
//		
//		
//		String fileRealName = "abc.111.jpg";//file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
//		//File file = new File(fileRealName);
//		//long size = file.getSize(); //파일 사이즈
//		System.out.println("파일명 : "  + fileRealName);
//		System.out.println(fileRealName.length()+ "," + fileRealName.lastIndexOf("."));
////		System.out.println("용량크기(byte) : " + size);
//		//서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
//		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());//(7,11)
//		String uploadFolder = "C:\\test\\upload";
//		
//		
//		/*
//		  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가 
//		  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다. 
//		  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
//		  고유한 랜던 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
//		 */
//		/*
//		 1205c696-fca1-4138-8c9b-1de2a9fd7f65
//		 생성된 고유문자열 1205c696.jpg로 등록됨
//		 확장자명.jpg
//
//		 */
//		UUID uuid = UUID.randomUUID(); //랜덤한 ID값을 만듬
//		System.out.println(uuid.toString());
//		String[] uuids = uuid.toString().split("-");
//		
//		String uniqueName = uuids[0];
//		System.out.println("생성된 고유문자열" + uniqueName);
//		System.out.println("확장자명" + fileExtension);
//		
//		
//		
//		// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전
//		
////		File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // 적용 후
////		try {
////			file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.) 파일 바이트스트림으로 경로에 저장됨
////		} catch (IllegalStateException e) {
////			e.printStackTrace();
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////		return "fileupload/upload_ok";
//	}
}
