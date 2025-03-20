package com.loja.loja_de_celulares.Estoque.controller;


import com.loja.loja_de_celulares.Estoque.repository.EstoqueRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {


    private EstoqueRepository estoqueRepository;

    public ResponseEntity consultaEStoque(){
        var consultaEstoque = estoqueRepository.findAllByAtivoTrue();
        return ResponseEntity.ok(consultaEstoque);
    }

}
