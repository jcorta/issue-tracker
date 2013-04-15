package edu.unlp.db.converters;

import org.dozer.DozerConverter;

import edu.unlp.db.domain.User;
import edu.unlp.db.loader.TrackerLoader;

public class UserToStringConverter extends DozerConverter<User, String> {

	public UserToStringConverter() {
		this(User.class, String.class);
	}
	
	public UserToStringConverter(Class<User> prototypeA,
			Class<String> prototypeB) {
		super(prototypeA, prototypeB);
	}

	@Override
	public String convertTo(User source, String destination) {
		if (source == null) {
			return null;
		}
		return source.getUsername();
	}

	@Override
	public User convertFrom(String source, User destination) {
		if (source == null) {
			return null;
		}
		
		User ret = null;
		TrackerLoader trackerLoader = TrackerLoader.getInstance();
		for(User user: trackerLoader.getTracker().getUsers()){
			if(source.equals(user.getUsername())){
				ret = user;
			}
		}
		return ret;
	}

}
