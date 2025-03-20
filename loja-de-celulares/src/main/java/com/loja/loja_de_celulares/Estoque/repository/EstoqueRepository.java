package com.loja.loja_de_celulares.Estoque.repository;

import com.loja.loja_de_celulares.Estoque.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*Herda uma série de métodos prontos que facilitam a interação com o banco de dados, como operações de CRUD (Create, Read, Update, Delete).
A regra definida neste negócio é Usá-la para funções personalizadas, como buscas, por exemplo, e implementá-las na controller */

public interface EstoqueRepository extends JpaRepository<Estoque, String> {
    List<Estoque> findAllByAtivoTrue();
}
