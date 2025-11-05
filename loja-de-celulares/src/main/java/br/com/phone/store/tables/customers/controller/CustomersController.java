package br.com.phone.store.tables.customers.controller;

import br.com.phone.store.tables.customers.dto.RequestCustomersDto;
import br.com.phone.store.tables.customers.model.CustomersModel;
import br.com.phone.store.tables.customers.repository.CustomersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private CustomersRepository customersRepository;

    //Method responsible for registering a new customer
    @PostMapping
    public ResponseEntity<CustomersModel> registerCustomer(@RequestBody @Valid RequestCustomersDto newCustomer) {
        CustomersModel customersModel = new CustomersModel(newCustomer);
        CustomersModel saved = customersRepository.save(customersModel);
        return ResponseEntity.ok(saved);
    }
}
