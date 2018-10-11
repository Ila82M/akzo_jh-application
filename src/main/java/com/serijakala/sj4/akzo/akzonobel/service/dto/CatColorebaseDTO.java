package com.serijakala.sj4.akzo.akzonobel.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CatColorebase entity.
 */
public class CatColorebaseDTO implements Serializable {

    private Long id;

    private Long resa;

    private Long baseId;

    private Long gruppocoloreId;

    private Long tipocoloreId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResa() {
        return resa;
    }

    public void setResa(Long resa) {
        this.resa = resa;
    }

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long catBaseId) {
        this.baseId = catBaseId;
    }

    public Long getGruppocoloreId() {
        return gruppocoloreId;
    }

    public void setGruppocoloreId(Long catGruppocoloreId) {
        this.gruppocoloreId = catGruppocoloreId;
    }

    public Long getTipocoloreId() {
        return tipocoloreId;
    }

    public void setTipocoloreId(Long catTipocoloreId) {
        this.tipocoloreId = catTipocoloreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CatColorebaseDTO catColorebaseDTO = (CatColorebaseDTO) o;
        if (catColorebaseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catColorebaseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatColorebaseDTO{" +
            "id=" + getId() +
            ", resa=" + getResa() +
            ", base=" + getBaseId() +
            ", gruppocolore=" + getGruppocoloreId() +
            ", tipocolore=" + getTipocoloreId() +
            "}";
    }
}
