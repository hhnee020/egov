package egovframework.example.sample.service;
/*
 *  객체지향 ( 캡슐화 ,추상, 상속 , 다형성 )
 * 
 * */
public class DeptVO {
/// 전달된값을 가지고 있는 것 ;
	
	// 접근 지정자
	private String deptno; // 인스턴트  ( 객체 ) 변수;
	private String dname;
	private String  loc;
	
	
	public String getDeptno() {
		return deptno;
	}
	
	public void setDeptno(String deptno) {
		this.deptno = deptno;
	// this 현재 실행 되고 있는 객체 (DeptVO);
		
	}
	///////////////////////////////
	
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	
	/////////////////////////////
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}

	
}
