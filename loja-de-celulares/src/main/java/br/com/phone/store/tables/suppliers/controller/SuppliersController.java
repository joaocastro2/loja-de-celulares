package br.com.phone.store.tables.suppliers.controller;

import br.com.phone.store.tables.suppliers.dto.RequestSuppliersDto;
import br.com.phone.store.tables.suppliers.model.SuppliersModel;
import br.com.phone.store.tables.suppliers.repository.SuppliersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
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
