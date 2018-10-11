package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.domain.CatFamiglia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CatFamiglia.
 */
public interface CatFamigliaService {

    /**
     * Save a catFamiglia.
     *
     * @param catFamiglia the entity to save
     * @return the persisted entity
     */
    CatFamiglia save(CatFamiglia catFamiglia);

    /**
     * Get all the catFamiglias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CatFamiglia> findAll(Pageable pageable);


    /**
     * Get the "id" catFamiglia.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CatFamiglia> findOne(Long id);

    /**
     * Delete the "id" catFamiglia.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
