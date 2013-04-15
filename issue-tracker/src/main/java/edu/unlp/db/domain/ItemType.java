package edu.unlp.db.domain;

import java.util.Set;

public class ItemType {
	private long oid;
	private int version;
	private String name;
	private String description;
	
	//El workflow que sigue este tipo de items
	private Workflow workflow;

	//Los posibles equipos que pueden resolver este tipo de items
	private Set<Team> posibleTeams;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	public Set<Team> getPosibleTeams() {
		return posibleTeams;
	}

	public void setPosibleTeams(Set<Team> posibleTeams) {
		this.posibleTeams = posibleTeams;
	}

	public void setInicialStateToItem(ItemState state, Item item) {
		workflow.setInicialStateToItem(state, item);
	}
	
	public void changeStateToItem(ItemState state, Item item) {
		workflow.changeStateToItem(state, item);
	}

}
