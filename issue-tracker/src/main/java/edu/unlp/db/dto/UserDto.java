package edu.unlp.db.dto;

import java.util.Collection;

public class UserDto {
	private long oid;
	private int version;
	private String username;
	private String password;
	private Collection<String> teams;
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<String> getTeams() {
		return teams;
	}
	public void setTeams(Collection<String> teams) {
		this.teams = teams;
	}
}
