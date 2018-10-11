package com.serijakala.sj4.akzo.akzonobel.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CatTipocolore entity.
 */
public class CatTipocoloreDTO implements Serializable {

    private Long id;

    private Integer idProdotto;

    private String descrizione;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(Integer idProdotto) {
        this.idProdotto = idProdotto;
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

        CatTipocoloreDTO catTipocoloreDTO = (CatTipocoloreDTO) o;
        if (catTipocoloreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catTipocoloreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatTipocoloreDTO{" +
            "id=" + getId() +
            ", idProdotto=" + getIdProdotto() +
            ", descrizione='" + getDescrizione() + "'" +
            "}";
    }
}
