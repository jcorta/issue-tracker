package edu.unlp.db.dto;

import java.util.Collection;

public class ItemDto {

	private long oid;
	private int version;
	private String subject;
	private String description;
	private String created;
	private Collection<ItemCommentDto> comments;
	private String state;
	private String priority;
	private String itemTypeName;
	private String user;
	private Collection<ItemStateHistoryDto> stateHistory;

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public Collection<ItemCommentDto> getComments() {
		return comments;
	}

	public void setComments(Collection<ItemCommentDto> comments) {
		this.comments = comments;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getItemTypeName() {
		return itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Collection<ItemStateHistoryDto> getStateHistory() {
		return stateHistory;
	}

	public void setStateHistory(Collection<ItemStateHistoryDto> stateHistory) {
		this.stateHistory = stateHistory;
	}

}
