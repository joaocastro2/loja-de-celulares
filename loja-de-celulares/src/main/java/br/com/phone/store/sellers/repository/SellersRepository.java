package br.com.phone.store.sellers.repository;

import br.com.phone.store.sellers.model.SellersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellersRepository extends JpaRepository<SellersModel, Integer> {}

