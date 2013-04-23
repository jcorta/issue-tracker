package edu.unlp.db.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.util.Assert;

import edu.unlp.db.domain.Item;
import edu.unlp.db.domain.ItemComment;
import edu.unlp.db.domain.ItemPriority;
import edu.unlp.db.domain.ItemStateHistory;
import edu.unlp.db.domain.ItemType;
import edu.unlp.db.dto.ItemDto;
import edu.unlp.db.dto.ItemPriorityDto;
import edu.unlp.db.dto.ItemTypeDto;
import edu.unlp.db.service.ItemService;
import edu.unlp.db.service.PartyServiceInternal;

public class ItemServiceImpl extends EntityService implements ItemService {
	PartyServiceInternal partyService;

	public Collection<ItemTypeDto> getItemTypes() {
		Collection<ItemType> itemTypes = getTracker().getItemTypes();
		Collection<ItemTypeDto> ret = new ArrayList<ItemTypeDto>();
		for (ItemType itemType : itemTypes) {
			ret.add(mapper.map(itemType, ItemTypeDto.class));
		}
		return ret;
	}

	public ItemTypeDto createItemType(ItemTypeDto itemTypeDto) {
		Assert.notNull(itemTypeDto.getName(), "name no puede ser null");
		Assert.notNull(itemTypeDto.getWorkflow(), "workflow no puede ser null");

		ItemType itemType = mapper.map(itemTypeDto, ItemType.class);
		Assert.notNull(itemType.getWorkflow(),
				"workflow no puede ser null. No se encontro uno");

		getTracker().addItemType(itemType);
		trackerLoader.refreshOids();

		return mapper.map(itemType, ItemTypeDto.class);
	}

	public Collection<ItemPriorityDto> getItemPriorities() {
		Collection<ItemPriority> itemPriorities = getTracker()
				.getItemPriorities();
		Collection<ItemPriorityDto> ret = new ArrayList<ItemPriorityDto>();
		for (ItemPriority itemPriority : itemPriorities) {
			ret.add(mapper.map(itemPriority, ItemPriorityDto.class));
		}
		return ret;
	}

	public ItemPriorityDto createItemPriority(String name, String description) {
		Assert.notNull(name, "name no puede ser null");

		ItemPriority itemPriority = new ItemPriority();
		itemPriority.setName(name);
		itemPriority.setDescription(description);
		getTracker().addItemPriority(itemPriority);
		trackerLoader.refreshOids();
		
		return mapper.map(itemPriority, ItemPriorityDto.class);
	}

	public Collection<ItemDto> getItems() {
		Collection<Item> items = getTracker().getItems();
		Collection<ItemDto> ret = new ArrayList<ItemDto>();
		for (Item item : items) {
			ret.add(mapper.map(item, ItemDto.class));
		}
		return ret;
	}

	public ItemDto createItem(ItemDto itemDto) {
		Assert.notNull(itemDto.getSubject(), "Subject no puede ser null");
		Assert.notNull(itemDto.getDescription(),
				"Description no puede ser null");
		Assert.notNull(itemDto.getItemTypeName(), "Type no puede ser null");
		Assert.notNull(itemDto.getUser(), "User no puede ser null");

		Item item = mapper.map(itemDto, Item.class);
		Assert.notNull(item.getType(),
				"Type no puede ser null. No se encontro uno");

		item.setCreated(new Date());
		item.setComments(new HashSet<ItemComment>());
		item.setStateHistory(new HashSet<ItemStateHistory>());

		item.setInicialState(item.getState());

		getTracker().addItem(item);
		trackerLoader.refreshOids();

		return mapper.map(item, ItemDto.class);
	}

	public ItemDto editItem(ItemDto itemDto, String user) {
		Assert.notNull(itemDto.getOid() > 0, "Oid debe ser valido");
		Assert.notNull(itemDto.getVersion() >= 0,
				"Version tiene que ser un valor valido");
		Assert.notNull(user, "User no puede ser null");

		Item item = mapper.map(itemDto, Item.class);

		Item newItem = getItemById(item.getOid());
		newItem.setSubject(item.getSubject());
		newItem.setDescription(item.getDescription());
		newItem.changeStateTo(item.getState(), partyService.getUserByUsername(user));
		newItem.setPriority(item.getPriority());

		return mapper.map(newItem, ItemDto.class);
	}

	public ItemDto addCommentToItem(long itemId, String comment, String user) {
		Assert.notNull(itemId > 0, "Oid debe ser valido");
		Assert.notNull(comment, "Comment no pude ser null");
		Assert.notNull(user, "Comment no pude ser null");
		
		Item item = getItemById(itemId);
		
		ItemComment itemComment = new ItemComment();
		itemComment.setComment(comment);
		itemComment.setDate(new Date());
		itemComment.setUser(partyService.getUserByUsername(user));
		
		item.addComment(itemComment);
		
		return mapper.map(item, ItemDto.class);
	}

	private Item getItemById(long oid) {
		Item ret = null;
		for (Item item : getTracker().getItems()) {
			if (item.getOid() == oid) {
				ret = item;
			}
		}
		if (ret == null) {
			throw new RuntimeException("No se encontro item con id " + oid);
		}
		return ret;
	}

	public PartyServiceInternal getPartyService() {
		return partyService;
	}

	public void setPartyService(PartyServiceInternal partyService) {
		this.partyService = partyService;
	}
	
}