package com.loja.loja_de_celulares.Estoque.repository;

import com.loja.loja_de_celulares.Estoque.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, String> {
}
