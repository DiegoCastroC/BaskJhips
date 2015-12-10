package com.diego.castro.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A Especificaciones.
 */
@Entity
@Table(name = "ESPECIFICACIONES")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Especificaciones implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    

    @Min(value = 0)        
    @Column(name = "rebotes")
    private Integer rebotes;

    @Min(value = 0)        
    @Column(name = "pases")
    private Integer pases;

    @Min(value = 0)        
    @Column(name = "puntos")
    private Integer puntos;

    @Min(value = 0)        
    @Column(name = "faltas")
    private Integer faltas;

    @ManyToOne
    private Jugador jugadorespec;

    @ManyToOne
    private Partido partidoespec;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRebotes() {
        return rebotes;
    }

    public void setRebotes(Integer rebotes) {
        this.rebotes = rebotes;
    }

    public Integer getPases() {
        return pases;
    }

    public void setPases(Integer pases) {
        this.pases = pases;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Integer getFaltas() {
        return faltas;
    }

    public void setFaltas(Integer faltas) {
        this.faltas = faltas;
    }

    public Jugador getJugadorespec() {
        return jugadorespec;
    }

    public void setJugadorespec(Jugador jugador) {
        this.jugadorespec = jugador;
    }

    public Partido getPartidoespec() {
        return partidoespec;
    }

    public void setPartidoespec(Partido partido) {
        this.partidoespec = partido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Especificaciones especificaciones = (Especificaciones) o;

        if ( ! Objects.equals(id, especificaciones.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Especificaciones{" +
                "id=" + id +
                ", rebotes='" + rebotes + "'" +
                ", pases='" + pases + "'" +
                ", puntos='" + puntos + "'" +
                ", faltas='" + faltas + "'" +
                '}';
    }
}
