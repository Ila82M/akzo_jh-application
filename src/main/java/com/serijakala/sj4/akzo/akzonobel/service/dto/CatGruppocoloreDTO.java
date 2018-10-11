package com.serijakala.sj4.akzo.akzonobel.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CatGruppocolore entity.
 */
public class CatGruppocoloreDTO implements Serializable {

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

        CatGruppocoloreDTO catGruppocoloreDTO = (CatGruppocoloreDTO) o;
        if (catGruppocoloreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catGruppocoloreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatGruppocoloreDTO{" +
            "id=" + getId() +
            ", descrizione='" + getDescrizione() + "'" +
            "}";
    }
}
