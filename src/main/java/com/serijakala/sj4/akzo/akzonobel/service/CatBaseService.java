package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.service.dto.CatBaseDTO;

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
     * @param catBaseDTO the entity to save
     * @return the persisted entity
     */
    CatBaseDTO save(CatBaseDTO catBaseDTO);

    /**
     * Get all the catBases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CatBaseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" catBase.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CatBaseDTO> findOne(Long id);

    /**
     * Delete the "id" catBase.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
