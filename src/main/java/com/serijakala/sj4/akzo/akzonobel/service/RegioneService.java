package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.domain.Regione;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Regione.
 */
public interface RegioneService {

    /**
     * Save a regione.
     *
     * @param regione the entity to save
     * @return the persisted entity
     */
    Regione save(Regione regione);

    /**
     * Get all the regiones.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Regione> findAll(Pageable pageable);


    /**
     * Get the "id" regione.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Regione> findOne(Long id);

    /**
     * Delete the "id" regione.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
