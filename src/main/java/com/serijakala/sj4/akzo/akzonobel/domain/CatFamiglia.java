package com.serijakala.sj4.akzo.akzonobel.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CatFamiglia.
 */
@Entity
@Table(name = "cat_famiglia")
public class CatFamiglia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "marchio")
    private String marchio;

    @Column(name = "attivo")
    private String attivo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarchio() {
        return marchio;
    }

    public CatFamiglia marchio(String marchio) {
        this.marchio = marchio;
        return this;
    }

    public void setMarchio(String marchio) {
        this.marchio = marchio;
    }

    public String getAttivo() {
        return attivo;
    }

    public CatFamiglia attivo(String attivo) {
        this.attivo = attivo;
        return this;
    }

    public void setAttivo(String attivo) {
        this.attivo = attivo;
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
        CatFamiglia catFamiglia = (CatFamiglia) o;
        if (catFamiglia.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catFamiglia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatFamiglia{" +
            "id=" + getId() +
            ", marchio='" + getMarchio() + "'" +
            ", attivo='" + getAttivo() + "'" +
            "}";
    }
}
