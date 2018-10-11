package com.serijakala.sj4.akzo.akzonobel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Comune.
 */
@Entity
@Table(name = "comune")
public class Comune implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cod")
    private Long cod;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Provincia codProvincia;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCod() {
        return cod;
    }

    public Comune cod(Long cod) {
        this.cod = cod;
        return this;
    }

    public void setCod(Long cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public Comune nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Provincia getCodProvincia() {
        return codProvincia;
    }

    public Comune codProvincia(Provincia provincia) {
        this.codProvincia = provincia;
        return this;
    }

    public void setCodProvincia(Provincia provincia) {
        this.codProvincia = provincia;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comune comune = (Comune) o;
        if (comune.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comune.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Comune{" +
            "id=" + getId() +
            ", cod=" + getCod() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
