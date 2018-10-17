package com.jqmkj.WaterResponseSystem.pojo;

public class QueryInfo {
	private Integer page;
	private Integer rows;
	private Integer start;

	public QueryInfo() {
	}

	public QueryInfo(Integer page, Integer rows) {
		this.page = page;
		this.rows = rows;
		this.start = (this.page - 1) * this.rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "QueryInfo [page=" + page + ", rows=" + rows + ", start="
				+ start + "]";
	}

}
