package br.com.phone.store.tables.users.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling user-related operations.
 *
 * <p>This controller currently provides a basic endpoint for testing or confirming
 * that the user route is accessible.</p>
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    /**
     * Basic GET endpoint for the {@code /users} route.
     *
     * <p>Returns a simple success message, typically used for testing connectivity
     * or verifying that the controller is active.</p>
     *
     * @return ResponseEntity containing the string "Sucesso".
     */
    @GetMapping
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("Sucesso");
    }
}
