package edu.unlp.db.service;

import java.util.Collection;

import edu.unlp.db.dto.ItemDto;
import edu.unlp.db.dto.ItemPriorityDto;
import edu.unlp.db.dto.ItemTypeDto;

public interface ItemService {
	public Collection<ItemTypeDto> getItemTypes();
	public ItemTypeDto createItemType(ItemTypeDto itemTypeDto);
	public Collection<ItemPriorityDto> getItemPriorities();
	public ItemPriorityDto createItemPriority(String name, String description);
	public Collection<ItemDto> getItems();
	public ItemDto createItem(ItemDto itemDto);
	public ItemDto editItem(ItemDto itemDto, String user);
	public ItemDto addCommentToItem(long itemId, String comment, String user);
}
