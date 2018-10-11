package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.service.dto.ManagerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Manager.
 */
public interface ManagerService {

    /**
     * Save a manager.
     *
     * @param managerDTO the entity to save
     * @return the persisted entity
     */
    ManagerDTO save(ManagerDTO managerDTO);

    /**
     * Get all the managers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ManagerDTO> findAll(Pageable pageable);


    /**
     * Get the "id" manager.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ManagerDTO> findOne(Long id);

    /**
     * Delete the "id" manager.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
