package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.CatTipocoloreService;
import com.serijakala.sj4.akzo.akzonobel.domain.CatTipocolore;
import com.serijakala.sj4.akzo.akzonobel.repository.CatTipocoloreRepository;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatTipocoloreDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.CatTipocoloreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CatTipocolore.
 */
@Service
@Transactional
public class CatTipocoloreServiceImpl implements CatTipocoloreService {

    private final Logger log = LoggerFactory.getLogger(CatTipocoloreServiceImpl.class);

    private final CatTipocoloreRepository catTipocoloreRepository;

    private final CatTipocoloreMapper catTipocoloreMapper;

    public CatTipocoloreServiceImpl(CatTipocoloreRepository catTipocoloreRepository, CatTipocoloreMapper catTipocoloreMapper) {
        this.catTipocoloreRepository = catTipocoloreRepository;
        this.catTipocoloreMapper = catTipocoloreMapper;
    }

    /**
     * Save a catTipocolore.
     *
     * @param catTipocoloreDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CatTipocoloreDTO save(CatTipocoloreDTO catTipocoloreDTO) {
        log.debug("Request to save CatTipocolore : {}", catTipocoloreDTO);

        CatTipocolore catTipocolore = catTipocoloreMapper.toEntity(catTipocoloreDTO);
        catTipocolore = catTipocoloreRepository.save(catTipocolore);
        return catTipocoloreMapper.toDto(catTipocolore);
    }

    /**
     * Get all the catTipocolores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CatTipocoloreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CatTipocolores");
        return catTipocoloreRepository.findAll(pageable)
            .map(catTipocoloreMapper::toDto);
    }


    /**
     * Get one catTipocolore by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CatTipocoloreDTO> findOne(Long id) {
        log.debug("Request to get CatTipocolore : {}", id);
        return catTipocoloreRepository.findById(id)
            .map(catTipocoloreMapper::toDto);
    }

    /**
     * Delete the catTipocolore by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CatTipocolore : {}", id);
        catTipocoloreRepository.deleteById(id);
    }
}
