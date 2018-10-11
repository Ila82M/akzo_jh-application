package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.CatColoreService;
import com.serijakala.sj4.akzo.akzonobel.domain.CatColore;
import com.serijakala.sj4.akzo.akzonobel.repository.CatColoreRepository;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatColoreDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.CatColoreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CatColore.
 */
@Service
@Transactional
public class CatColoreServiceImpl implements CatColoreService {

    private final Logger log = LoggerFactory.getLogger(CatColoreServiceImpl.class);

    private final CatColoreRepository catColoreRepository;

    private final CatColoreMapper catColoreMapper;

    public CatColoreServiceImpl(CatColoreRepository catColoreRepository, CatColoreMapper catColoreMapper) {
        this.catColoreRepository = catColoreRepository;
        this.catColoreMapper = catColoreMapper;
    }

    /**
     * Save a catColore.
     *
     * @param catColoreDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CatColoreDTO save(CatColoreDTO catColoreDTO) {
        log.debug("Request to save CatColore : {}", catColoreDTO);

        CatColore catColore = catColoreMapper.toEntity(catColoreDTO);
        catColore = catColoreRepository.save(catColore);
        return catColoreMapper.toDto(catColore);
    }

    /**
     * Get all the catColores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CatColoreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CatColores");
        return catColoreRepository.findAll(pageable)
            .map(catColoreMapper::toDto);
    }


    /**
     * Get one catColore by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CatColoreDTO> findOne(Long id) {
        log.debug("Request to get CatColore : {}", id);
        return catColoreRepository.findById(id)
            .map(catColoreMapper::toDto);
    }

    /**
     * Delete the catColore by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CatColore : {}", id);
        catColoreRepository.deleteById(id);
    }
}
