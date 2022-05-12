package com.brian.desafio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.brian.desafio.entities.Collaborator;
import com.brian.desafio.entities.Item;
import com.brian.desafio.service.CollaboratorItemService;
import com.brian.desafio.service.CollaboratorService;
import com.brian.desafio.service.ItemService;

@Controller
@RequestMapping("/")
public class ViewController {

	private final CollaboratorItemService collaboratorItemService;
	private final CollaboratorService collaboratorService;
	private final ItemService itemService;

	public ViewController(CollaboratorItemService collaboratorItemService, CollaboratorService collaboratorService,
			ItemService itemService) {
		this.collaboratorItemService = collaboratorItemService;
		this.collaboratorService = collaboratorService;
		this.itemService = itemService;
	}

	@GetMapping
	public ModelAndView mainPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		modelAndView.addObject("participants", collaboratorItemService.findCollaboratorAndItems());
		return modelAndView;
	}

	@GetMapping("/items")
	public ModelAndView itemsPage() {
		ModelAndView modelAndView = new ModelAndView();
		var items = itemService.findAll();
		modelAndView.setViewName("item/items");
		modelAndView.addObject("itemsList", items);
		return modelAndView;
	}

	@GetMapping("/items/create")
	public ModelAndView itemCreate() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("item/create");
		modelAndView.addObject("item", new Item());
		return modelAndView;
	}

	@GetMapping("/items/edit/id/{id}")
	public ModelAndView itemCreate(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("item/edit");
		modelAndView.addObject("item", itemService.findOneById(id));
		return modelAndView;
	}

	@GetMapping("/collaborators")
	public ModelAndView collaboratorsPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("collaborator/collaborators");
		modelAndView.addObject("collaboratorsList", collaboratorService.findAll());
		return modelAndView;
	}

	@GetMapping("/collaborators/id/{id}/add-item")
	public ModelAndView collaboratorAddItem(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("collaborator/add-item");
		modelAndView.addObject("itemsList", itemService.findUnusedItems());
		modelAndView.addObject("collaborator", collaboratorService.findOneById(id));
		return modelAndView;
	}

	@GetMapping("/collaborators/id/{id}/remove-item")
	public ModelAndView collaboratorRemoveItem(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("collaborator/remove-item");
		modelAndView.addObject("itemsList", collaboratorService.findCollaboratorItems(id));
		modelAndView.addObject("collaborator", collaboratorService.findOneById(id));
		return modelAndView;
	}

	@GetMapping("/collaborators/create")
	public ModelAndView collaboratorCreate() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("collaborator/create");
		modelAndView.addObject("collaborator", new Collaborator());
		return modelAndView;
	}

	@GetMapping("/collaborators/edit/id/{id}")
	public ModelAndView collaboratorCreate(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("collaborator/edit");
		modelAndView.addObject("collaborator", collaboratorService.findOneById(id));
		return modelAndView;
	}
}
