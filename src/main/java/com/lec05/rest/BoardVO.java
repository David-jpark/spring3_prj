package com.lec05.rest;

import java.util.List;

public class BoardVO {
	private int seq;
	private String title;
	private String contents;
	private String regid;
	private String regdate;
	
	List<ReplyVO> replies;
	
	public BoardVO() {
		
	}
	
public BoardVO(int seq, String title, String contents, String regid, String regdate, List<ReplyVO> replies) {
		super();
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.regid = regid;
		this.regdate = regdate;
		this.replies = replies;
	}

	public BoardVO(int seq, String title, String regid, String regdate, String contents) {
//		super();
//		this(seq, title ..);
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.regid = regid;
		this.regdate = regdate;
	}
	
	public List<ReplyVO> getReplies() {
		return replies;
	}

	public void setReplies(List<ReplyVO> replies) {
		this.replies = replies;
	}

	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", contents=" + contents + ", regid=" + regid + ", regdate="
				+ regdate + ", replies=" + replies + "]";
	}
	
	
	
}
