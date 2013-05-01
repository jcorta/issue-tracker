package edu.unlp.db.domain;

import java.util.Collection;
import java.util.HashSet;

public class Team {
	private long oid;
	private int version;
	private String name;
	private Collection<User> users = new HashSet<User>();
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection<User> getUsers() {
		return users;
	}
	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	public void addUser(User user){
		getUsers().add(user);
	}
	public void removeUser(User user) {
		getUsers().remove(user);
	}
}
