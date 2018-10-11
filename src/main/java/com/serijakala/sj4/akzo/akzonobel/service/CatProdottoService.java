package com.serijakala.sj4.akzo.akzonobel.service;

import com.serijakala.sj4.akzo.akzonobel.service.dto.CatProdottoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CatProdotto.
 */
public interface CatProdottoService {

    /**
     * Save a catProdotto.
     *
     * @param catProdottoDTO the entity to save
     * @return the persisted entity
     */
    CatProdottoDTO save(CatProdottoDTO catProdottoDTO);

    /**
     * Get all the catProdottos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CatProdottoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" catProdotto.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CatProdottoDTO> findOne(Long id);

    /**
     * Delete the "id" catProdotto.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
