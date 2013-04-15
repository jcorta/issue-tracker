package edu.unlp.db.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class Item {
	private long oid;
	private int version;
	private String subject;
	private String description;
	private Date created;
	private Collection<ItemComment> comments = new ArrayList<ItemComment>();
	private ItemState state;
	private ItemPriority priority;
	private ItemType type;
	private User user;
	private Collection<ItemStateHistory> stateHistory = new HashSet<ItemStateHistory>();
	
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
	public Collection<ItemComment> getComments() {
		return comments;
	}
	public void setComments(Collection<ItemComment> comments) {
		this.comments = comments;
	}
	public ItemState getState() {
		return state;
	}
	public void setState(ItemState state) {
		this.state = state;
	}
	public ItemPriority getPriority() {
		return priority;
	}
	public void setPriority(ItemPriority priority) {
		this.priority = priority;
	}
	public ItemType getType() {
		return type;
	}
	public void setType(ItemType type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Collection<ItemStateHistory> getStateHistory() {
		return stateHistory;
	}
	public void setStateHistory(Collection<ItemStateHistory> stateHistory) {
		this.stateHistory = stateHistory;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public void setInicialState(ItemState state2) {
		type.setInicialStateToItem(state2, this);
	}
	public void changeStateTo(ItemState state2, User changedBy) {
		ItemStateHistory itemStateHistory = new ItemStateHistory();
		itemStateHistory.setChangedBy(changedBy);
		itemStateHistory.setChangedDate(new Date());
		itemStateHistory.setFromState(state);
		itemStateHistory.setToState(state2);
		stateHistory.add(itemStateHistory);
		
		type.changeStateToItem(state2, this);
	}
	public void addComment(ItemComment itemComment) {
		comments.add(itemComment);
	}
	
}
