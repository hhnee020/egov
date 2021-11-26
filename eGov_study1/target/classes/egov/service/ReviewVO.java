package egov.service;

public class ReviewVO {

	int unq;
	String title;
	String pass;
	String name;
	String content;
	int hits;
	String rdate;
	String udate;
	String email;
	String sns;
	
	int page_unit = 10;
	int page_size = 10;
	int page_no = 1;
	int row_no = 10;
	int start_no = 1;
	int end_no = 10;
	int total = 1;
	int total_page = 1;

	public int getPage_unit() {
		return page_unit;
	}
	public void setPage_unit(int page_unit) {
		this.page_unit = page_unit;
	}
	public int getPage_size() {
		return page_size;
	}
	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}
	public int getPage_no() {
		return page_no;
	}
	public void setPage_no(int page_no) {
		this.page_no = page_no;
	}
	public int getRow_no() {
		
		row_no = total - (page_no-1)*page_unit;
		return row_no;
	}
	public void setRow_no(int row_no) {
		this.row_no = row_no;
	}
	
	public int getStart_no() {
		
		start_no = (page_no-1)*page_unit + 1;
		return start_no;
	}
	
	public void setStart_no(int start_no) {
		this.start_no = start_no;
	}
	
	public int getEnd_no() {
		
		end_no = start_no+(page_unit-1);
		return end_no;
	}
	public void setEnd_no(int end_no) {
		this.end_no = end_no;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotal_page() {
		total_page = (int) Math.ceil( (double)total/page_unit );
		return total_page;
	}
	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSns() {
		return sns;
	}
	public void setSns(String sns) {
		this.sns = sns;
	}

}
