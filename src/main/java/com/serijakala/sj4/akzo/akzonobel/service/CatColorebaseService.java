package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.domain.CatColorebase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CatColorebase.
 */
public interface CatColorebaseService {

    /**
     * Save a catColorebase.
     *
     * @param catColorebase the entity to save
     * @return the persisted entity
     */
    CatColorebase save(CatColorebase catColorebase);

    /**
     * Get all the catColorebases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CatColorebase> findAll(Pageable pageable);


    /**
     * Get the "id" catColorebase.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CatColorebase> findOne(Long id);

    /**
     * Delete the "id" catColorebase.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
