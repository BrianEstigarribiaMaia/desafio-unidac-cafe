package com.brian.desafio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.brian.desafio.entities.Collaborator;
import com.brian.desafio.entities.Item;
import com.brian.desafio.exceptions.collaborator.CollaboratorAlreadyExistsException;
import com.brian.desafio.exceptions.collaborator.CollaboratorNotFoundException;
import com.brian.desafio.repositories.CollaboratorRepository;

@Service
public class CollaboratorService {

	private final CollaboratorRepository collaboratorRepository;
	private final CollaboratorItemService collaboratorItemService;

	public CollaboratorService(CollaboratorRepository collaboratorRepository,
			CollaboratorItemService collaboratorItemService) {
		this.collaboratorRepository = collaboratorRepository;
		this.collaboratorItemService = collaboratorItemService;
	}

	public void creatOne(Collaborator collaborator) {
		if (collaboratorRepository.findOneByCpf(collaborator.getCpf()).isPresent()) {
			throw new CollaboratorAlreadyExistsException();
		}
		collaboratorRepository.createOne(collaborator.getFullName(), collaborator.getCpf());
	}

	public List<Collaborator> findAll() {
		return collaboratorRepository.findAllCollaborators();
	}

	public Collaborator findOneById(Long id) {
		return collaboratorRepository.findOneById(id).orElseThrow(CollaboratorNotFoundException::new);
	}

	public Collaborator findOneByCpf(String cpf) {
		return collaboratorRepository.findOneByCpf(cpf).orElseThrow(CollaboratorNotFoundException::new);
	}

	public void updateOneById(Long id, Collaborator collaborator) {
		if (findOneById(id) != null) {
			collaboratorRepository.updateOneById(id, collaborator.getFullName(), collaborator.getCpf());
		}
	}

	public void deleteOneById(Long id) {
		if (findOneById(id) != null) {
			collaboratorRepository.removeRelationship(id);
			collaboratorRepository.deleteOneById(id);
		}
	}

	public void addCollaboratorItem(Long id, List<Long> items) {
		if (findOneById(id) != null) {
			collaboratorItemService.addAllItems(id, items);
		}
	}

	public void removeCollaboratorItem(Long id, List<Long> items) {
		if (findOneById(id) != null) {
			collaboratorItemService.removeAllItems(id, items);
		}
		collaboratorItemService.removeAllItems(id, items);
	}

	public List<Item> findCollaboratorItems(Long id) {
		if (findOneById(id) != null) {
			return collaboratorItemService.findCollaboratorItems(id);
		}
		return null;
	}
}
