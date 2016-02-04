package com.diego.castro.repository;

import com.diego.castro.domain.Equipo;
import com.diego.castro.domain.Jugador;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Equipo entity.
 */
public interface EquipoRepository extends JpaRepository<Equipo,Long> {

    @Query("select distinct equipo from Equipo equipo left join fetch equipo.ligaequipos")
    List<Equipo> findAllWithEagerRelationships();

    @Query("select equipo from Equipo equipo left join fetch equipo.ligaequipos where equipo.id =:id")
    Equipo findOneWithEagerRelationships(@Param("id") Long id);

    /*@Query("select jugador from Jugador j where j.equipo.id =:id")
    List<Jugador> findbyEquipo(@Param("id") Long id);*/

}
