package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.domain.CatLattina;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CatLattina.
 */
public interface CatLattinaService {

    /**
     * Save a catLattina.
     *
     * @param catLattina the entity to save
     * @return the persisted entity
     */
    CatLattina save(CatLattina catLattina);

    /**
     * Get all the catLattinas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CatLattina> findAll(Pageable pageable);


    /**
     * Get the "id" catLattina.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CatLattina> findOne(Long id);

    /**
     * Delete the "id" catLattina.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
