package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.CatBaseService;
import com.serijakala.sj4.akzo.akzonobel.domain.CatBase;
import com.serijakala.sj4.akzo.akzonobel.repository.CatBaseRepository;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatBaseDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.CatBaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CatBase.
 */
@Service
@Transactional
public class CatBaseServiceImpl implements CatBaseService {

    private final Logger log = LoggerFactory.getLogger(CatBaseServiceImpl.class);

    private final CatBaseRepository catBaseRepository;

    private final CatBaseMapper catBaseMapper;

    public CatBaseServiceImpl(CatBaseRepository catBaseRepository, CatBaseMapper catBaseMapper) {
        this.catBaseRepository = catBaseRepository;
        this.catBaseMapper = catBaseMapper;
    }

    /**
     * Save a catBase.
     *
     * @param catBaseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CatBaseDTO save(CatBaseDTO catBaseDTO) {
        log.debug("Request to save CatBase : {}", catBaseDTO);

        CatBase catBase = catBaseMapper.toEntity(catBaseDTO);
        catBase = catBaseRepository.save(catBase);
        return catBaseMapper.toDto(catBase);
    }

    /**
     * Get all the catBases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CatBaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CatBases");
        return catBaseRepository.findAll(pageable)
            .map(catBaseMapper::toDto);
    }


    /**
     * Get one catBase by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CatBaseDTO> findOne(Long id) {
        log.debug("Request to get CatBase : {}", id);
        return catBaseRepository.findById(id)
            .map(catBaseMapper::toDto);
    }

    /**
     * Delete the catBase by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CatBase : {}", id);
        catBaseRepository.deleteById(id);
    }
}
