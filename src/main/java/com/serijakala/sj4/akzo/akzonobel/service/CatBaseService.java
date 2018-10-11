package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.domain.CatBase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CatBase.
 */
public interface CatBaseService {

    /**
     * Save a catBase.
     *
     * @param catBase the entity to save
     * @return the persisted entity
     */
    CatBase save(CatBase catBase);

    /**
     * Get all the catBases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CatBase> findAll(Pageable pageable);


    /**
     * Get the "id" catBase.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CatBase> findOne(Long id);

    /**
     * Delete the "id" catBase.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
