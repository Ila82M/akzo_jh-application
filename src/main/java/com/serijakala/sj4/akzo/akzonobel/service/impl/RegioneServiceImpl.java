package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.RegioneService;
import com.serijakala.sj4.akzo.akzonobel.domain.Regione;
import com.serijakala.sj4.akzo.akzonobel.repository.RegioneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Regione.
 */
@Service
@Transactional
public class RegioneServiceImpl implements RegioneService {

    private final Logger log = LoggerFactory.getLogger(RegioneServiceImpl.class);

    private final RegioneRepository regioneRepository;

    public RegioneServiceImpl(RegioneRepository regioneRepository) {
        this.regioneRepository = regioneRepository;
    }

    /**
     * Save a regione.
     *
     * @param regione the entity to save
     * @return the persisted entity
     */
    @Override
    public Regione save(Regione regione) {
        log.debug("Request to save Regione : {}", regione);
        return regioneRepository.save(regione);
    }

    /**
     * Get all the regiones.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Regione> findAll(Pageable pageable) {
        log.debug("Request to get all Regiones");
        return regioneRepository.findAll(pageable);
    }


    /**
     * Get one regione by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Regione> findOne(Long id) {
        log.debug("Request to get Regione : {}", id);
        return regioneRepository.findById(id);
    }

    /**
     * Delete the regione by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Regione : {}", id);
        regioneRepository.deleteById(id);
    }
}
