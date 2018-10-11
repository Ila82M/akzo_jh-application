package com.serijakala.sj4.akzo.akzonobel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Provincia.
 */
@Entity
@Table(name = "provincia")
public class Provincia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cod_provincia")
    private Long codProvincia;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sigla")
    private String sigla;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Regione codRegione;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodProvincia() {
        return codProvincia;
    }

    public Provincia codProvincia(Long codProvincia) {
        this.codProvincia = codProvincia;
        return this;
    }

    public void setCodProvincia(Long codProvincia) {
        this.codProvincia = codProvincia;
    }

    public String getNome() {
        return nome;
    }

    public Provincia nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public Provincia sigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Regione getCodRegione() {
        return codRegione;
    }

    public Provincia codRegione(Regione regione) {
        this.codRegione = regione;
        return this;
    }

    public void setCodRegione(Regione regione) {
        this.codRegione = regione;
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
        Provincia provincia = (Provincia) o;
        if (provincia.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), provincia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Provincia{" +
            "id=" + getId() +
            ", codProvincia=" + getCodProvincia() +
            ", nome='" + getNome() + "'" +
            ", sigla='" + getSigla() + "'" +
            "}";
    }
}
