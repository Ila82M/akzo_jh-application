package com.serijakala.sj4.akzo.akzonobel.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CatTipoprodotto entity.
 */
public class CatTipoprodottoDTO implements Serializable {

    private Long id;

    private String descrizione;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CatTipoprodottoDTO catTipoprodottoDTO = (CatTipoprodottoDTO) o;
        if (catTipoprodottoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catTipoprodottoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatTipoprodottoDTO{" +
            "id=" + getId() +
            ", descrizione='" + getDescrizione() + "'" +
            "}";
    }
}
