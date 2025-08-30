package br.com.phone.store.customers.controller;

import br.com.phone.store.customers.dto.RequestCustomersDto;
import br.com.phone.store.customers.model.CustomersModel;
import br.com.phone.store.customers.repository.CustomersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private CustomersRepository customersRepository;

    @PostMapping
    public ResponseEntity<CustomersModel> registerCustomer(@RequestBody @Valid RequestCustomersDto newCustomer) {
        CustomersModel customersModel = new CustomersModel(newCustomer);
        CustomersModel saved = customersRepository.save(customersModel);
        return ResponseEntity.ok(saved);
    }
}
