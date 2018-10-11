package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.RegioneService;
import com.serijakala.sj4.akzo.akzonobel.domain.Regione;
import com.serijakala.sj4.akzo.akzonobel.repository.RegioneRepository;
import com.serijakala.sj4.akzo.akzonobel.service.dto.RegioneDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.RegioneMapper;
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

    private final RegioneMapper regioneMapper;

    public RegioneServiceImpl(RegioneRepository regioneRepository, RegioneMapper regioneMapper) {
        this.regioneRepository = regioneRepository;
        this.regioneMapper = regioneMapper;
    }

    /**
     * Save a regione.
     *
     * @param regioneDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RegioneDTO save(RegioneDTO regioneDTO) {
        log.debug("Request to save Regione : {}", regioneDTO);

        Regione regione = regioneMapper.toEntity(regioneDTO);
        regione = regioneRepository.save(regione);
        return regioneMapper.toDto(regione);
    }

    /**
     * Get all the regiones.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RegioneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Regiones");
        return regioneRepository.findAll(pageable)
            .map(regioneMapper::toDto);
    }


    /**
     * Get one regione by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RegioneDTO> findOne(Long id) {
        log.debug("Request to get Regione : {}", id);
        return regioneRepository.findById(id)
            .map(regioneMapper::toDto);
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
