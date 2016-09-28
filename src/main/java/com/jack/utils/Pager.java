package com.jack.utils;

import java.io.Serializable;
import java.util.List;

public class Pager<T> implements Serializable{
	public static final Integer MAX_PAGE_SIZE = 500;// 每页最大记录数限制

	// 排序方式（递增、递减）
	public enum Order {
		asc, desc
	}

	private int pageNumber = 1;// 当前页码
	private int pageSize = 21;// 每页记录数
	private int f_page = 1;
	private int l_page = 1;
	private String searchBy;// 查找字段
	private String keyword;// 查找关键字
	private String orderBy;// 排序字段
	private Order order;// 排序方式

	private int totalCount;// 总记录数
	private List<T> result;// 返回结果

	private int firstResult;

	private int maxResults;


	public  void setFirstandLastPn(){
		if (pageNumber < 5) {
			f_page = 1;
		} else {
			f_page = pageNumber - 3;
		}
		if (pageNumber + 3 >= getPageCount()) {
			l_page = getPageCount();
		} else {
			l_page = getPageNumber() + 3;
		}

	}

	public int getF_page() {
		return f_page;
	}

	public int getL_page() {
		return l_page;
	}

	public int getFirstResult() {
		firstResult = getFirst() -1;
		return firstResult;
	}

	public int getMaxResults() {
		maxResults = getPageSize();
		return maxResults;
	}

	public int getFirst() {
		return ((pageNumber - 1) * pageSize) + 1;
	}

	// 获取总页数
	public int getPageCount() {
		int pageCount = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			pageCount++;
		}
		return pageCount;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		} else if (pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getKeyword() {
		return keyword;
	}

	/**csw 添加**/
	public int getStartRow() {
		return this.getFirst() - 1;
	}

	public int getEndRow() {
		return  this.getPageSize();
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}


}

