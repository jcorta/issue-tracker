package edu.unlp.db.converters;

import org.dozer.DozerConverter;

import edu.unlp.db.domain.Team;
import edu.unlp.db.loader.TrackerLoader;

public class TeamToStringConverter extends DozerConverter<Team, String> {

	public TeamToStringConverter() {
		this(Team.class, String.class);
	}
	
	public TeamToStringConverter(Class<Team> prototypeA,
			Class<String> prototypeB) {
		super(prototypeA, prototypeB);
	}

	@Override
	public String convertTo(Team source, String destination) {
		if (source == null) {
			return null;
		}
		return source.getName();
	}

	@Override
	public Team convertFrom(String source, Team destination) {
		if (source == null) {
			return null;
		}
		
		Team ret = null;
		TrackerLoader trackerLoader = TrackerLoader.getInstance();
		for(Team team: trackerLoader.getTracker().getTeams()){
			if(source.equals(team.getName())){
				ret = team;
			}
		}
		return ret;
	}

}
