package com.brian.desafio.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brian.desafio.entities.Item;
import com.brian.desafio.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createItem(@RequestBody Item item) {
        itemService.createItem(item);
    }

    @GetMapping(value = "/id/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.FOUND)
    public Item findOneById(@PathVariable Long id) {
        return itemService.findOneById(id);
    }

    @GetMapping(value = "/name/{name}", produces = "application/json")
    @ResponseStatus(HttpStatus.FOUND)
    public Item findOneById(@PathVariable String name) {
        return itemService.findOneByName(name);
    }

    @PutMapping(value = "/id/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void updateOneById(@PathVariable Long id, @RequestBody Item item) {
        itemService.updateItemById(id, item);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOneById(@PathVariable Long id) {
        itemService.deleteOneById(id);
    }
}
