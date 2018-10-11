package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.ProvinciaService;
import com.serijakala.sj4.akzo.akzonobel.domain.Provincia;
import com.serijakala.sj4.akzo.akzonobel.repository.ProvinciaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Provincia.
 */
@Service
@Transactional
public class ProvinciaServiceImpl implements ProvinciaService {

    private final Logger log = LoggerFactory.getLogger(ProvinciaServiceImpl.class);

    private final ProvinciaRepository provinciaRepository;

    public ProvinciaServiceImpl(ProvinciaRepository provinciaRepository) {
        this.provinciaRepository = provinciaRepository;
    }

    /**
     * Save a provincia.
     *
     * @param provincia the entity to save
     * @return the persisted entity
     */
    @Override
    public Provincia save(Provincia provincia) {
        log.debug("Request to save Provincia : {}", provincia);
        return provinciaRepository.save(provincia);
    }

    /**
     * Get all the provincias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Provincia> findAll(Pageable pageable) {
        log.debug("Request to get all Provincias");
        return provinciaRepository.findAll(pageable);
    }


    /**
     * Get one provincia by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Provincia> findOne(Long id) {
        log.debug("Request to get Provincia : {}", id);
        return provinciaRepository.findById(id);
    }

    /**
     * Delete the provincia by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Provincia : {}", id);
        provinciaRepository.deleteById(id);
    }
}
