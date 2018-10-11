package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.CatGruppocoloreService;
import com.serijakala.sj4.akzo.akzonobel.domain.CatGruppocolore;
import com.serijakala.sj4.akzo.akzonobel.repository.CatGruppocoloreRepository;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatGruppocoloreDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.CatGruppocoloreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CatGruppocolore.
 */
@Service
@Transactional
public class CatGruppocoloreServiceImpl implements CatGruppocoloreService {

    private final Logger log = LoggerFactory.getLogger(CatGruppocoloreServiceImpl.class);

    private final CatGruppocoloreRepository catGruppocoloreRepository;

    private final CatGruppocoloreMapper catGruppocoloreMapper;

    public CatGruppocoloreServiceImpl(CatGruppocoloreRepository catGruppocoloreRepository, CatGruppocoloreMapper catGruppocoloreMapper) {
        this.catGruppocoloreRepository = catGruppocoloreRepository;
        this.catGruppocoloreMapper = catGruppocoloreMapper;
    }

    /**
     * Save a catGruppocolore.
     *
     * @param catGruppocoloreDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CatGruppocoloreDTO save(CatGruppocoloreDTO catGruppocoloreDTO) {
        log.debug("Request to save CatGruppocolore : {}", catGruppocoloreDTO);

        CatGruppocolore catGruppocolore = catGruppocoloreMapper.toEntity(catGruppocoloreDTO);
        catGruppocolore = catGruppocoloreRepository.save(catGruppocolore);
        return catGruppocoloreMapper.toDto(catGruppocolore);
    }

    /**
     * Get all the catGruppocolores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CatGruppocoloreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CatGruppocolores");
        return catGruppocoloreRepository.findAll(pageable)
            .map(catGruppocoloreMapper::toDto);
    }


    /**
     * Get one catGruppocolore by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CatGruppocoloreDTO> findOne(Long id) {
        log.debug("Request to get CatGruppocolore : {}", id);
        return catGruppocoloreRepository.findById(id)
            .map(catGruppocoloreMapper::toDto);
    }

    /**
     * Delete the catGruppocolore by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CatGruppocolore : {}", id);
        catGruppocoloreRepository.deleteById(id);
    }
}
