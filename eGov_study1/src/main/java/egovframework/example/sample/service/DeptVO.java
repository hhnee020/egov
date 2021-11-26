package egovframework.example.sample.service;

public class DeptVO {

	/*
	 * 객체지향 (은늑화(캡슐화), 추상, 상속, 다형성 ) 
	 */
	// 접근지정자
	private String deptno;  // 인스턴스(객체)변수
	private String dname;
	private String loc;
	//private String[] hobby;
	
	public String getDeptno() {
		return deptno;
	}
	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}

}
