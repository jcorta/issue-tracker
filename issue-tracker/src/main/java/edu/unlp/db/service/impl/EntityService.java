package edu.unlp.db.service.impl;

import org.dozer.DozerBeanMapper;

import edu.unlp.db.domain.Tracker;
import edu.unlp.db.loader.TrackerLoader;

public class EntityService {
	protected TrackerLoader trackerLoader;
	protected DozerBeanMapper mapper;
	
	protected Tracker getTracker() {
		return trackerLoader.getTracker();
	}

	public DozerBeanMapper getMapper() {
		return mapper;
	}

	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	public TrackerLoader getTrackerLoader() {
		return trackerLoader;
	}

	public void setTrackerLoader(TrackerLoader trackerLoader) {
		this.trackerLoader = trackerLoader;
	}
}
