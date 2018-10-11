package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.ComuneService;
import com.serijakala.sj4.akzo.akzonobel.domain.Comune;
import com.serijakala.sj4.akzo.akzonobel.repository.ComuneRepository;
import com.serijakala.sj4.akzo.akzonobel.service.dto.ComuneDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.ComuneMapper;
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

    private final ComuneMapper comuneMapper;

    public ComuneServiceImpl(ComuneRepository comuneRepository, ComuneMapper comuneMapper) {
        this.comuneRepository = comuneRepository;
        this.comuneMapper = comuneMapper;
    }

    /**
     * Save a comune.
     *
     * @param comuneDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ComuneDTO save(ComuneDTO comuneDTO) {
        log.debug("Request to save Comune : {}", comuneDTO);

        Comune comune = comuneMapper.toEntity(comuneDTO);
        comune = comuneRepository.save(comune);
        return comuneMapper.toDto(comune);
    }

    /**
     * Get all the comunes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ComuneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Comunes");
        return comuneRepository.findAll(pageable)
            .map(comuneMapper::toDto);
    }


    /**
     * Get one comune by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ComuneDTO> findOne(Long id) {
        log.debug("Request to get Comune : {}", id);
        return comuneRepository.findById(id)
            .map(comuneMapper::toDto);
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
