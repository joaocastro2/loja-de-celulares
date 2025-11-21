package br.com.phone.store.tables.customers.controller;

import br.com.phone.store.tables.customers.dto.RequestCustomersDto;
import br.com.phone.store.tables.customers.model.CustomersModel;
import br.com.phone.store.tables.customers.repository.CustomersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller responsible for handling customer-related operations.
 *
 * <p>This controller provides endpoints for managing customer data in the phone store system.
 * It is configured to accept cross-origin requests from the frontend running at http://localhost:5173.</p>
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private CustomersRepository customersRepository;

    /**
     * Registers a new customer in the system.
     *
     * <p>This endpoint receives a {@link RequestCustomersDto} object with customer details,
     * validates the input, converts it into a {@link CustomersModel}, and persists it using
     * the {@link CustomersRepository}.</p>
     *
     * @param newCustomer DTO containing the customer's registration data. Must be valid.
     * @return ResponseEntity containing the saved {@link CustomersModel} object.
     */
    @PostMapping
    public ResponseEntity<CustomersModel> registerCustomer(@RequestBody @Valid RequestCustomersDto newCustomer) {
        CustomersModel customersModel = new CustomersModel(newCustomer);
        CustomersModel saved = customersRepository.save(customersModel);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<CustomersModel>> findAll(@RequestHeader(name = "Authorization", required = false) String token) {
        if (token == null || !token.startsWith("Bearer" )){
            return ResponseEntity.status(401).build();
        }

        List<CustomersModel> customersQuerry = customersRepository.findAll();
        return ResponseEntity.ok(customersQuerry);
    }

}
