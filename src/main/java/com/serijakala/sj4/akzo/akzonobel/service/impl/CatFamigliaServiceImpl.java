package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.CatFamigliaService;
import com.serijakala.sj4.akzo.akzonobel.domain.CatFamiglia;
import com.serijakala.sj4.akzo.akzonobel.repository.CatFamigliaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CatFamiglia.
 */
@Service
@Transactional
public class CatFamigliaServiceImpl implements CatFamigliaService {

    private final Logger log = LoggerFactory.getLogger(CatFamigliaServiceImpl.class);

    private final CatFamigliaRepository catFamigliaRepository;

    public CatFamigliaServiceImpl(CatFamigliaRepository catFamigliaRepository) {
        this.catFamigliaRepository = catFamigliaRepository;
    }

    /**
     * Save a catFamiglia.
     *
     * @param catFamiglia the entity to save
     * @return the persisted entity
     */
    @Override
    public CatFamiglia save(CatFamiglia catFamiglia) {
        log.debug("Request to save CatFamiglia : {}", catFamiglia);
        return catFamigliaRepository.save(catFamiglia);
    }

    /**
     * Get all the catFamiglias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CatFamiglia> findAll(Pageable pageable) {
        log.debug("Request to get all CatFamiglias");
        return catFamigliaRepository.findAll(pageable);
    }


    /**
     * Get one catFamiglia by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CatFamiglia> findOne(Long id) {
        log.debug("Request to get CatFamiglia : {}", id);
        return catFamigliaRepository.findById(id);
    }

    /**
     * Delete the catFamiglia by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CatFamiglia : {}", id);
        catFamigliaRepository.deleteById(id);
    }
}
