package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.CatLattinaService;
import com.serijakala.sj4.akzo.akzonobel.domain.CatLattina;
import com.serijakala.sj4.akzo.akzonobel.repository.CatLattinaRepository;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatLattinaDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.CatLattinaMapper;
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

    private final CatLattinaMapper catLattinaMapper;

    public CatLattinaServiceImpl(CatLattinaRepository catLattinaRepository, CatLattinaMapper catLattinaMapper) {
        this.catLattinaRepository = catLattinaRepository;
        this.catLattinaMapper = catLattinaMapper;
    }

    /**
     * Save a catLattina.
     *
     * @param catLattinaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CatLattinaDTO save(CatLattinaDTO catLattinaDTO) {
        log.debug("Request to save CatLattina : {}", catLattinaDTO);

        CatLattina catLattina = catLattinaMapper.toEntity(catLattinaDTO);
        catLattina = catLattinaRepository.save(catLattina);
        return catLattinaMapper.toDto(catLattina);
    }

    /**
     * Get all the catLattinas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CatLattinaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CatLattinas");
        return catLattinaRepository.findAll(pageable)
            .map(catLattinaMapper::toDto);
    }


    /**
     * Get one catLattina by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CatLattinaDTO> findOne(Long id) {
        log.debug("Request to get CatLattina : {}", id);
        return catLattinaRepository.findById(id)
            .map(catLattinaMapper::toDto);
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
