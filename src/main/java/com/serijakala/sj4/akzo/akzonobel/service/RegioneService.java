package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.service.dto.RegioneDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Regione.
 */
public interface RegioneService {

    /**
     * Save a regione.
     *
     * @param regioneDTO the entity to save
     * @return the persisted entity
     */
    RegioneDTO save(RegioneDTO regioneDTO);

    /**
     * Get all the regiones.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RegioneDTO> findAll(Pageable pageable);


    /**
     * Get the "id" regione.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RegioneDTO> findOne(Long id);

    /**
     * Delete the "id" regione.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
