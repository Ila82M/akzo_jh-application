package com.serijakala.sj4.akzo.akzonobel.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CatLattina entity.
 */
public class CatLattinaDTO implements Serializable {

    private Long id;

    private Long litraggio;

    private String codice;

    private Long prezzo;

    private Instant dataUpdate;

    private Long colorebaseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLitraggio() {
        return litraggio;
    }

    public void setLitraggio(Long litraggio) {
        this.litraggio = litraggio;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Long getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Long prezzo) {
        this.prezzo = prezzo;
    }

    public Instant getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(Instant dataUpdate) {
        this.dataUpdate = dataUpdate;
    }

    public Long getColorebaseId() {
        return colorebaseId;
    }

    public void setColorebaseId(Long catColorebaseId) {
        this.colorebaseId = catColorebaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CatLattinaDTO catLattinaDTO = (CatLattinaDTO) o;
        if (catLattinaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catLattinaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatLattinaDTO{" +
            "id=" + getId() +
            ", litraggio=" + getLitraggio() +
            ", codice='" + getCodice() + "'" +
            ", prezzo=" + getPrezzo() +
            ", dataUpdate='" + getDataUpdate() + "'" +
            ", colorebase=" + getColorebaseId() +
            "}";
    }
}
