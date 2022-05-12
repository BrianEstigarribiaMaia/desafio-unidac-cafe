package com.brian.desafio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.brian.desafio.entities.Item;
import com.brian.desafio.exceptions.item.ItemAlreadyExistsException;
import com.brian.desafio.exceptions.item.ItemNotFoundException;
import com.brian.desafio.repositories.ItemRepository;

@Service
public class ItemService {

	private final ItemRepository itemRepository;

	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public void createItem(Item item) {
		if (itemRepository.findOneByName(item.getName()).isPresent()) {
			throw new ItemAlreadyExistsException();
		}
		itemRepository.createOne(item.getName());
	}

	public List<Item> findAll() {
		return itemRepository.findAllItems();
	}

	public Item findOneById(Long id) {
		return itemRepository.findOneById(id).orElseThrow(ItemNotFoundException::new);
	}

	public Item findOneByName(String name) {
		return itemRepository.findOneByName(name).orElseThrow(ItemNotFoundException::new);
	}

	public void updateItemById(Long id, Item item) {
		if (findOneById(id) != null) {
			itemRepository.updateOneById(id, item.getName());
		}
	}

	public void deleteOneById(Long id) {
		if (findOneById(id) != null) {
			itemRepository.removeRelationship(id);
			itemRepository.deleteOneById(id);
		}
	}

	public List<Item> findUnusedItems() {
		return itemRepository.findUnusedItems();
	}
}
