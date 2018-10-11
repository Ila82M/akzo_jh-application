package com.serijakala.sj4.akzo.akzonobel.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Comune entity.
 */
public class ComuneDTO implements Serializable {

    private Long id;

    private Long cod;

    private String nome;

    private Long codProvinciaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCod() {
        return cod;
    }

    public void setCod(Long cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCodProvinciaId() {
        return codProvinciaId;
    }

    public void setCodProvinciaId(Long provinciaId) {
        this.codProvinciaId = provinciaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComuneDTO comuneDTO = (ComuneDTO) o;
        if (comuneDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comuneDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComuneDTO{" +
            "id=" + getId() +
            ", cod=" + getCod() +
            ", nome='" + getNome() + "'" +
            ", codProvincia=" + getCodProvinciaId() +
            "}";
    }
}
