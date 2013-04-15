package edu.unlp.db.dto;

import java.util.Set;

public class ItemTypeDto {
	private long oid;
	private String name;
	private String description;
	
	//El workflow que sigue este tipo de items
	private String workflow;

	//Los posibles equipos que pueden resolver este tipo de items
	private Set<String> posibleTeams;

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWorkflow() {
		return workflow;
	}

	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}

	public Set<String> getPosibleTeams() {
		return posibleTeams;
	}

	public void setPosibleTeams(Set<String> posibleTeams) {
		this.posibleTeams = posibleTeams;
	}
	
}
