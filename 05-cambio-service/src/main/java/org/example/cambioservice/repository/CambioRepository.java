package org.example.cambioservice.repository;

import org.example.cambioservice.model.Cambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CambioRepository extends JpaRepository<Cambio, Long> {

  Cambio findByFromAndTo(String from, String to);
}
