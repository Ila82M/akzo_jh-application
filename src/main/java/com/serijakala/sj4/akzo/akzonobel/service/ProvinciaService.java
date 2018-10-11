package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.service.dto.ProvinciaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Provincia.
 */
public interface ProvinciaService {

    /**
     * Save a provincia.
     *
     * @param provinciaDTO the entity to save
     * @return the persisted entity
     */
    ProvinciaDTO save(ProvinciaDTO provinciaDTO);

    /**
     * Get all the provincias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProvinciaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" provincia.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProvinciaDTO> findOne(Long id);

    /**
     * Delete the "id" provincia.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
