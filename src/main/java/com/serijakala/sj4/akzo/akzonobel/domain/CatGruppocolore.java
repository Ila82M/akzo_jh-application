package com.serijakala.sj4.akzo.akzonobel.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CatGruppocolore.
 */
@Entity
@Table(name = "cat_gruppocolore")
public class CatGruppocolore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "descrizione")
    private String descrizione;

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

    public CatGruppocolore descrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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
        CatGruppocolore catGruppocolore = (CatGruppocolore) o;
        if (catGruppocolore.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catGruppocolore.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatGruppocolore{" +
            "id=" + getId() +
            ", descrizione='" + getDescrizione() + "'" +
            "}";
    }
}
