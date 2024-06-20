package com.lec05.rest;

public class ReplyVO {
	
	private int rseq;
	private String reply;
	private String regid;
	private String regdate;
	private int seq; 		//FK - 
	
	public ReplyVO() {
		
	}
	public ReplyVO(int rseq, String reply, String regid, String regdate, int seq) {
		super();
		this.rseq = rseq;
		this.reply = reply;
		this.regid = regid;
		this.regdate = regdate;
		this.seq = seq;
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getRseq() {
		return rseq;
	}
	public void setRseq(int rseq) {
		this.rseq = rseq;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
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
		return "ReplyVO [rseq=" + rseq + ", reply=" + reply + ", regid=" + regid + ", regdate=" + regdate + ", seq="
				+ seq + "]";
	}
	
	
	
	
	
}
