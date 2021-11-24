package egov.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NBoardVO {
	public int getUnq() {
		return unq;
	}
	
	
	public void setUnq(int unq) {
		this.unq = unq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public String getUdate() {
		return udate;
	}
	public void setUdate(String udate) {
		this.udate = udate;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	public int geteNo() {
		return eNo;
	}
	public void seteNo(int eNo) {
		this.eNo = eNo;
	}
	public String getS_field() {
		return s_field;
	}
	public void setS_field(String s_field) {
		this.s_field = s_field;
	}
	public String getS_text() {
		return s_text;
	}
	public void setS_text(String s_text) {
		this.s_text = s_text;
	}
	

	public String getUnq1() {
		return unq1;
	}
	public void setUnq1(String unq1) {
		this.unq1 = unq1;
	}
	public String getUnq2() {
		return unq2;
	}
	public void setUnq2(String unq2) {
		this.unq2 = unq2;
	}
	public String getUnq3() {
		return unq3;
	}
	public void setUnq3(String unq3) {
		this.unq3 = unq3;
	}


	public String getBeforeid() {
		return beforeid;
	}
	public void setBeforeid(String beforeid) {
		this.beforeid = beforeid;
	}
	public String getNextid() {
		return nextid;
	}
	public void setNextid(String nextid) {
		this.nextid = nextid;
	}


	private int unq;
	private String title;
	private String pass;
	private String name;
	private String content;
	private int hits;
	private String rdate;
	private String udate;
	private String classe;
	private int pageNo =1;
	private int unit=10;
	private int totalpage =1;
	private int sNo=0;
	private int eNo=9;
	private String s_field ="";
	private String s_text="";
	
	private String unq1;
	private String unq2;
	private String unq3;
	private String beforeid;
	private String nextid;
	
}
