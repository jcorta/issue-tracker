package edu.unlp.db.domain;

import java.util.Collection;

public class Tracker {
	
	private long oid;
	private int version;
	private Collection<Item> items;
	private Collection<ItemType> itemTypes;
	private Collection<Workflow> workflows;
	private Collection<ItemState> itemStates;
	private Collection<ItemPriority> itemPriorities;
	private Collection<User> users;
	private Collection<Team> teams;
	
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
	public Collection<Item> getItems() {
		return items;
	}
	public void setItems(Collection<Item> items) {
		this.items = items;
	}
	public Collection<ItemType> getItemTypes() {
		return itemTypes;
	}
	public void setItemTypes(Collection<ItemType> itemTypes) {
		this.itemTypes = itemTypes;
	}
	public Collection<Workflow> getWorkflows() {
		return workflows;
	}
	public void setWorkflows(Collection<Workflow> workflows) {
		this.workflows = workflows;
	}
	public Collection<User> getUsers() {
		return users;
	}
	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	public Collection<Team> getTeams() {
		return teams;
	}
	public void setTeams(Collection<Team> teams) {
		this.teams = teams;
	}
	public Collection<ItemState> getItemStates() {
		return itemStates;
	}
	public void setItemStates(Collection<ItemState> itemStates) {
		this.itemStates = itemStates;
	}
	public Collection<ItemPriority> getItemPriorities() {
		return itemPriorities;
	}
	public void setItemPriorities(Collection<ItemPriority> itemPriorities) {
		this.itemPriorities = itemPriorities;
	}
	public void addUser(User user){
		getUsers().add(user);
	}
	public void removeUser(User user){
		getUsers().remove(user);
	}
	public void addTeam(Team team){
		getTeams().add(team);
	}
	public void removeTeam(Team team){
		getTeams().remove(team);
	}
	public void addItemState(ItemState state){
		getItemStates().add(state);
	}
	public void removeItemState(ItemState state){
		getItemStates().remove(state);
	}
	public void addWorkflow(Workflow workflow){
		getWorkflows().add(workflow);
	}
	public void removeWorkflow(Workflow workflow){
		getWorkflows().remove(workflow);
	}
	public void addItemType(ItemType itemType){
		getItemTypes().add(itemType);
	}
	public void removeItemType(ItemType itemType){
		getItemTypes().remove(itemType);
	}
	public void addItemPriority(ItemPriority itemPriority){
		getItemPriorities().add(itemPriority);
	}
	public void removeItemPriority(ItemPriority itemPriority){
		getItemPriorities().remove(itemPriority);
	}
	public void addItem(Item item) {
		getItems().add(item);
	}
	public void removeItem(Item item){
		getItems().remove(item);
	}

}
