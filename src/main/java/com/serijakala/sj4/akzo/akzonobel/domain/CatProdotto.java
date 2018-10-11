package com.serijakala.sj4.akzo.akzonobel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A CatProdotto.
 */
@Entity
@Table(name = "cat_prodotto")
public class CatProdotto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "sottotipo")
    private String sottotipo;

    @Column(name = "note")
    private String note;

    @Column(name = "scheda_tecnica")
    private String schedaTecnica;

    @Column(name = "scheda_sicurezza")
    private String schedaSicurezza;

    @Column(name = "img")
    private String img;

    @Column(name = "data_update")
    private Instant dataUpdate;

    @Column(name = "pubblicato")
    private String pubblicato;

    @Column(name = "misura")
    private String misura;

    @ManyToOne
    @JsonIgnoreProperties("")
    private CatFamiglia famiglia;

    @ManyToOne
    @JsonIgnoreProperties("")
    private CatTipoprodotto tipoprodotto;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public CatProdotto nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public CatProdotto descrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getSottotipo() {
        return sottotipo;
    }

    public CatProdotto sottotipo(String sottotipo) {
        this.sottotipo = sottotipo;
        return this;
    }

    public void setSottotipo(String sottotipo) {
        this.sottotipo = sottotipo;
    }

    public String getNote() {
        return note;
    }

    public CatProdotto note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSchedaTecnica() {
        return schedaTecnica;
    }

    public CatProdotto schedaTecnica(String schedaTecnica) {
        this.schedaTecnica = schedaTecnica;
        return this;
    }

    public void setSchedaTecnica(String schedaTecnica) {
        this.schedaTecnica = schedaTecnica;
    }

    public String getSchedaSicurezza() {
        return schedaSicurezza;
    }

    public CatProdotto schedaSicurezza(String schedaSicurezza) {
        this.schedaSicurezza = schedaSicurezza;
        return this;
    }

    public void setSchedaSicurezza(String schedaSicurezza) {
        this.schedaSicurezza = schedaSicurezza;
    }

    public String getImg() {
        return img;
    }

    public CatProdotto img(String img) {
        this.img = img;
        return this;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Instant getDataUpdate() {
        return dataUpdate;
    }

    public CatProdotto dataUpdate(Instant dataUpdate) {
        this.dataUpdate = dataUpdate;
        return this;
    }

    public void setDataUpdate(Instant dataUpdate) {
        this.dataUpdate = dataUpdate;
    }

    public String getPubblicato() {
        return pubblicato;
    }

    public CatProdotto pubblicato(String pubblicato) {
        this.pubblicato = pubblicato;
        return this;
    }

    public void setPubblicato(String pubblicato) {
        this.pubblicato = pubblicato;
    }

    public String getMisura() {
        return misura;
    }

    public CatProdotto misura(String misura) {
        this.misura = misura;
        return this;
    }

    public void setMisura(String misura) {
        this.misura = misura;
    }

    public CatFamiglia getFamiglia() {
        return famiglia;
    }

    public CatProdotto famiglia(CatFamiglia catFamiglia) {
        this.famiglia = catFamiglia;
        return this;
    }

    public void setFamiglia(CatFamiglia catFamiglia) {
        this.famiglia = catFamiglia;
    }

    public CatTipoprodotto getTipoprodotto() {
        return tipoprodotto;
    }

    public CatProdotto tipoprodotto(CatTipoprodotto catTipoprodotto) {
        this.tipoprodotto = catTipoprodotto;
        return this;
    }

    public void setTipoprodotto(CatTipoprodotto catTipoprodotto) {
        this.tipoprodotto = catTipoprodotto;
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
        CatProdotto catProdotto = (CatProdotto) o;
        if (catProdotto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catProdotto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatProdotto{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", descrizione='" + getDescrizione() + "'" +
            ", sottotipo='" + getSottotipo() + "'" +
            ", note='" + getNote() + "'" +
            ", schedaTecnica='" + getSchedaTecnica() + "'" +
            ", schedaSicurezza='" + getSchedaSicurezza() + "'" +
            ", img='" + getImg() + "'" +
            ", dataUpdate='" + getDataUpdate() + "'" +
            ", pubblicato='" + getPubblicato() + "'" +
            ", misura='" + getMisura() + "'" +
            "}";
    }
}
