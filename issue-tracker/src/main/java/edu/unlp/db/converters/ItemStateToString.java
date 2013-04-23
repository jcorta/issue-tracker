package edu.unlp.db.converters;

import org.dozer.DozerConverter;

import edu.unlp.db.domain.ItemState;
import edu.unlp.db.loader.TrackerLoader;

public class ItemStateToString extends DozerConverter<ItemState, String> {

	public ItemStateToString() {
		this(ItemState.class, String.class);
	}
	
	public ItemStateToString(Class<ItemState> prototypeA, Class<String> prototypeB) {
		super(prototypeA, prototypeB);
	}

	@Override
	public String convertTo(ItemState source, String destination) {
		if (source == null) {
			return null;
		}
		return source.getName();
	}

	@Override
	public ItemState convertFrom(String source, ItemState destination) {
		if (source == null) {
			return null;
		}
		ItemState ret = null;
		TrackerLoader trackerLoader = TrackerLoader.getInstance();
		for(ItemState itemState: trackerLoader.getTracker().getItemStates()){
			if(source.equals(itemState.getName())){
				ret = itemState;
			}
		}
		return ret;
	}

}
