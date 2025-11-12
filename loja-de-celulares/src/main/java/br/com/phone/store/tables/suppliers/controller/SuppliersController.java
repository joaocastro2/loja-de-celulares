package br.com.phone.store.tables.suppliers.controller;

import br.com.phone.store.tables.suppliers.dto.RequestSuppliersDto;
import br.com.phone.store.tables.suppliers.model.SuppliersModel;
import br.com.phone.store.tables.suppliers.repository.SuppliersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<List<SuppliersModel>> findAll(@RequestHeader(name = "Authorization", required = false) String token) {
        // Apenas um exemplo: aqui você poderia validar o token JWT
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(403).build(); // Forbidden
        }

        // Se quiser, você pode validar o JWT aqui usando sua lógica
        // Ex: JwtUtils.validateToken(token)

        List<SuppliersModel> suppliersQuery = suppliersRepository.findAll();
        return ResponseEntity.ok(suppliersQuery);
    }
}
