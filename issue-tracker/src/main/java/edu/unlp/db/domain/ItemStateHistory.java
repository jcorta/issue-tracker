package edu.unlp.db.domain;

import java.util.Date;

public class ItemStateHistory {
	private Long oid;
	private int version;
	private ItemState fromState;
	private ItemState toState;
	private Date changedDate;
	private User changedBy;
	
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public ItemState getFromState() {
		return fromState;
	}
	public void setFromState(ItemState fromState) {
		this.fromState = fromState;
	}
	public ItemState getToState() {
		return toState;
	}
	public void setToState(ItemState toState) {
		this.toState = toState;
	}
	public Date getChangedDate() {
		return changedDate;
	}
	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}
	public User getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(User changedBy) {
		this.changedBy = changedBy;
	}

}
