package br.com.phone.store.tables.suppliers.controller;

import br.com.phone.store.tables.suppliers.dto.RequestSuppliersDto;
import br.com.phone.store.tables.suppliers.model.SuppliersModel;
import br.com.phone.store.tables.suppliers.repository.SuppliersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // SuppliersController.java (Versão que exige token e valida)
    @GetMapping
    public ResponseEntity<List<SuppliersModel>> findAll(@RequestHeader(name = "Authorization", required = false) String token) {

        // Se o token for obrigatório para o acesso:
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build(); // 401 Unauthorized
        }

        // AQUI ENTRARIA A LÓGICA DE VALIDAÇÃO REAL DO SEU TOKEN JWT
        // Se o token for inválido, também retorne 401

        List<SuppliersModel> suppliersQuery = suppliersRepository.findAll();
        return ResponseEntity.ok(suppliersQuery);
    }
}