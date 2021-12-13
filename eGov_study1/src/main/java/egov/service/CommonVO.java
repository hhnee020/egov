package egov.service;

public class CommonVO {

	// 한 화면의 데이터 출력 단위(개수)
	int page_unit = 10;
	
	// 한 화면의 페이징 숫자 출력 단위(개수)
	int page_size = 10;
	
	// 현 페이지 번호
	int page_no = 1;
	
	// 출력 SQL의 시작 번호
	int s_no;
	
	// 출력 SQL의 마지막 번호
	int e_no;
	
	// 총 데이터 개수
	int total;
	
	// 총 페이지 개수
	int total_page;
	
	// 출력 페이지의 시작 행 번호
	int row_no;

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

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public int getE_no() {
		return e_no;
	}

	public void setE_no(int e_no) {
		this.e_no = e_no;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotal_page() {
		return total_page;
	}

	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}

	public int getRow_no() {
		return row_no;
	}

	public void setRow_no(int row_no) {
		this.row_no = row_no;
	}

}







