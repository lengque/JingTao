package model;

import java.util.List;

public class PageBean<T> {
	private List<T> list; // 要返回的某一页的记录列表
	private int totalRecord; // 总记录数
	private int totalPage; // 总页数
	private int currentPage; // 当前页
	private int pageSize = 5; // 每页记录数

	private boolean isFirstPage; // 是否为第一页
	private boolean isLastPage; // 是否为最后一页
	private boolean hasPreviousPage; // 是否有前一页
	private boolean hasNextPage; // 是否有下一页

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setIsFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}
	
	public boolean getIsFirstPage(){
		return this.isFirstPage;
	}

	public void setIsLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}
	
	public boolean getIsLastPage(){
		return this.isLastPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	
	public boolean getHasPreviousPage(){
		return this.hasPreviousPage;
	}
	
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	
	public boolean getHasNextPage(){
		return this.hasNextPage;
	}
	

	/**
	 * 初始化分页信息
	 */
	public void init() {
		if(totalRecord > 0){
			this.isFirstPage = isFirstPage();
			this.isLastPage = isLastPage();
			this.hasPreviousPage = isHasPreviousPage();
			this.hasNextPage = isHasNextPage();
		}else{
			this.isFirstPage = true;
			this.isLastPage = true;
			this.hasPreviousPage = false;
			this.hasNextPage = false;
		}
	}

	/**
	 * 以下判断页的信息,只需getter方法(is方法)即可
	 * 
	 * @return
	 */
	public boolean isFirstPage() {
		return currentPage == 1; // 如是当前页是第1页
	}

	public boolean isLastPage() {
		return currentPage == totalPage; // 如果当前页是最后一页
	}

	public boolean isHasPreviousPage() {
		return currentPage != 1; // 只要当前页不是第1页

	}

	public boolean isHasNextPage() {
		return currentPage != totalPage; // 只要当前页不是最后1页
	}

	/**
	 * 计算总页数,静态方法,供外部直接通过类名调用 
	 * @param pageSize 每页记录数 
	 * 
	 * @param allRow 总记录数
	 * @return 总页数
	 */
	public static int countTotalPage(final int pageSize, final int allRow) {
		int totalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow
				/ pageSize + 1;
		return totalPage;
	}

	/**
	 * 计算当前页开始记录
	 * 
	 * @param pageSize
	 *            每页记录数 * @param currentPage 当前第几页 * @return 当前页开始记录号
	 */
	public static int countOffset(final int pageSize, final int currentPage) {
		final int offset = pageSize * (currentPage - 1);
		return offset;
	}

	/**
	 * 计算当前页,若为0或者请求的URL中没有"?page=",则用1代替 * 
	 * @param page 传入的参数(可能为空,即0,则返回1) 
	 * @return
	 * 当前页
	 */
	public static int countCurrentPage(int page) {
		final int curPage = (page == 0 ? 1 : page);
		return curPage;
	}

}
