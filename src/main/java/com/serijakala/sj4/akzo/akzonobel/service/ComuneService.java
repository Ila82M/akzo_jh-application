package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.service.dto.ComuneDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Comune.
 */
public interface ComuneService {

    /**
     * Save a comune.
     *
     * @param comuneDTO the entity to save
     * @return the persisted entity
     */
    ComuneDTO save(ComuneDTO comuneDTO);

    /**
     * Get all the comunes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ComuneDTO> findAll(Pageable pageable);


    /**
     * Get the "id" comune.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ComuneDTO> findOne(Long id);

    /**
     * Delete the "id" comune.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
