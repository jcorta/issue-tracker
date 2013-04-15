package edu.unlp.db.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.unlp.db.dto.ItemDto;
import edu.unlp.db.dto.ItemPriorityDto;
import edu.unlp.db.dto.ItemTypeDto;
import edu.unlp.db.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	ItemService itemService;

	@RequestMapping(value = "/itemTypes", method = RequestMethod.GET)
	public @ResponseBody
	Collection<ItemTypeDto> getItemTypes() {
		return itemService.getItemTypes();
	}

	@RequestMapping(value = "/itemType", method = RequestMethod.POST)
	public @ResponseBody
	ItemTypeDto createItemType(@RequestBody ItemTypeDto itemTypeDto) {
		return itemService.createItemType(itemTypeDto);
	}

	@RequestMapping(value = "/itemPriorities", method = RequestMethod.GET)
	public @ResponseBody
	Collection<ItemPriorityDto> getItemPriorities() {
		return itemService.getItemPriorities();
	}

	@RequestMapping(value = "/itemPriority/{name}/{description}", method = RequestMethod.POST)
	public @ResponseBody
	ItemPriorityDto createItemPriority(@PathVariable String name,
			@PathVariable String description) {
		return itemService.createItemPriority(name, description);
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public @ResponseBody
	Collection<ItemDto> getItems() {
		return itemService.getItems();
	}

	@RequestMapping(value = "/item", method = RequestMethod.POST)
	public @ResponseBody
	ItemDto createItem(@RequestBody ItemDto itemDto) {
		return itemService.createItem(itemDto);
	}

	@RequestMapping(value = "/item/{user}", method = RequestMethod.PUT)
	public @ResponseBody
	ItemDto editItem(@RequestBody ItemDto itemDto, @PathVariable String user) {
		return itemService.editItem(itemDto, user);
	}

	@RequestMapping(value = "/item/addComment/{itemId}/{user}/{comment}", method = RequestMethod.POST)
	public @ResponseBody
	ItemDto editItem(@PathVariable long itemId, @PathVariable String user,
			@PathVariable String comment) {
		return itemService.addCommentToItem(itemId, comment, user);
	}

}
