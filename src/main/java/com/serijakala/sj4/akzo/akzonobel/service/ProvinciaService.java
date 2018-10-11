package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.domain.Provincia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Provincia.
 */
public interface ProvinciaService {

    /**
     * Save a provincia.
     *
     * @param provincia the entity to save
     * @return the persisted entity
     */
    Provincia save(Provincia provincia);

    /**
     * Get all the provincias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Provincia> findAll(Pageable pageable);


    /**
     * Get the "id" provincia.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Provincia> findOne(Long id);

    /**
     * Delete the "id" provincia.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
