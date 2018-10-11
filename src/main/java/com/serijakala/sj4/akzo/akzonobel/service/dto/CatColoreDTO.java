package com.serijakala.sj4.akzo.akzonobel.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CatColore entity.
 */
public class CatColoreDTO implements Serializable {

    private Long id;

    private String descrizione;

    private Long gruppocoloreId;

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

    public Long getGruppocoloreId() {
        return gruppocoloreId;
    }

    public void setGruppocoloreId(Long catGruppocoloreId) {
        this.gruppocoloreId = catGruppocoloreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CatColoreDTO catColoreDTO = (CatColoreDTO) o;
        if (catColoreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catColoreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatColoreDTO{" +
            "id=" + getId() +
            ", descrizione='" + getDescrizione() + "'" +
            ", gruppocolore=" + getGruppocoloreId() +
            "}";
    }
}
