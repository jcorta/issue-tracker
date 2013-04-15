package edu.unlp.db.converters;

import org.dozer.DozerConverter;

import edu.unlp.db.domain.ItemPriority;
import edu.unlp.db.loader.TrackerLoader;

public class ItemPriorityToString extends DozerConverter<ItemPriority, String> {

	public ItemPriorityToString() {
		this(ItemPriority.class, String.class);
	}
	
	public ItemPriorityToString(Class<ItemPriority> prototypeA, Class<String> prototypeB) {
		super(prototypeA, prototypeB);
	}

	@Override
	public String convertTo(ItemPriority source, String destination) {
		if (source == null) {
			return null;
		}
		return source.getName();
	}

	@Override
	public ItemPriority convertFrom(String source, ItemPriority destination) {
		if (source == null) {
			return null;
		}
		ItemPriority ret = null;
		TrackerLoader trackerLoader = TrackerLoader.getInstance();
		for(ItemPriority itemPriority: trackerLoader.getTracker().getItemPriorities()){
			if(source.equals(itemPriority.getName())){
				ret = itemPriority;
			}
		}
		return ret;
	}

}
