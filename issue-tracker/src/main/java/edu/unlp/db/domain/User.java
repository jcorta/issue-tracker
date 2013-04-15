package edu.unlp.db.domain;

import java.util.Collection;
import java.util.HashSet;

public class User {
	private long oid;
	private int version;
	private String username;
	private String password;
	private Collection<Team> teams = new HashSet<Team>();
	
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
	public Collection<Team> getTeams() {
		return teams;
	}
	public void setTeams(Collection<Team> teams) {
		this.teams = teams;
	}
	
}
