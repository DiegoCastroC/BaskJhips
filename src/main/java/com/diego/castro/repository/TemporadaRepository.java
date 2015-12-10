package com.diego.castro.repository;

import com.diego.castro.domain.Temporada;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Temporada entity.
 */
public interface TemporadaRepository extends JpaRepository<Temporada,Long> {

}
