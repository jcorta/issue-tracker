package edu.unlp.db.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.unlp.db.dto.TeamDto;
import edu.unlp.db.dto.UserDto;
import edu.unlp.db.service.PartyService;

@Controller
@RequestMapping("/party")
public class PartyController {
	
	@Autowired
	PartyService partyService;
	
	@RequestMapping(value="/user/{username}/{password}", method = RequestMethod.POST)
	public @ResponseBody UserDto createUser(@PathVariable String username, @PathVariable String password) {
		return partyService.createUser(username, password);
	}
	
	@RequestMapping(value="/user/{username}", method = RequestMethod.DELETE)
	public @ResponseBody void removeUser(@PathVariable String username) {
		partyService.removeUser(username);
	}
	
	@RequestMapping(value="/users", method = RequestMethod.GET)
	public @ResponseBody Collection<UserDto> getUsers() {
		return partyService.getUsers();
	}

	@RequestMapping(value="/team/{teamName}", method = RequestMethod.POST)
	public @ResponseBody TeamDto createTeam(@PathVariable String teamName) {
		return partyService.createTeam(teamName);
	}
	
	@RequestMapping(value="/team/{teamName}", method = RequestMethod.DELETE)
	public @ResponseBody void removeTeam(@PathVariable String teamName) {
		partyService.removeTeam(teamName);
	}
	
	@RequestMapping(value="/teams", method = RequestMethod.GET)
	public @ResponseBody Collection<TeamDto> getTeams() {
		return partyService.getTeams();
	}
	
	@RequestMapping(value="/addUserToTeam/{userName}/{teamName}", method = RequestMethod.POST)
	public @ResponseBody void addUserToTeam(@PathVariable String userName, @PathVariable String teamName) {
		partyService.addUserToTeam(userName, teamName);
	}
	
	@RequestMapping(value="/removeUserFromTeam/{userName}/{teamName}", method = RequestMethod.DELETE)
	public @ResponseBody void removeUserFromTeam(@PathVariable String userName, @PathVariable String teamName) {
		partyService.removeUserFromTeam(userName, teamName);
	}
}
