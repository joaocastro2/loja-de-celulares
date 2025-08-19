package br.com.phone.store.suppliers.controller;

import br.com.phone.store.suppliers.dto.RequestSuppliersDto;
import br.com.phone.store.suppliers.model.SuppliersModel;
import br.com.phone.store.suppliers.repository.SuppliersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suppliers")
public class SuppliersController {

    @Autowired
    private SuppliersRepository suppliersRepository;

    @PostMapping
    public ResponseEntity<SuppliersModel> registerSupplier(@RequestBody @Valid RequestSuppliersDto newSupplier) {
        SuppliersModel suppliersModel = new SuppliersModel(newSupplier);
        SuppliersModel saved = suppliersRepository.save(suppliersModel);
        return ResponseEntity.ok(saved);
    }
}
