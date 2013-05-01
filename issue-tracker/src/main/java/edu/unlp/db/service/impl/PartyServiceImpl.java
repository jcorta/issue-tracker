package edu.unlp.db.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.util.Assert;

import edu.unlp.db.domain.Team;
import edu.unlp.db.domain.User;
import edu.unlp.db.dto.TeamDto;
import edu.unlp.db.dto.UserDto;
import edu.unlp.db.service.PartyService;
import edu.unlp.db.service.PartyServiceInternal;

public class PartyServiceImpl extends EntityService implements PartyService, PartyServiceInternal {

	public UserDto createUser(String username, String password) {
		Assert.notNull(username, "Username no puede ser null");
		Assert.notNull(password, "Password no puede ser null");

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);

		getTracker().addUser(user);
		trackerLoader.refreshOids();
		
		return mapper.map(user, UserDto.class);
	}

	public TeamDto createTeam(String teamName) {
		Assert.notNull(teamName, "teamName no puede ser null");

		Team team = new Team();
		team.setName(teamName);

		getTracker().addTeam(team);
		trackerLoader.refreshOids();
		
		return mapper.map(team, TeamDto.class);
	}

	public void removeUser(String username) {
		Assert.notNull(username, "Username no puede ser null");

		Iterator<User> it = getTracker().getUsers().iterator();
		boolean ok = false;
		User user;
		while (it.hasNext() && ok == false) {
			user = it.next();
			if (username.equals(user.getUsername())) {
				it.remove();
				ok = true;
			}
		}
		if (!ok) {
			throw new RuntimeException("Usuario no existe");
		}

	}

	public void removeTeam(String teamName) {
		Assert.notNull(teamName, "teamName no puede ser null");

		Iterator<Team> it = getTracker().getTeams().iterator();
		boolean ok = false;
		Team team;
		while (it.hasNext() && ok == false) {
			team = it.next();
			if (teamName.equals(team.getName())) {
				it.remove();
				ok = true;
			}
		}
		if (!ok) {
			throw new RuntimeException("Grupo no existe");
		}

	}

	public void addUserToTeam(String username, String teamName) {
		Assert.notNull(username, "username no puede ser null");
		Assert.notNull(teamName, "groupName no puede ser null");

		User user = getUserByUsername(username);
		Team team = getTeamByName(teamName);
		
		team.addUser(user);
	}

	public void removeUserFromTeam(String username, String teamName) {
		Assert.notNull(username, "username no puede ser null");
		Assert.notNull(teamName, "groupName no puede ser null");

		User user = getUserByUsername(username);
		Team team = getTeamByName(teamName);
		
		team.removeUser(user);
	}

	public Collection<UserDto> getUsers() {
		Collection<User> users = getTracker().getUsers();
		Collection<UserDto> ret = new ArrayList<UserDto>();
		for(User user: users){
			ret.add(mapper.map(user, UserDto.class));
		}
		return ret;
	}

	public Collection<TeamDto> getTeams() {
		Collection<Team> teams = getTracker().getTeams();
		Collection<TeamDto> ret = new ArrayList<TeamDto>();
		for(Team team: teams){
			ret.add(mapper.map(team, TeamDto.class));
		}
		return ret;
	}

	private Team getTeamByName(String name) {
		Assert.notNull(name, "name no puede ser null");

		Iterator<Team> it = getTracker().getTeams().iterator();
		boolean salir = false;
		Team team, retTeam  = null;
		while (it.hasNext() && salir == false) {
			team = it.next();
			if (name.equals(team.getName())) {
				retTeam = team;
				salir = true;
			}
		}
		if (retTeam == null) {
			throw new RuntimeException("Grupo no existe");
		}
		return retTeam;
	}

	public User getUserByUsername(String username) {
		Iterator<User> it = getTracker().getUsers().iterator();
		User user, retUser = null;
		boolean exit = false;
		while (it.hasNext() && exit == false) {
			user = it.next();
			if (username.equals(user.getUsername())) {
				retUser = user;
				exit = true;
			}
		}
		if (retUser == null) {
			throw new RuntimeException("Usuario no existe");
		}
		return retUser;
	}

}
