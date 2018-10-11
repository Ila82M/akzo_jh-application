package com.serijakala.sj4.akzo.akzonobel.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CatFamiglia entity.
 */
public class CatFamigliaDTO implements Serializable {

    private Long id;

    private String marchio;

    private String attivo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarchio() {
        return marchio;
    }

    public void setMarchio(String marchio) {
        this.marchio = marchio;
    }

    public String getAttivo() {
        return attivo;
    }

    public void setAttivo(String attivo) {
        this.attivo = attivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CatFamigliaDTO catFamigliaDTO = (CatFamigliaDTO) o;
        if (catFamigliaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catFamigliaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatFamigliaDTO{" +
            "id=" + getId() +
            ", marchio='" + getMarchio() + "'" +
            ", attivo='" + getAttivo() + "'" +
            "}";
    }
}
