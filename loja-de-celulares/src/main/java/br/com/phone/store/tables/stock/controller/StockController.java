package br.com.phone.store.tables.stock.controller;

import br.com.phone.store.tables.stock.dto.RequestStockDto;
import br.com.phone.store.tables.stock.model.StockModel;
import br.com.phone.store.tables.stock.repository.StockRepository;
import br.com.phone.store.tables.suppliers.model.SuppliersModel;
import br.com.phone.store.tables.suppliers.repository.SuppliersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private SuppliersRepository supplierRepository;

    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody @Valid RequestStockDto newProduct) {
        try {
            SuppliersModel supplier = getSupplierOrThrow(newProduct.supplier_id());
            StockModel stockModel = new StockModel(newProduct, supplier);
            stockRepository.save(stockModel);
            return ResponseEntity.ok(stockModel);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("supplier_id inválido ou malformado.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/id/{product_id}")
    public ResponseEntity<List<StockModel>> findById(@PathVariable String product_id) {
        List<StockModel> productById = stockRepository.findByProductNameIgnoreCase(product_id);
        if (productById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productById);
    }

    @GetMapping
    public ResponseEntity<List<StockModel>> listAllActive() {
        List<StockModel> stockQuery = stockRepository.findAllByActiveTrue();
        return ResponseEntity.ok(stockQuery);
    }

    @GetMapping("/name/{product_name}")
    public ResponseEntity<List<StockModel>> findByProductName(@PathVariable String product_name) {
        Set<StockModel> finalResult = new LinkedHashSet<>();
        finalResult.addAll(stockRepository.findByProductNameIgnoreCase(product_name));
        finalResult.addAll(stockRepository.findByProductNameContainingIgnoreCase(product_name));

        if (finalResult.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ArrayList<>(finalResult));
    }

    private SuppliersModel getSupplierOrThrow(String supplierId) {
        UUID uuid = UUID.fromString(supplierId);
        return supplierRepository.findById(UUID.fromString(String.valueOf(uuid)))
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    }
}