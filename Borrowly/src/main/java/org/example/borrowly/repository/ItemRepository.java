package org.example.borrowly.repository;

import org.example.borrowly.model.Item;
import org.example.borrowly.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByOwner(User owner);

    List<Item> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCategoryContainingIgnoreCase(
            String name, String description, String category);

    List<Item> findAllByOrderByCreatedAtDesc();
}
