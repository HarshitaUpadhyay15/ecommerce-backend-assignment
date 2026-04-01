package com.example.ecommerce.config;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        seedUser("testuser", "password123", Role.ROLE_USER);
        seedUser("admin", "admin123", Role.ROLE_ADMIN);
        System.out.println("==============================================");
        System.out.println("  Sample users created:");
        System.out.println("  User  -> username: testuser  | password: password123");
        System.out.println("  Admin -> username: admin      | password: admin123");
        System.out.println("  Login endpoint: POST /api/auth/login");
        System.out.println("==============================================");
    }

    private void seedUser(String username, String password, Role role) {
        if (!userRepository.existsByUsername(username)) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(role);
            userRepository.save(user);

            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }
    }
}
