package br.com.phone.store.sales.repository;

import br.com.phone.store.sales.model.SalesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository <SalesModel, Integer>{}
