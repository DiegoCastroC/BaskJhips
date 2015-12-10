package com.diego.castro.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.diego.castro.domain.util.CustomLocalDateSerializer;
import com.diego.castro.domain.util.ISO8601LocalDateDeserializer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A Equipo.
 */
@Entity
@Table(name = "EQUIPO")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Equipo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @Column(name = "nombre")
    private String nombre;
    
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonDeserialize(using = ISO8601LocalDateDeserializer.class)
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @OneToOne
    private Estadio estadio;

    @ManyToMany(mappedBy = "equipos")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Socio> socios = new HashSet<>();

    @OneToOne
    private Entrenador entrenador;

    @OneToMany(mappedBy = "equipo")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Jugador> jugadors = new HashSet<>();

    @OneToMany(mappedBy = "equipoLocal")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Partido> equipoLocals = new HashSet<>();

    @OneToMany(mappedBy = "equipoVisitante")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Partido> equipoVisitantes = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "EQUIPO_LIGAEQUIPO",
               joinColumns = @JoinColumn(name="equipos_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="ligaequipos_id", referencedColumnName="ID"))
    private Set<Temporada> ligaequipos = new HashSet<>();

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

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public Set<Socio> getSocios() {
        return socios;
    }

    public void setSocios(Set<Socio> socios) {
        this.socios = socios;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Set<Jugador> getJugadors() {
        return jugadors;
    }

    public void setJugadors(Set<Jugador> jugadors) {
        this.jugadors = jugadors;
    }

    public Set<Partido> getEquipoLocals() {
        return equipoLocals;
    }

    public void setEquipoLocals(Set<Partido> partidos) {
        this.equipoLocals = partidos;
    }

    public Set<Partido> getEquipoVisitantes() {
        return equipoVisitantes;
    }

    public void setEquipoVisitantes(Set<Partido> partidos) {
        this.equipoVisitantes = partidos;
    }

    public Set<Temporada> getLigaequipos() {
        return ligaequipos;
    }

    public void setLigaequipos(Set<Temporada> temporadas) {
        this.ligaequipos = temporadas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Equipo equipo = (Equipo) o;

        if ( ! Objects.equals(id, equipo.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + "'" +
                ", fechaCreacion='" + fechaCreacion + "'" +
                '}';
    }
}
