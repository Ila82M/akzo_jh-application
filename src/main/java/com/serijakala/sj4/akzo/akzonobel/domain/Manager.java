package com.serijakala.sj4.akzo.akzonobel.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Manager.
 */
@Entity
@Table(name = "manager")
public class Manager implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "iduser")
    private Long iduser;

    @Column(name = "areamanager")
    private String areamanager;

    @Column(name = "agente")
    private String agente;

    @Column(name = "npuntivendita")
    private Long npuntivendita;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIduser() {
        return iduser;
    }

    public Manager iduser(Long iduser) {
        this.iduser = iduser;
        return this;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public String getAreamanager() {
        return areamanager;
    }

    public Manager areamanager(String areamanager) {
        this.areamanager = areamanager;
        return this;
    }

    public void setAreamanager(String areamanager) {
        this.areamanager = areamanager;
    }

    public String getAgente() {
        return agente;
    }

    public Manager agente(String agente) {
        this.agente = agente;
        return this;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public Long getNpuntivendita() {
        return npuntivendita;
    }

    public Manager npuntivendita(Long npuntivendita) {
        this.npuntivendita = npuntivendita;
        return this;
    }

    public void setNpuntivendita(Long npuntivendita) {
        this.npuntivendita = npuntivendita;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Manager manager = (Manager) o;
        if (manager.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), manager.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Manager{" +
            "id=" + getId() +
            ", iduser=" + getIduser() +
            ", areamanager='" + getAreamanager() + "'" +
            ", agente='" + getAgente() + "'" +
            ", npuntivendita=" + getNpuntivendita() +
            "}";
    }
}
