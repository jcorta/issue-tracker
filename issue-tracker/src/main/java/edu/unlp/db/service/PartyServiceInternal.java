package edu.unlp.db.service;

import edu.unlp.db.domain.User;

public interface PartyServiceInternal extends PartyService {
	
	public User getUserByUsername(String username);
}
