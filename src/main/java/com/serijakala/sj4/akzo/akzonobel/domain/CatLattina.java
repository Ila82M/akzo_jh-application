package com.serijakala.sj4.akzo.akzonobel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A CatLattina.
 */
@Entity
@Table(name = "cat_lattina")
public class CatLattina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "litraggio")
    private Long litraggio;

    @Column(name = "codice")
    private String codice;

    @Column(name = "prezzo")
    private Long prezzo;

    @Column(name = "data_update")
    private Instant dataUpdate;

    @ManyToOne
    @JsonIgnoreProperties("")
    private CatColorebase colorebase;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLitraggio() {
        return litraggio;
    }

    public CatLattina litraggio(Long litraggio) {
        this.litraggio = litraggio;
        return this;
    }

    public void setLitraggio(Long litraggio) {
        this.litraggio = litraggio;
    }

    public String getCodice() {
        return codice;
    }

    public CatLattina codice(String codice) {
        this.codice = codice;
        return this;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Long getPrezzo() {
        return prezzo;
    }

    public CatLattina prezzo(Long prezzo) {
        this.prezzo = prezzo;
        return this;
    }

    public void setPrezzo(Long prezzo) {
        this.prezzo = prezzo;
    }

    public Instant getDataUpdate() {
        return dataUpdate;
    }

    public CatLattina dataUpdate(Instant dataUpdate) {
        this.dataUpdate = dataUpdate;
        return this;
    }

    public void setDataUpdate(Instant dataUpdate) {
        this.dataUpdate = dataUpdate;
    }

    public CatColorebase getColorebase() {
        return colorebase;
    }

    public CatLattina colorebase(CatColorebase catColorebase) {
        this.colorebase = catColorebase;
        return this;
    }

    public void setColorebase(CatColorebase catColorebase) {
        this.colorebase = catColorebase;
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
        CatLattina catLattina = (CatLattina) o;
        if (catLattina.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catLattina.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatLattina{" +
            "id=" + getId() +
            ", litraggio=" + getLitraggio() +
            ", codice='" + getCodice() + "'" +
            ", prezzo=" + getPrezzo() +
            ", dataUpdate='" + getDataUpdate() + "'" +
            "}";
    }
}
