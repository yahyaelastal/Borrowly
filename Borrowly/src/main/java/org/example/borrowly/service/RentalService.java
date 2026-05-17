package org.example.borrowly.service;

import org.example.borrowly.model.Item;
import org.example.borrowly.model.Rental;
import org.example.borrowly.model.RentalStatus;
import org.example.borrowly.model.User;
import org.example.borrowly.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    /**
     * Create a new rental, automatically calculating total cost from item price and dates.
     */
    public Rental createRental(Item item, User borrower, LocalDate startDate, LocalDate endDate) {
        // Prevent renting starting in the past
        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Start date cannot be in the past");
        }

        long days = ChronoUnit.DAYS.between(startDate, endDate);
        if (days <= 0) {
            throw new IllegalArgumentException("End date must be after start date");
        }

        Rental rental = new Rental();
        rental.setItem(item);
        rental.setBorrower(borrower);
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);
        rental.setTotalCost(item.getPricePerDay() * days);

        // Auto-set status based on dates
        if (startDate.isAfter(LocalDate.now())) {
            rental.setStatus(RentalStatus.PENDING);
        } else {
            rental.setStatus(RentalStatus.ACTIVE);
        }

        return rentalRepository.save(rental);
    }

    public List<Rental> getRentalsByBorrower(User borrower) {
        return rentalRepository.findByBorrowerOrderByCreatedAtDesc(borrower);
    }

    public List<Rental> getRentalsByItemOwner(User owner) {
        return rentalRepository.findByItem_OwnerOrderByCreatedAtDesc(owner);
    }

    /**
     * Cancel a rental. Only the borrower can cancel, and only if not already completed/cancelled.
     */
    public boolean cancelRental(Long rentalId, User user) {
        Optional<Rental> rentalOpt = rentalRepository.findById(rentalId);
        if (rentalOpt.isEmpty()) {
            return false;
        }

        Rental rental = rentalOpt.get();

        // Only the borrower can cancel
        if (!rental.getBorrower().getId().equals(user.getId())) {
            return false;
        }

        // Cannot cancel already completed or cancelled rentals
        if (rental.getStatus() == RentalStatus.COMPLETED || rental.getStatus() == RentalStatus.CANCELLED) {
            return false;
        }

        rental.setStatus(RentalStatus.CANCELLED);
        rentalRepository.save(rental);
        return true;
    }
}
