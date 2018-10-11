package com.serijakala.sj4.akzo.akzonobel.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CatBase entity.
 */
public class CatBaseDTO implements Serializable {

    private Long id;

    private String codbase;

    private String descrizione;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodbase() {
        return codbase;
    }

    public void setCodbase(String codbase) {
        this.codbase = codbase;
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

        CatBaseDTO catBaseDTO = (CatBaseDTO) o;
        if (catBaseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catBaseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatBaseDTO{" +
            "id=" + getId() +
            ", codbase='" + getCodbase() + "'" +
            ", descrizione='" + getDescrizione() + "'" +
            "}";
    }
}
