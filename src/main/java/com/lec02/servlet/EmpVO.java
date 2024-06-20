package com.lec02.servlet;


//setter(): 넣기 --> set+프로퍼티명()
//getter(): 꺼내기--> get+프로퍼티명()


public class EmpVO {
	//프로퍼티(property) : 클래스 값 변수
	private int empno;
	private String ename;
	private int sal;
	
	public EmpVO() {
		
	}
	
	//마우스>source>Generate Constructor Using field..
	public EmpVO(int empno, String ename, int sal) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.sal = sal;
	}
	//마우스>source>Generate getter and setter..
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	
	
	//Object-> toString()을 오버라이딩
	@Override
	public String toString() {
		return "EmpVO [empno=" + empno + ", ename=" + ename + ", sal=" + sal + "]";
	}
	
	
//	public int getEmpno() {
//		return this.empno;
//	}
//	
//	public void setEmpno(int prmEmpno) {
//		this.empno = prmEmpno;
//	}
//	
//	public String getEname() {
//		return this.ename;
//	}
//	
//	public void setEname(String prmEname) {
//		this.ename = prmEname;
//	}
	
	
}
