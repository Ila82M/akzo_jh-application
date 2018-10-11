package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.CatLattinaService;
import com.serijakala.sj4.akzo.akzonobel.domain.CatLattina;
import com.serijakala.sj4.akzo.akzonobel.repository.CatLattinaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CatLattina.
 */
@Service
@Transactional
public class CatLattinaServiceImpl implements CatLattinaService {

    private final Logger log = LoggerFactory.getLogger(CatLattinaServiceImpl.class);

    private final CatLattinaRepository catLattinaRepository;

    public CatLattinaServiceImpl(CatLattinaRepository catLattinaRepository) {
        this.catLattinaRepository = catLattinaRepository;
    }

    /**
     * Save a catLattina.
     *
     * @param catLattina the entity to save
     * @return the persisted entity
     */
    @Override
    public CatLattina save(CatLattina catLattina) {
        log.debug("Request to save CatLattina : {}", catLattina);
        return catLattinaRepository.save(catLattina);
    }

    /**
     * Get all the catLattinas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CatLattina> findAll(Pageable pageable) {
        log.debug("Request to get all CatLattinas");
        return catLattinaRepository.findAll(pageable);
    }


    /**
     * Get one catLattina by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CatLattina> findOne(Long id) {
        log.debug("Request to get CatLattina : {}", id);
        return catLattinaRepository.findById(id);
    }

    /**
     * Delete the catLattina by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CatLattina : {}", id);
        catLattinaRepository.deleteById(id);
    }
}
