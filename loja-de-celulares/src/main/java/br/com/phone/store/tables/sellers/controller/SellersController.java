package br.com.phone.store.tables.sellers.controller;

import br.com.phone.store.tables.sellers.dto.RequestSellersDto;
import br.com.phone.store.tables.sellers.model.SellersModel;
import br.com.phone.store.tables.sellers.repository.SellersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller responsible for handling seller-related operations.
 *
 * <p>This controller provides an endpoint for registering new sellers in the phone store system.
 * It is configured to accept cross-origin requests from the frontend running at http://localhost:5173.</p>
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/sellers")
public class SellersController {

    @Autowired
    private SellersRepository sellersRepository;

    /**
     * Registers a new seller in the system.
     *
     * <p>This endpoint receives a {@link RequestSellersDto} object with seller details,
     * validates the input, converts it into a {@link SellersModel}, and persists it using
     * the {@link SellersRepository}.</p>
     *
     * @param newSeller DTO containing the seller's registration data. Must be valid.
     * @return ResponseEntity containing the saved {@link SellersModel} object.
     */
    @PostMapping
    public ResponseEntity<SellersModel> registerSeller(@RequestBody @Valid RequestSellersDto newSeller) {
        SellersModel sellersModel = new SellersModel(newSeller);
        SellersModel saved = sellersRepository.save(sellersModel);
        return ResponseEntity.ok(saved);
    }

    /**
     *
     * @param token (Token validation for request permission)
     * @return Retrieves All Sellers.
     */
    @GetMapping
    public ResponseEntity<List<SellersModel>> findAll(@RequestHeader(name = "Authorization", required = false) String token){
        if (token == null || !token.startsWith("Bearer ")){
            return ResponseEntity.status(401).build();
        }
        List<SellersModel> sellersQuery = sellersRepository.findAll();
        return ResponseEntity.ok(sellersQuery);
    }

}
