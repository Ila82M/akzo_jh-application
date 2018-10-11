package com.serijakala.sj4.akzo.akzonobel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CatColore.
 */
@Entity
@Table(name = "cat_colore")
public class CatColore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "descrizione")
    private String descrizione;

    @ManyToOne
    @JsonIgnoreProperties("")
    private CatGruppocolore gruppocolore;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public CatColore descrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public CatGruppocolore getGruppocolore() {
        return gruppocolore;
    }

    public CatColore gruppocolore(CatGruppocolore catGruppocolore) {
        this.gruppocolore = catGruppocolore;
        return this;
    }

    public void setGruppocolore(CatGruppocolore catGruppocolore) {
        this.gruppocolore = catGruppocolore;
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
        CatColore catColore = (CatColore) o;
        if (catColore.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catColore.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatColore{" +
            "id=" + getId() +
            ", descrizione='" + getDescrizione() + "'" +
            "}";
    }
}
