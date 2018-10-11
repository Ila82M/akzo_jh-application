package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.ComuneService;
import com.serijakala.sj4.akzo.akzonobel.domain.Comune;
import com.serijakala.sj4.akzo.akzonobel.repository.ComuneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Comune.
 */
@Service
@Transactional
public class ComuneServiceImpl implements ComuneService {

    private final Logger log = LoggerFactory.getLogger(ComuneServiceImpl.class);

    private final ComuneRepository comuneRepository;

    public ComuneServiceImpl(ComuneRepository comuneRepository) {
        this.comuneRepository = comuneRepository;
    }

    /**
     * Save a comune.
     *
     * @param comune the entity to save
     * @return the persisted entity
     */
    @Override
    public Comune save(Comune comune) {
        log.debug("Request to save Comune : {}", comune);
        return comuneRepository.save(comune);
    }

    /**
     * Get all the comunes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Comune> findAll(Pageable pageable) {
        log.debug("Request to get all Comunes");
        return comuneRepository.findAll(pageable);
    }


    /**
     * Get one comune by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Comune> findOne(Long id) {
        log.debug("Request to get Comune : {}", id);
        return comuneRepository.findById(id);
    }

    /**
     * Delete the comune by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Comune : {}", id);
        comuneRepository.deleteById(id);
    }
}
