package com.serijakala.sj4.akzo.akzonobel.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Manager entity.
 */
public class ManagerDTO implements Serializable {

    private Long id;

    private Long iduser;

    private String areamanager;

    private String agente;

    private Long npuntivendita;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public String getAreamanager() {
        return areamanager;
    }

    public void setAreamanager(String areamanager) {
        this.areamanager = areamanager;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public Long getNpuntivendita() {
        return npuntivendita;
    }

    public void setNpuntivendita(Long npuntivendita) {
        this.npuntivendita = npuntivendita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ManagerDTO managerDTO = (ManagerDTO) o;
        if (managerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), managerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ManagerDTO{" +
            "id=" + getId() +
            ", iduser=" + getIduser() +
            ", areamanager='" + getAreamanager() + "'" +
            ", agente='" + getAgente() + "'" +
            ", npuntivendita=" + getNpuntivendita() +
            "}";
    }
}
