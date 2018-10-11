package com.serijakala.sj4.akzo.akzonobel.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Regione.
 */
@Entity
@Table(name = "regione")
public class Regione implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cod_regione")
    private Long codRegione;

    @Column(name = "nome")
    private String nome;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodRegione() {
        return codRegione;
    }

    public Regione codRegione(Long codRegione) {
        this.codRegione = codRegione;
        return this;
    }

    public void setCodRegione(Long codRegione) {
        this.codRegione = codRegione;
    }

    public String getNome() {
        return nome;
    }

    public Regione nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        Regione regione = (Regione) o;
        if (regione.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), regione.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Regione{" +
            "id=" + getId() +
            ", codRegione=" + getCodRegione() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
