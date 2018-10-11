package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.CatColorebaseService;
import com.serijakala.sj4.akzo.akzonobel.domain.CatColorebase;
import com.serijakala.sj4.akzo.akzonobel.repository.CatColorebaseRepository;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatColorebaseDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.CatColorebaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CatColorebase.
 */
@Service
@Transactional
public class CatColorebaseServiceImpl implements CatColorebaseService {

    private final Logger log = LoggerFactory.getLogger(CatColorebaseServiceImpl.class);

    private final CatColorebaseRepository catColorebaseRepository;

    private final CatColorebaseMapper catColorebaseMapper;

    public CatColorebaseServiceImpl(CatColorebaseRepository catColorebaseRepository, CatColorebaseMapper catColorebaseMapper) {
        this.catColorebaseRepository = catColorebaseRepository;
        this.catColorebaseMapper = catColorebaseMapper;
    }

    /**
     * Save a catColorebase.
     *
     * @param catColorebaseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CatColorebaseDTO save(CatColorebaseDTO catColorebaseDTO) {
        log.debug("Request to save CatColorebase : {}", catColorebaseDTO);

        CatColorebase catColorebase = catColorebaseMapper.toEntity(catColorebaseDTO);
        catColorebase = catColorebaseRepository.save(catColorebase);
        return catColorebaseMapper.toDto(catColorebase);
    }

    /**
     * Get all the catColorebases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CatColorebaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CatColorebases");
        return catColorebaseRepository.findAll(pageable)
            .map(catColorebaseMapper::toDto);
    }


    /**
     * Get one catColorebase by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CatColorebaseDTO> findOne(Long id) {
        log.debug("Request to get CatColorebase : {}", id);
        return catColorebaseRepository.findById(id)
            .map(catColorebaseMapper::toDto);
    }

    /**
     * Delete the catColorebase by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CatColorebase : {}", id);
        catColorebaseRepository.deleteById(id);
    }
}
