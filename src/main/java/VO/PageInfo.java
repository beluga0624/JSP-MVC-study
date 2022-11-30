package VO;

public class PageInfo {
	private int page; // 현재 페이지 번호
	private int maxPage; // 총 게시물에 대해 필요한 총 페이지 수
	private int startPage; // 현재 창에서 첫 페이지 번호(1번 페이지) ex 1 2 3 4 5 >>
	private int endPage; // 현재 창에서 마지막 페이지 번호(5번 페이지) >> 클릭시 startPage, endPage가 바뀜
	private int listCount; // 총 게시물 건수
	private String type; 
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
