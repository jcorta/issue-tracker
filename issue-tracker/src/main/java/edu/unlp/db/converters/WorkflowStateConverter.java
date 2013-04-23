package edu.unlp.db.converters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.dozer.DozerConverter;

import edu.unlp.db.domain.ItemState;
import edu.unlp.db.domain.Tracker;
import edu.unlp.db.domain.WorkflowItem;
import edu.unlp.db.loader.TrackerLoader;

public class WorkflowStateConverter extends
		DozerConverter<Map<ItemState, WorkflowItem>, Map<String, Collection<String>>> {
	private TrackerLoader trackerLoader;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public WorkflowStateConverter() {
		this((Class) Map.class, (Class) Map.class);
	}

	public WorkflowStateConverter(
			Class<Map<ItemState, WorkflowItem>> prototypeA,
			Class<Map<String, Collection<String>>> prototypeB) {
		super(prototypeA, prototypeB);
	}

	// Esta truchada es porque el framework se confunde cual es el origen y cual
	// es el destino
	@SuppressWarnings("unchecked")
	@Override
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		try {
			return convertTo((Map<ItemState, WorkflowItem>) sourceFieldValue,
					(Map<String, Collection<String>>) existingDestinationFieldValue);
		} catch (ClassCastException e) {
			return convertFrom(
					(Map<String, Collection<String>>) sourceFieldValue,
					(Map<ItemState, WorkflowItem>) existingDestinationFieldValue);
		}
	}

	@Override
	public Map<String, Collection<String>> convertTo(Map<ItemState, WorkflowItem> source,
			Map<String, Collection<String>> destination) {
		
		Map<String, Collection<String>> ret = new HashMap<String, Collection<String>>();
		WorkflowItem workflowItem;
		if (source != null) {
			for (ItemState itemStateFrom : source.keySet()) {
				Collection<String> col = new ArrayList<String>();
				workflowItem = source.get(itemStateFrom);
				for(ItemState itemStateTo : workflowItem.getToStates()){
					col.add(itemStateTo.getName());
				}
				ret.put(itemStateFrom.getName(),col);
			}
		}
		return ret;
	}

	@Override
	public Map<ItemState, WorkflowItem> convertFrom(Map<String, Collection<String>> source,
			Map<ItemState, WorkflowItem> destination) {

		Map<ItemState, WorkflowItem> ret = new HashMap<ItemState, WorkflowItem>();
		ItemState itemStateFrom, itemStateTo;
		if (source != null) {
			for (String fromState : source.keySet()) {
				itemStateFrom = getItemState(fromState);
				for(String toState: source.get(fromState)){
					itemStateTo = getItemState(toState);
					addTransition(itemStateFrom, itemStateTo, ret);
				}
			}
		}

		return ret;
	}

	private void addTransition(ItemState itemStateFrom, ItemState itemStateTo,
			Map<ItemState, WorkflowItem> states) {
		WorkflowItem wi = states.get(itemStateFrom);
		if (wi == null) {
			wi = new WorkflowItem();
			wi.setFromState(itemStateFrom);
			states.put(itemStateFrom, wi);
		}
		wi.addToState(itemStateTo);
	}

	private ItemState getItemState(String stateName) {
		ItemState ret = null;
		Collection<ItemState> itemStates = getTracker().getItemStates();
		for (ItemState itemState : itemStates) {
			if (stateName.equals(itemState.getName())) {
				ret = itemState;
			}
		}

		if (ret == null) {
			throw new RuntimeException("No existe estado con ese nombre");
		}

		return ret;
	}

	private Tracker getTracker() {
		return trackerLoader.getTracker();
	}

	public TrackerLoader getTrackerLoader() {
		return trackerLoader;
	}

	public void setTrackerLoader(TrackerLoader trackerLoader) {
		this.trackerLoader = trackerLoader;
	}

}
