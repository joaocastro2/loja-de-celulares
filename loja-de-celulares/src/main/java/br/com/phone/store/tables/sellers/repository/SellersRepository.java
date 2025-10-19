package br.com.phone.store.tables.sellers.repository;

import br.com.phone.store.tables.sellers.model.SellersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellersRepository extends JpaRepository<SellersModel, Integer> {}

