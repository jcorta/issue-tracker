package edu.unlp.db.converters;

import org.dozer.DozerConverter;

import edu.unlp.db.domain.ItemType;
import edu.unlp.db.loader.TrackerLoader;

public class ItemTypeToString extends DozerConverter<ItemType, String> {

	public ItemTypeToString() {
		this(ItemType.class, String.class);
	}
	
	public ItemTypeToString(Class<ItemType> prototypeA, Class<String> prototypeB) {
		super(prototypeA, prototypeB);
	}

	@Override
	public String convertTo(ItemType source, String destination) {
		if (source == null) {
			return null;
		}
		return source.getName();
	}

	@Override
	public ItemType convertFrom(String source, ItemType destination) {
		if (source == null) {
			return null;
		}
		ItemType ret = null;
		TrackerLoader trackerLoader = TrackerLoader.getInstance();
		for(ItemType itemType: trackerLoader.getTracker().getItemTypes()){
			if(source.equals(itemType.getName())){
				ret = itemType;
			}
		}
		return ret;
	}

}
