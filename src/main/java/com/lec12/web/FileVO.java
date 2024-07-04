package com.lec12.web;

import lombok.Data;

@Data
public class FileVO {
	
	private int fseq;			//board_file_seq.nextval
	private String originName; // 사용자가 올린 파일 원본명		 ex) aa.jpg
	private String sysName; // 시스템에 올릴때 rename되는 파일명 ex) 2024789_aa.jpg 
	private long fsize =0;		// 바이트 234342
	private String fpath;	// 파일저장경로 : </webapp>/uploads/2024789_aa.jpg
	private int seq;		// 게시물번호
	private String regid = "tester";
	private String regdate;
}
