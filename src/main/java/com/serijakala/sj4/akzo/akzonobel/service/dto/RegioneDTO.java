package com.serijakala.sj4.akzo.akzonobel.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Regione entity.
 */
public class RegioneDTO implements Serializable {

    private Long id;

    private Long codRegione;

    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodRegione() {
        return codRegione;
    }

    public void setCodRegione(Long codRegione) {
        this.codRegione = codRegione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RegioneDTO regioneDTO = (RegioneDTO) o;
        if (regioneDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), regioneDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RegioneDTO{" +
            "id=" + getId() +
            ", codRegione=" + getCodRegione() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
