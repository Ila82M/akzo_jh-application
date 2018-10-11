package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.CatColorebaseService;
import com.serijakala.sj4.akzo.akzonobel.domain.CatColorebase;
import com.serijakala.sj4.akzo.akzonobel.repository.CatColorebaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CatColorebase.
 */
@Service
@Transactional
public class CatColorebaseServiceImpl implements CatColorebaseService {

    private final Logger log = LoggerFactory.getLogger(CatColorebaseServiceImpl.class);

    private final CatColorebaseRepository catColorebaseRepository;

    public CatColorebaseServiceImpl(CatColorebaseRepository catColorebaseRepository) {
        this.catColorebaseRepository = catColorebaseRepository;
    }

    /**
     * Save a catColorebase.
     *
     * @param catColorebase the entity to save
     * @return the persisted entity
     */
    @Override
    public CatColorebase save(CatColorebase catColorebase) {
        log.debug("Request to save CatColorebase : {}", catColorebase);
        return catColorebaseRepository.save(catColorebase);
    }

    /**
     * Get all the catColorebases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CatColorebase> findAll(Pageable pageable) {
        log.debug("Request to get all CatColorebases");
        return catColorebaseRepository.findAll(pageable);
    }


    /**
     * Get one catColorebase by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CatColorebase> findOne(Long id) {
        log.debug("Request to get CatColorebase : {}", id);
        return catColorebaseRepository.findById(id);
    }

    /**
     * Delete the catColorebase by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CatColorebase : {}", id);
        catColorebaseRepository.deleteById(id);
    }
}
