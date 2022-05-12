package com.brian.desafio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brian.desafio.entities.Collaborator;
import com.brian.desafio.service.CollaboratorService;

@RestController
@RequestMapping("/collaborators")
public class CollaboratorController {

	private final CollaboratorService collaboratorService;

	public CollaboratorController(CollaboratorService collaboratorService) {
		this.collaboratorService = collaboratorService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createCollaborator(@RequestBody Collaborator collaborator) {
		collaboratorService.creatOne(collaborator);
	}

	@GetMapping("/all")
	@ResponseStatus(HttpStatus.FOUND)
	public List<Collaborator> findAll() {
		return collaboratorService.findAll();
	}

	@GetMapping("/id/{id}")
	@ResponseStatus(HttpStatus.FOUND)
	public Collaborator findOneById(@PathVariable Long id) {
		return collaboratorService.findOneById(id);
	}

	@GetMapping("/cpf/{cpf}")
	@ResponseStatus(HttpStatus.FOUND)
	public Collaborator findOneByCpf(@PathVariable String cpf) {
		return collaboratorService.findOneByCpf(cpf);
	}

	@PutMapping("/id/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateOneById(@PathVariable Long id, @RequestBody Collaborator collaborator) {
		collaboratorService.updateOneById(id, collaborator);
	}

	@DeleteMapping("/id/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteOneById(@PathVariable Long id) {
		collaboratorService.deleteOneById(id);
	}

	@PostMapping(value = "/id/{id}/add", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void addCollaboratorItems(@PathVariable Long id, @RequestParam List<Long> items) {
		collaboratorService.addCollaboratorItem(id, items);
	}

	@PostMapping(value = "/id/{id}/remove", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void removeCollaboratorItems(@PathVariable Long id, @RequestParam List<Long> items) {
		collaboratorService.removeCollaboratorItem(id, items);
	}
}
