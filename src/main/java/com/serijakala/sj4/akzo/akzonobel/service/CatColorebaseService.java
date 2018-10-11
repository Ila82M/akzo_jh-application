package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.service.dto.CatColorebaseDTO;

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
     * @param catColorebaseDTO the entity to save
     * @return the persisted entity
     */
    CatColorebaseDTO save(CatColorebaseDTO catColorebaseDTO);

    /**
     * Get all the catColorebases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CatColorebaseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" catColorebase.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CatColorebaseDTO> findOne(Long id);

    /**
     * Delete the "id" catColorebase.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
