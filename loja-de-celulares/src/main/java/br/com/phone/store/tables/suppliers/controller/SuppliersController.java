package br.com.phone.store.tables.suppliers.controller;

import br.com.phone.store.tables.suppliers.dto.RequestSuppliersDto;
import br.com.phone.store.tables.suppliers.model.SuppliersModel;
import br.com.phone.store.tables.suppliers.repository.SuppliersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller responsible for managing supplier-related operations.
 *
 * <p>This controller provides an endpoint for registering new suppliers in the phone store system.
 * It is configured to accept cross-origin requests from the frontend running at http://localhost:5173.</p>
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/suppliers")
public class SuppliersController {

    @Autowired
    private SuppliersRepository suppliersRepository;

    /**
     * Registers a new supplier in the system.
     *
     * <p>This endpoint receives a {@link RequestSuppliersDto} object with supplier details,
     * validates the input, converts it into a {@link SuppliersModel}, and persists it using
     * the {@link SuppliersRepository}.</p>
     *
     * @param newSupplier DTO containing the supplier's registration data. Must be valid.
     * @return ResponseEntity containing the saved {@link SuppliersModel} object.
     */
    @PostMapping
    public ResponseEntity<SuppliersModel> registerSupplier(@RequestBody @Valid RequestSuppliersDto newSupplier) {
        SuppliersModel suppliersModel = new SuppliersModel(newSupplier);
        SuppliersModel saved = suppliersRepository.save(suppliersModel);
        return ResponseEntity.ok(saved);
    }
}
