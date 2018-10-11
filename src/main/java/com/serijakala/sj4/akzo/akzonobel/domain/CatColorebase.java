package com.serijakala.sj4.akzo.akzonobel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CatColorebase.
 */
@Entity
@Table(name = "cat_colorebase")
public class CatColorebase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "resa")
    private Long resa;

    @ManyToOne
    @JsonIgnoreProperties("")
    private CatBase base;

    @ManyToOne
    @JsonIgnoreProperties("")
    private CatGruppocolore gruppocolore;

    @ManyToOne
    @JsonIgnoreProperties("")
    private CatTipocolore tipocolore;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResa() {
        return resa;
    }

    public CatColorebase resa(Long resa) {
        this.resa = resa;
        return this;
    }

    public void setResa(Long resa) {
        this.resa = resa;
    }

    public CatBase getBase() {
        return base;
    }

    public CatColorebase base(CatBase catBase) {
        this.base = catBase;
        return this;
    }

    public void setBase(CatBase catBase) {
        this.base = catBase;
    }

    public CatGruppocolore getGruppocolore() {
        return gruppocolore;
    }

    public CatColorebase gruppocolore(CatGruppocolore catGruppocolore) {
        this.gruppocolore = catGruppocolore;
        return this;
    }

    public void setGruppocolore(CatGruppocolore catGruppocolore) {
        this.gruppocolore = catGruppocolore;
    }

    public CatTipocolore getTipocolore() {
        return tipocolore;
    }

    public CatColorebase tipocolore(CatTipocolore catTipocolore) {
        this.tipocolore = catTipocolore;
        return this;
    }

    public void setTipocolore(CatTipocolore catTipocolore) {
        this.tipocolore = catTipocolore;
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
        CatColorebase catColorebase = (CatColorebase) o;
        if (catColorebase.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catColorebase.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatColorebase{" +
            "id=" + getId() +
            ", resa=" + getResa() +
            "}";
    }
}
