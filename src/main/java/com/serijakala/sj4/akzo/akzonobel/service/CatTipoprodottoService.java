package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.domain.CatTipoprodotto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CatTipoprodotto.
 */
public interface CatTipoprodottoService {

    /**
     * Save a catTipoprodotto.
     *
     * @param catTipoprodotto the entity to save
     * @return the persisted entity
     */
    CatTipoprodotto save(CatTipoprodotto catTipoprodotto);

    /**
     * Get all the catTipoprodottos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CatTipoprodotto> findAll(Pageable pageable);


    /**
     * Get the "id" catTipoprodotto.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CatTipoprodotto> findOne(Long id);

    /**
     * Delete the "id" catTipoprodotto.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
