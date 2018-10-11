package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.CatTipoprodottoService;
import com.serijakala.sj4.akzo.akzonobel.domain.CatTipoprodotto;
import com.serijakala.sj4.akzo.akzonobel.repository.CatTipoprodottoRepository;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatTipoprodottoDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.CatTipoprodottoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CatTipoprodotto.
 */
@Service
@Transactional
public class CatTipoprodottoServiceImpl implements CatTipoprodottoService {

    private final Logger log = LoggerFactory.getLogger(CatTipoprodottoServiceImpl.class);

    private final CatTipoprodottoRepository catTipoprodottoRepository;

    private final CatTipoprodottoMapper catTipoprodottoMapper;

    public CatTipoprodottoServiceImpl(CatTipoprodottoRepository catTipoprodottoRepository, CatTipoprodottoMapper catTipoprodottoMapper) {
        this.catTipoprodottoRepository = catTipoprodottoRepository;
        this.catTipoprodottoMapper = catTipoprodottoMapper;
    }

    /**
     * Save a catTipoprodotto.
     *
     * @param catTipoprodottoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CatTipoprodottoDTO save(CatTipoprodottoDTO catTipoprodottoDTO) {
        log.debug("Request to save CatTipoprodotto : {}", catTipoprodottoDTO);

        CatTipoprodotto catTipoprodotto = catTipoprodottoMapper.toEntity(catTipoprodottoDTO);
        catTipoprodotto = catTipoprodottoRepository.save(catTipoprodotto);
        return catTipoprodottoMapper.toDto(catTipoprodotto);
    }

    /**
     * Get all the catTipoprodottos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CatTipoprodottoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CatTipoprodottos");
        return catTipoprodottoRepository.findAll(pageable)
            .map(catTipoprodottoMapper::toDto);
    }


    /**
     * Get one catTipoprodotto by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CatTipoprodottoDTO> findOne(Long id) {
        log.debug("Request to get CatTipoprodotto : {}", id);
        return catTipoprodottoRepository.findById(id)
            .map(catTipoprodottoMapper::toDto);
    }

    /**
     * Delete the catTipoprodotto by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CatTipoprodotto : {}", id);
        catTipoprodottoRepository.deleteById(id);
    }
}
