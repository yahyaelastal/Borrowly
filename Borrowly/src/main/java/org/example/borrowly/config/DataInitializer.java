package org.example.borrowly.config;

import org.example.borrowly.model.Item;
import org.example.borrowly.model.User;
import org.example.borrowly.repository.ItemRepository;
import org.example.borrowly.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Seeds the database with sample data on startup if no users exist.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, ItemRepository itemRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Only seed if database is empty
        if (userRepository.count() > 0) {
            return;
        }

        // Create demo users
        User sarah = new User("Sarah Chen", "sarah@example.com",
                passwordEncoder.encode("password123"));
        sarah = userRepository.save(sarah);

        User mike = new User("Mike Johnson", "mike@example.com",
                passwordEncoder.encode("password123"));
        mike = userRepository.save(mike);

        User lisa = new User("Lisa Thompson", "lisa@example.com",
                passwordEncoder.encode("password123"));
        lisa = userRepository.save(lisa);

        // Create sample items
        Item camera = new Item();
        camera.setName("Professional Camera Lens");
        camera.setDescription("Professional-grade Canon EF 24-70mm f/2.8L II USM lens. Perfect for everything from landscapes to portraits. Meticulously maintained, glass is flawless. Comes with front and rear caps, lens hood, and a protective pouch.");
        camera.setCategory("Photography");
        camera.setPricePerDay(35.0);
        camera.setSecurityDeposit(150.0);
        camera.setLocation("Downtown, NY");
        camera.setImageUrl("https://images.unsplash.com/photo-1768081529866-a5ec754fe14c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjYW1lcmElMjBsZW5zJTIwcGhvdG9ncmFwaHklMjBlcXVpcG1lbnR8ZW58MXx8fHwxNzc1NjYxMDA1fDA&ixlib=rb-4.1.0&q=80&w=1080");
        camera.setOwner(sarah);
        itemRepository.save(camera);

        Item drill = new Item();
        drill.setName("Cordless Power Drill");
        drill.setDescription("DeWalt 20V MAX cordless drill with lithium-ion battery. Includes a full set of drill bits and a carrying case. Perfect for home improvement projects. Battery holds charge for extended use.");
        drill.setCategory("Tools");
        drill.setPricePerDay(18.0);
        drill.setSecurityDeposit(80.0);
        drill.setLocation("Brooklyn, NY");
        drill.setImageUrl("https://images.unsplash.com/photo-1770763233593-74dfd0da7bf0?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwb3dlciUyMGRyaWxsJTIwdG9vbHMlMjB3b3Jrc2hvcHxlbnwxfHx8fDE3NzU2NjEwMDV8MA&ixlib=rb-4.1.0&q=80&w=1080");
        drill.setOwner(mike);
        itemRepository.save(drill);

        Item tent = new Item();
        tent.setName("4-Person Camping Tent");
        tent.setDescription("Spacious 4-person tent perfect for family camping trips. Features easy setup, waterproof construction, and excellent ventilation. Includes rainfly, stakes, and carrying bag.");
        tent.setCategory("Outdoor");
        tent.setPricePerDay(25.0);
        tent.setSecurityDeposit(100.0);
        tent.setLocation("Queens, NY");
        tent.setImageUrl("https://images.unsplash.com/photo-1774886302070-0553eb9b4215?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjYW1waW5nJTIwdGVudCUyMG91dGRvb3IlMjBlcXVpcG1lbnR8ZW58MXx8fHwxNzc1NjMyNzE4fDA&ixlib=rb-4.1.0&q=80&w=1080");
        tent.setOwner(lisa);
        itemRepository.save(tent);

        Item kayak = new Item();
        kayak.setName("Two-Person Kayak");
        kayak.setDescription("Tandem kayak perfect for exploring lakes and calm rivers. Lightweight and stable design. Includes two paddles and life vests. Great for beginners and experienced paddlers alike.");
        kayak.setCategory("Sports");
        kayak.setPricePerDay(45.0);
        kayak.setSecurityDeposit(200.0);
        kayak.setLocation("Staten Island, NY");
        kayak.setImageUrl("https://images.unsplash.com/photo-1764182160030-f274751838a3?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxrYXlhayUyMHdhdGVyJTIwc3BvcnRzfGVufDF8fHx8MTc3NTY2MTAwNnww&ixlib=rb-4.1.0&q=80&w=1080");
        kayak.setOwner(sarah);
        itemRepository.save(kayak);

        System.out.println("=== Borrowly: Sample data initialized ===");
        System.out.println("Demo accounts: sarah@example.com / mike@example.com / lisa@example.com");
        System.out.println("Password for all: password123");
    }
}
