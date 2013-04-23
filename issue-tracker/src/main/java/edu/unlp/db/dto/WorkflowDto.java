package edu.unlp.db.dto;

import java.util.Collection;
import java.util.Map;


public class WorkflowDto {
	private long oid;
	private int version;
	private String name;
	Map<String, Collection<String>> stateTransitions;
	
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
	public Map<String, Collection<String>> getStateTransitions() {
		return stateTransitions;
	}
	public void setStateTransitions(Map<String, Collection<String>> stateTransitions) {
		this.stateTransitions = stateTransitions;
	}
	
}
