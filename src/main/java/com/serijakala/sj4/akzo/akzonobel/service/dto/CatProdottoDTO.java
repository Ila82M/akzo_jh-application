package com.serijakala.sj4.akzo.akzonobel.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CatProdotto entity.
 */
public class CatProdottoDTO implements Serializable {

    private Long id;

    private String nome;

    private String descrizione;

    private String sottotipo;

    private String note;

    private String schedaTecnica;

    private String schedaSicurezza;

    private String img;

    private Instant dataUpdate;

    private String pubblicato;

    private String misura;

    private Long famigliaId;

    private Long tipoprodottoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getSottotipo() {
        return sottotipo;
    }

    public void setSottotipo(String sottotipo) {
        this.sottotipo = sottotipo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSchedaTecnica() {
        return schedaTecnica;
    }

    public void setSchedaTecnica(String schedaTecnica) {
        this.schedaTecnica = schedaTecnica;
    }

    public String getSchedaSicurezza() {
        return schedaSicurezza;
    }

    public void setSchedaSicurezza(String schedaSicurezza) {
        this.schedaSicurezza = schedaSicurezza;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Instant getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(Instant dataUpdate) {
        this.dataUpdate = dataUpdate;
    }

    public String getPubblicato() {
        return pubblicato;
    }

    public void setPubblicato(String pubblicato) {
        this.pubblicato = pubblicato;
    }

    public String getMisura() {
        return misura;
    }

    public void setMisura(String misura) {
        this.misura = misura;
    }

    public Long getFamigliaId() {
        return famigliaId;
    }

    public void setFamigliaId(Long catFamigliaId) {
        this.famigliaId = catFamigliaId;
    }

    public Long getTipoprodottoId() {
        return tipoprodottoId;
    }

    public void setTipoprodottoId(Long catTipoprodottoId) {
        this.tipoprodottoId = catTipoprodottoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CatProdottoDTO catProdottoDTO = (CatProdottoDTO) o;
        if (catProdottoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catProdottoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatProdottoDTO{" +
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
            ", famiglia=" + getFamigliaId() +
            ", tipoprodotto=" + getTipoprodottoId() +
            "}";
    }
}
