package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.domain.CatColore;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CatColore.
 */
public interface CatColoreService {

    /**
     * Save a catColore.
     *
     * @param catColore the entity to save
     * @return the persisted entity
     */
    CatColore save(CatColore catColore);

    /**
     * Get all the catColores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CatColore> findAll(Pageable pageable);


    /**
     * Get the "id" catColore.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CatColore> findOne(Long id);

    /**
     * Delete the "id" catColore.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
