package edu.unlp.db.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import edu.unlp.db.service.PartyService;

@ContextConfiguration(locations={"/context.xml"})
@TransactionConfiguration(defaultRollback = true)
public class PartyServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	PartyService partyService;
	
	@Test
	public void createUser() {
		getPartyService().createUser("juan", "juan");
	}
	
	@Test
	public void removeUser() {
		getPartyService().createUser("juan", "juan");
		getPartyService().removeUser("juan");
	}
	
	@Test
	public void createTeam() {
		getPartyService().createTeam("team1");
	}
	
	@Test
	public void removeTeam() {
		getPartyService().createTeam("team1");
		getPartyService().removeTeam("team1");
	}
	
	@Test
	public void addUserToTeam() {
		getPartyService().createTeam("team1");
		getPartyService().createUser("juan", "juan");
		getPartyService().addUserToTeam("juan", "team1");
	}
	
	@Test
	public void getUsers() {
		getPartyService().getUsers();
	}	
	
	@Test
	public void getTeams() {
		getPartyService().getTeams();
	}
	
	
	//Con el autowire deja de ser lazy y en ese caso cuando levanta el tracker no esta dentro de la transaccion
	private PartyService getPartyService(){
		return partyService;
	}

}
