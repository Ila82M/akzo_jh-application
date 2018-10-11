package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.CatProdottoService;
import com.serijakala.sj4.akzo.akzonobel.domain.CatProdotto;
import com.serijakala.sj4.akzo.akzonobel.repository.CatProdottoRepository;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatProdottoDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.CatProdottoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CatProdotto.
 */
@Service
@Transactional
public class CatProdottoServiceImpl implements CatProdottoService {

    private final Logger log = LoggerFactory.getLogger(CatProdottoServiceImpl.class);

    private final CatProdottoRepository catProdottoRepository;

    private final CatProdottoMapper catProdottoMapper;

    public CatProdottoServiceImpl(CatProdottoRepository catProdottoRepository, CatProdottoMapper catProdottoMapper) {
        this.catProdottoRepository = catProdottoRepository;
        this.catProdottoMapper = catProdottoMapper;
    }

    /**
     * Save a catProdotto.
     *
     * @param catProdottoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CatProdottoDTO save(CatProdottoDTO catProdottoDTO) {
        log.debug("Request to save CatProdotto : {}", catProdottoDTO);

        CatProdotto catProdotto = catProdottoMapper.toEntity(catProdottoDTO);
        catProdotto = catProdottoRepository.save(catProdotto);
        return catProdottoMapper.toDto(catProdotto);
    }

    /**
     * Get all the catProdottos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CatProdottoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CatProdottos");
        return catProdottoRepository.findAll(pageable)
            .map(catProdottoMapper::toDto);
    }


    /**
     * Get one catProdotto by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CatProdottoDTO> findOne(Long id) {
        log.debug("Request to get CatProdotto : {}", id);
        return catProdottoRepository.findById(id)
            .map(catProdottoMapper::toDto);
    }

    /**
     * Delete the catProdotto by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CatProdotto : {}", id);
        catProdottoRepository.deleteById(id);
    }
}
