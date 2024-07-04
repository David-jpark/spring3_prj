package com.lec12.web;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;


@Data
public class BoardVO {
	private int seq;
	private String title;
	private String contents;
	private String regid;
	private String regdate;
	
	private List<FileVO> files;
}
