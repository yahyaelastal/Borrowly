package org.example.borrowly.controller;

import org.example.borrowly.model.Item;
import org.example.borrowly.model.Rental;
import org.example.borrowly.model.User;
import org.example.borrowly.service.ItemService;
import org.example.borrowly.service.RentalService;
import org.example.borrowly.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final UserService userService;
    private final ItemService itemService;
    private final RentalService rentalService;

    public DashboardController(UserService userService, ItemService itemService,
                               RentalService rentalService) {
        this.userService = userService;
        this.itemService = itemService;
        this.rentalService = rentalService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Items the user is borrowing
        List<Rental> myRentals = rentalService.getRentalsByBorrower(user);

        // Items the user has listed
        List<Item> myListings = itemService.getItemsByOwner(user);

        // Rentals of user's items (income tracking)
        List<Rental> incomingRentals = rentalService.getRentalsByItemOwner(user);

        // Stats
        long activeRentals = myRentals.stream()
                .filter(r -> r.getStatus().name().equals("ACTIVE") || r.getStatus().name().equals("PENDING"))
                .count();
        double totalEarned = incomingRentals.stream()
                .mapToDouble(Rental::getTotalCost)
                .sum();

        model.addAttribute("user", user);
        model.addAttribute("myRentals", myRentals);
        model.addAttribute("myListings", myListings);
        model.addAttribute("activeRentals", activeRentals);
        model.addAttribute("activeListings", myListings.size());
        model.addAttribute("totalEarned", totalEarned);

        return "dashboard";
    }
}
