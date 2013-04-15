package edu.unlp.db.service;

import java.util.Collection;

import edu.unlp.db.dto.TeamDto;
import edu.unlp.db.dto.UserDto;

public interface PartyService{

	public UserDto createUser(String username, String password);
	public void removeUser(String username);
	public Collection<UserDto> getUsers();
	
	public TeamDto createTeam(String teamName);
	public void removeTeam(String teamName);
	public Collection<TeamDto> getTeams();
	
	public void addUserToTeam(String username, String groupName);
}
