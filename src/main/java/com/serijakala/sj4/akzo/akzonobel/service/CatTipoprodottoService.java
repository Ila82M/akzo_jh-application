package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.service.dto.CatTipoprodottoDTO;

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
     * @param catTipoprodottoDTO the entity to save
     * @return the persisted entity
     */
    CatTipoprodottoDTO save(CatTipoprodottoDTO catTipoprodottoDTO);

    /**
     * Get all the catTipoprodottos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CatTipoprodottoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" catTipoprodotto.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CatTipoprodottoDTO> findOne(Long id);

    /**
     * Delete the "id" catTipoprodotto.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
