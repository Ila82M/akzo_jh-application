package com.serijakala.sj4.akzo.akzonobel.service.impl;

import com.serijakala.sj4.akzo.akzonobel.service.CatProdottoService;
import com.serijakala.sj4.akzo.akzonobel.domain.CatProdotto;
import com.serijakala.sj4.akzo.akzonobel.repository.CatProdottoRepository;
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

    public CatProdottoServiceImpl(CatProdottoRepository catProdottoRepository) {
        this.catProdottoRepository = catProdottoRepository;
    }

    /**
     * Save a catProdotto.
     *
     * @param catProdotto the entity to save
     * @return the persisted entity
     */
    @Override
    public CatProdotto save(CatProdotto catProdotto) {
        log.debug("Request to save CatProdotto : {}", catProdotto);
        return catProdottoRepository.save(catProdotto);
    }

    /**
     * Get all the catProdottos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CatProdotto> findAll(Pageable pageable) {
        log.debug("Request to get all CatProdottos");
        return catProdottoRepository.findAll(pageable);
    }


    /**
     * Get one catProdotto by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CatProdotto> findOne(Long id) {
        log.debug("Request to get CatProdotto : {}", id);
        return catProdottoRepository.findById(id);
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
