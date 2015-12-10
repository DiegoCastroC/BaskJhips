package com.diego.castro.repository;

import com.diego.castro.domain.Especificaciones;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Especificaciones entity.
 */
public interface EspecificacionesRepository extends JpaRepository<Especificaciones,Long> {

}
