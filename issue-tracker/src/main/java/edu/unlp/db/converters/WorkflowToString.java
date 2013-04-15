package edu.unlp.db.converters;

import org.dozer.DozerConverter;

import edu.unlp.db.domain.Workflow;
import edu.unlp.db.loader.TrackerLoader;

public class WorkflowToString extends DozerConverter<Workflow, String> {

	public WorkflowToString() {
		this(Workflow.class, String.class);
	}
	
	public WorkflowToString(Class<Workflow> prototypeA, Class<String> prototypeB) {
		super(prototypeA, prototypeB);
	}

	@Override
	public String convertTo(Workflow source, String destination) {
		if (source == null) {
			return null;
		}
		return source.getName();
	}

	@Override
	public Workflow convertFrom(String source, Workflow destination) {
		if (source == null) {
			return null;
		}
		Workflow ret = null;
		TrackerLoader trackerLoader = TrackerLoader.getInstance();
		for(Workflow workflow: trackerLoader.getTracker().getWorkflows()){
			if(source.equals(workflow.getName())){
				ret = workflow;
			}
		}
		return ret;
	}

}
