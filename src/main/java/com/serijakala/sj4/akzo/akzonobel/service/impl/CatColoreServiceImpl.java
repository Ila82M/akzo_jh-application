package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.CatColoreService;
import com.serijakala.sj4.akzo.akzonobel.domain.CatColore;
import com.serijakala.sj4.akzo.akzonobel.repository.CatColoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CatColore.
 */
@Service
@Transactional
public class CatColoreServiceImpl implements CatColoreService {

    private final Logger log = LoggerFactory.getLogger(CatColoreServiceImpl.class);

    private final CatColoreRepository catColoreRepository;

    public CatColoreServiceImpl(CatColoreRepository catColoreRepository) {
        this.catColoreRepository = catColoreRepository;
    }

    /**
     * Save a catColore.
     *
     * @param catColore the entity to save
     * @return the persisted entity
     */
    @Override
    public CatColore save(CatColore catColore) {
        log.debug("Request to save CatColore : {}", catColore);
        return catColoreRepository.save(catColore);
    }

    /**
     * Get all the catColores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CatColore> findAll(Pageable pageable) {
        log.debug("Request to get all CatColores");
        return catColoreRepository.findAll(pageable);
    }


    /**
     * Get one catColore by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CatColore> findOne(Long id) {
        log.debug("Request to get CatColore : {}", id);
        return catColoreRepository.findById(id);
    }

    /**
     * Delete the catColore by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CatColore : {}", id);
        catColoreRepository.deleteById(id);
    }
}
