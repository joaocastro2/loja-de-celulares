package br.com.phone.store.tables.suppliers.controller;

import br.com.phone.store.tables.stock.model.StockModel;
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

    /**
     * Registers a new Supplier in the stock.
     *
     * <p>Validates the supplier ID, creates a {@link SuppliersModel} from the provided DTO,
     * and persists it in the database.</p>
     *
     * @param newSupplier DTO containing product and supplier details.
     * @return ResponseEntity with the saved Supplier or error message.
     */
    @PostMapping
    public ResponseEntity<SuppliersModel> registerSupplier(@RequestBody @Valid RequestSuppliersDto newSupplier) {
        SuppliersModel suppliersModel = new SuppliersModel(newSupplier);
        SuppliersModel saved = suppliersRepository.save(suppliersModel);
        return ResponseEntity.ok(saved);
    }


    /**
     *
     * @param token (Token validation for request permission)
     * @return Retrieves All Suppliers
     */
    @GetMapping
    public ResponseEntity<List<SuppliersModel>> findAll(@RequestHeader(name = "Authorization", required = false) String token) {

        // Se o token for obrigatório para o acesso:
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build(); // 401 Unauthorized
        }

        List<SuppliersModel> suppliersQuery = suppliersRepository.findAll();
        return ResponseEntity.ok(suppliersQuery);
    }
}