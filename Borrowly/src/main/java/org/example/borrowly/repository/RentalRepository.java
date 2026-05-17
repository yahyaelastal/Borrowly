package org.example.borrowly.repository;

import org.example.borrowly.model.Rental;
import org.example.borrowly.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByBorrowerOrderByCreatedAtDesc(User borrower);

    List<Rental> findByItem_OwnerOrderByCreatedAtDesc(User owner);
}
