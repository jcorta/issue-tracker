package edu.unlp.db.dto;

import java.util.Collection;

public class TeamDto {
	private long oid;
	private int version;
	private String name;
	private Collection<String> users;
	
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
	public Collection<String> getUsers() {
		return users;
	}
	public void setUsers(Collection<String> users) {
		this.users = users;
	}

}
