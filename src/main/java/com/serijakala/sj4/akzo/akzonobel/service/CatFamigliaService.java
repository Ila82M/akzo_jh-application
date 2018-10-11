package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.service.dto.CatFamigliaDTO;

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
     * @param catFamigliaDTO the entity to save
     * @return the persisted entity
     */
    CatFamigliaDTO save(CatFamigliaDTO catFamigliaDTO);

    /**
     * Get all the catFamiglias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CatFamigliaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" catFamiglia.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CatFamigliaDTO> findOne(Long id);

    /**
     * Delete the "id" catFamiglia.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
