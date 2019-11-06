package com.line.road.framework.persistence.entity;

import java.io.Serializable;

public class BaseEntity<E> implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Page<E> page;
	// 是否是新的记录
	protected boolean isNewRecord;
	// 状态
	protected String status;

	public Page<E> getPage() {
		return page;
	}

	public void setPage(Page<E> page) {
		this.page = page;
	}

	public boolean isNewRecord() {
		return isNewRecord;
	}

	public void setNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
