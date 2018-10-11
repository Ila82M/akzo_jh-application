package com.serijakala.sj4.akzo.akzonobel.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CatTipocolore.
 */
@Entity
@Table(name = "cat_tipocolore")
public class CatTipocolore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_prodotto")
    private Integer idProdotto;

    @Column(name = "descrizione")
    private String descrizione;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdProdotto() {
        return idProdotto;
    }

    public CatTipocolore idProdotto(Integer idProdotto) {
        this.idProdotto = idProdotto;
        return this;
    }

    public void setIdProdotto(Integer idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public CatTipocolore descrizione(String descrizione) {
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
        CatTipocolore catTipocolore = (CatTipocolore) o;
        if (catTipocolore.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catTipocolore.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatTipocolore{" +
            "id=" + getId() +
            ", idProdotto=" + getIdProdotto() +
            ", descrizione='" + getDescrizione() + "'" +
            "}";
    }
}
