package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.CatTipocoloreService;
import com.serijakala.sj4.akzo.akzonobel.domain.CatTipocolore;
import com.serijakala.sj4.akzo.akzonobel.repository.CatTipocoloreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CatTipocolore.
 */
@Service
@Transactional
public class CatTipocoloreServiceImpl implements CatTipocoloreService {

    private final Logger log = LoggerFactory.getLogger(CatTipocoloreServiceImpl.class);

    private final CatTipocoloreRepository catTipocoloreRepository;

    public CatTipocoloreServiceImpl(CatTipocoloreRepository catTipocoloreRepository) {
        this.catTipocoloreRepository = catTipocoloreRepository;
    }

    /**
     * Save a catTipocolore.
     *
     * @param catTipocolore the entity to save
     * @return the persisted entity
     */
    @Override
    public CatTipocolore save(CatTipocolore catTipocolore) {
        log.debug("Request to save CatTipocolore : {}", catTipocolore);
        return catTipocoloreRepository.save(catTipocolore);
    }

    /**
     * Get all the catTipocolores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CatTipocolore> findAll(Pageable pageable) {
        log.debug("Request to get all CatTipocolores");
        return catTipocoloreRepository.findAll(pageable);
    }


    /**
     * Get one catTipocolore by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CatTipocolore> findOne(Long id) {
        log.debug("Request to get CatTipocolore : {}", id);
        return catTipocoloreRepository.findById(id);
    }

    /**
     * Delete the catTipocolore by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CatTipocolore : {}", id);
        catTipocoloreRepository.deleteById(id);
    }
}
