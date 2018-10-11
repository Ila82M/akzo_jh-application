package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.service.dto.CatTipocoloreDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CatTipocolore.
 */
public interface CatTipocoloreService {

    /**
     * Save a catTipocolore.
     *
     * @param catTipocoloreDTO the entity to save
     * @return the persisted entity
     */
    CatTipocoloreDTO save(CatTipocoloreDTO catTipocoloreDTO);

    /**
     * Get all the catTipocolores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CatTipocoloreDTO> findAll(Pageable pageable);


    /**
     * Get the "id" catTipocolore.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CatTipocoloreDTO> findOne(Long id);

    /**
     * Delete the "id" catTipocolore.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
