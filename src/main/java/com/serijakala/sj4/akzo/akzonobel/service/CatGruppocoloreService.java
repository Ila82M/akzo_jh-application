package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.domain.CatGruppocolore;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CatGruppocolore.
 */
public interface CatGruppocoloreService {

    /**
     * Save a catGruppocolore.
     *
     * @param catGruppocolore the entity to save
     * @return the persisted entity
     */
    CatGruppocolore save(CatGruppocolore catGruppocolore);

    /**
     * Get all the catGruppocolores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CatGruppocolore> findAll(Pageable pageable);


    /**
     * Get the "id" catGruppocolore.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CatGruppocolore> findOne(Long id);

    /**
     * Delete the "id" catGruppocolore.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
