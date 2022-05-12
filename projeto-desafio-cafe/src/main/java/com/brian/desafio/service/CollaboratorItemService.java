package com.brian.desafio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.brian.desafio.entities.Item;
import com.brian.desafio.entities.dto.CollaboratorItems;
import com.brian.desafio.exceptions.item.ItemAlreadyInUseException;
import com.brian.desafio.exceptions.item.ItemNotFoundException;
import com.brian.desafio.repositories.CollaboratorRepository;
import com.brian.desafio.repositories.ItemRepository;

@Service
public class CollaboratorItemService {

	private final CollaboratorRepository collaboratorRepository;
	private final ItemRepository itemRepository;

	public CollaboratorItemService(CollaboratorRepository collaboratorRepository, ItemRepository itemRepository) {
		this.collaboratorRepository = collaboratorRepository;
		this.itemRepository = itemRepository;
	}

	public List<Item> findCollaboratorItems(Long id) {
		return itemRepository.findCollaboratorItems(id);
	}

	public List<CollaboratorItems> findCollaboratorAndItems() {
		List<CollaboratorItems> collaboratorItems = new ArrayList<>();
		collaboratorRepository.findCollaboratorAndItems().forEach(ci -> {
			var data = ci.split(",");
			var items = data[2].split("\\|");
			collaboratorItems.add(new CollaboratorItems(data[0], data[1], items));
		});
		return collaboratorItems;
	}

	public void addAllItems(Long id, List<Long> items) {
		for (Long x : items) {
			var item = itemRepository.findOneById(x).orElseThrow(ItemNotFoundException::new);
			if (itemRepository.findOneUsed(id, item.getId()).isPresent()) {
				throw new ItemAlreadyInUseException();
			}
			collaboratorRepository.addCollaboratorItem(id, item.getId());
		}
	}

	public void removeAllItems(Long id, List<Long> items) {
		for (Long itemId : items) {
			collaboratorRepository.removeCollaboratorItem(id, itemId);
		}
	}
}
