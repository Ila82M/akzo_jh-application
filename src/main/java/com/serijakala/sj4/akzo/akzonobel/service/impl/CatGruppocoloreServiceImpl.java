package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.CatGruppocoloreService;
import com.serijakala.sj4.akzo.akzonobel.domain.CatGruppocolore;
import com.serijakala.sj4.akzo.akzonobel.repository.CatGruppocoloreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CatGruppocolore.
 */
@Service
@Transactional
public class CatGruppocoloreServiceImpl implements CatGruppocoloreService {

    private final Logger log = LoggerFactory.getLogger(CatGruppocoloreServiceImpl.class);

    private final CatGruppocoloreRepository catGruppocoloreRepository;

    public CatGruppocoloreServiceImpl(CatGruppocoloreRepository catGruppocoloreRepository) {
        this.catGruppocoloreRepository = catGruppocoloreRepository;
    }

    /**
     * Save a catGruppocolore.
     *
     * @param catGruppocolore the entity to save
     * @return the persisted entity
     */
    @Override
    public CatGruppocolore save(CatGruppocolore catGruppocolore) {
        log.debug("Request to save CatGruppocolore : {}", catGruppocolore);
        return catGruppocoloreRepository.save(catGruppocolore);
    }

    /**
     * Get all the catGruppocolores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CatGruppocolore> findAll(Pageable pageable) {
        log.debug("Request to get all CatGruppocolores");
        return catGruppocoloreRepository.findAll(pageable);
    }


    /**
     * Get one catGruppocolore by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CatGruppocolore> findOne(Long id) {
        log.debug("Request to get CatGruppocolore : {}", id);
        return catGruppocoloreRepository.findById(id);
    }

    /**
     * Delete the catGruppocolore by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CatGruppocolore : {}", id);
        catGruppocoloreRepository.deleteById(id);
    }
}
