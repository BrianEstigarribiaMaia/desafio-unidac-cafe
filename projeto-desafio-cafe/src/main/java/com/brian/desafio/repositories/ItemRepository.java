package com.brian.desafio.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brian.desafio.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO ITEMS (NAME) VALUES (?)", nativeQuery = true)
	void createOne(String name);

	@Query(value = "SELECT * FROM ITEMS ORDER BY ID", nativeQuery = true)
	List<Item> findAllItems();

	@Query(value = "SELECT * FROM ITEMS i WHERE i.ID = ?", nativeQuery = true)
	Optional<Item> findOneById(Long id);

	@Query(value = "SELECT * FROM ITEMS i WHERE i.NAME = ?", nativeQuery = true)
	Optional<Item> findOneByName(String name);

	@Modifying
	@Transactional
	@Query(value = "UPDATE ITEMS SET NAME = ?2 WHERE ID = ?1", nativeQuery = true)
	void updateOneById(Long id, String name);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM ITEMS WHERE ID = ? ", nativeQuery = true)
	void deleteOneById(Long id);

	@Query(value = "SELECT * FROM ITEMS i WHERE i.ID IN (SELECT ci.ITEM_ID FROM COLLABORATOR_ITEMS ci "
			+ "WHERE ci.ITEM_ID = ?2 AND ci.COLLABORATOR_ID != ?1)", nativeQuery = true)
	Optional<Item> findOneUsed(Long collaboratorId, Long itemId);

	@Query(value = "SELECT * FROM ITEMS i WHERE i.ID NOT IN (SELECT ITEM_ID FROM COLLABORATOR_ITEMS) ORDER BY i.ID", nativeQuery = true)
	List<Item> findUnusedItems();

	@Query(value = "SELECT * FROM ITEMS i WHERE i.ID IN (SELECT ci.ITEM_ID FROM COLLABORATOR_ITEMS ci "
			+ "WHERE ci.COLLABORATOR_ID = ?) ORDER BY i.ID", nativeQuery = true)
	List<Item> findCollaboratorItems(Long collaboratorId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM COLLABORATOR_ITEMS WHERE ITEM_ID = ?", nativeQuery = true)
	void removeRelationship(Long id);
}
