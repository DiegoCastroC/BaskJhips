package com.diego.castro.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A Temporada.
 */
@Entity
@Table(name = "TEMPORADA")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Temporada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    private Liga ligatemp;

    @ManyToMany(mappedBy = "ligaequipos")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Equipo> ligaequipos = new HashSet<>();

    @OneToMany(mappedBy = "temporada")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Partido> ligapartidos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Liga getLigatemp() {
        return ligatemp;
    }

    public void setLigatemp(Liga liga) {
        this.ligatemp = liga;
    }

    public Set<Equipo> getLigaequipos() {
        return ligaequipos;
    }

    public void setLigaequipos(Set<Equipo> equipos) {
        this.ligaequipos = equipos;
    }

    public Set<Partido> getLigapartidos() {
        return ligapartidos;
    }

    public void setLigapartidos(Set<Partido> partidos) {
        this.ligapartidos = partidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Temporada temporada = (Temporada) o;

        if ( ! Objects.equals(id, temporada.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Temporada{" +
                "id=" + id +
                ", nombre='" + nombre + "'" +
                '}';
    }
}
