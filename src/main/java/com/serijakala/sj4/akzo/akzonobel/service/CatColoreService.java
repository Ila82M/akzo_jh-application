package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.service.dto.CatColoreDTO;

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
     * @param catColoreDTO the entity to save
     * @return the persisted entity
     */
    CatColoreDTO save(CatColoreDTO catColoreDTO);

    /**
     * Get all the catColores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CatColoreDTO> findAll(Pageable pageable);


    /**
     * Get the "id" catColore.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CatColoreDTO> findOne(Long id);

    /**
     * Delete the "id" catColore.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
