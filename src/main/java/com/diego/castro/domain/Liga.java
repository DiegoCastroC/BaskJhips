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
 * A Liga.
 */
@Entity
@Table(name = "LIGA")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Liga implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "ligatemp")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Temporada> ligatemps = new HashSet<>();

    @OneToMany(mappedBy = "ligarb")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Arbitro> ligarbs = new HashSet<>();

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

    public Set<Temporada> getLigatemps() {
        return ligatemps;
    }

    public void setLigatemps(Set<Temporada> temporadas) {
        this.ligatemps = temporadas;
    }

    public Set<Arbitro> getLigarbs() {
        return ligarbs;
    }

    public void setLigarbs(Set<Arbitro> arbitros) {
        this.ligarbs = arbitros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Liga liga = (Liga) o;

        if ( ! Objects.equals(id, liga.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Liga{" +
                "id=" + id +
                ", nombre='" + nombre + "'" +
                '}';
    }
}
