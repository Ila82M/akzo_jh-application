package com.serijakala.sj4.akzo.akzonobel.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Provincia entity.
 */
public class ProvinciaDTO implements Serializable {

    private Long id;

    private Long codProvincia;

    private String nome;

    private String sigla;

    private Long codRegioneId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(Long codProvincia) {
        this.codProvincia = codProvincia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Long getCodRegioneId() {
        return codRegioneId;
    }

    public void setCodRegioneId(Long regioneId) {
        this.codRegioneId = regioneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProvinciaDTO provinciaDTO = (ProvinciaDTO) o;
        if (provinciaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), provinciaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProvinciaDTO{" +
            "id=" + getId() +
            ", codProvincia=" + getCodProvincia() +
            ", nome='" + getNome() + "'" +
            ", sigla='" + getSigla() + "'" +
            ", codRegione=" + getCodRegioneId() +
            "}";
    }
}
