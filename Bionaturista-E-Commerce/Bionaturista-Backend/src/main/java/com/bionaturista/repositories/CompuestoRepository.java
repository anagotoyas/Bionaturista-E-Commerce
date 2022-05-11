package com.bionaturista.repositories;

import com.bionaturista.model.Compuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompuestoRepository extends JpaRepository<Compuesto, Integer> {
}
