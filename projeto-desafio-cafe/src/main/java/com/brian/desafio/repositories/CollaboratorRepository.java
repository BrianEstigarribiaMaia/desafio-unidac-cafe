package com.brian.desafio.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brian.desafio.entities.Collaborator;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO COLLABORATORS (FULL_NAME, CPF) VALUES (?1, ?2)", nativeQuery = true)
	void createOne(String fullName, String cpf);

	@Query(value = "SELECT * FROM COLLABORATORS ORDER BY ID", nativeQuery = true)
	List<Collaborator> findAllCollaborators();

	@Query(value = "SELECT * FROM COLLABORATORS c WHERE c.ID = ?", nativeQuery = true)
	Optional<Collaborator> findOneById(Long id);

	@Query(value = "SELECT * FROM COLLABORATORS c WHERE c.CPF = ?", nativeQuery = true)
	Optional<Collaborator> findOneByCpf(String cpf);

	@Modifying
	@Transactional
	@Query(value = "UPDATE COLLABORATORS SET FULL_NAME = ?2, CPF = ?3 WHERE ID = ?1", nativeQuery = true)
	void updateOneById(Long id, String fullName, String cpf);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM COLLABORATORS WHERE ID = ?", nativeQuery = true)
	void deleteOneById(Long id);

	@Query(value = "SELECT c.FULL_NAME, c.CPF, STRING_AGG(i.NAME, '|') FROM COLLABORATORS c "
			+ "INNER JOIN COLLABORATOR_ITEMS ci ON c.ID  = ci.COLLABORATOR_ID "
			+ "INNER JOIN ITEMS i ON  i.ID = ci.ITEM_ID GROUP BY c.ID, c.FULL_NAME, c.CPF ORDER BY c.ID", nativeQuery = true)
	List<String> findCollaboratorAndItems();

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO COLLABORATOR_ITEMS (COLLABORATOR_ID, ITEM_ID) VALUES (?1, ?2)", nativeQuery = true)
	void addCollaboratorItem(Long collaboratorId, Long itemId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM COLLABORATOR_ITEMS WHERE ID IN (SELECT ci.ID FROM COLLABORATOR_ITEMS ci "
			+ "WHERE ci.COLLABORATOR_ID  = ?1 AND ci.ITEM_ID = ?2);", nativeQuery = true)
	void removeCollaboratorItem(Long collaboratorId, Long itemId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM COLLABORATOR_ITEMS WHERE COLLABORATOR_ID = ?", nativeQuery = true)
	void removeRelationship(Long id);
}
