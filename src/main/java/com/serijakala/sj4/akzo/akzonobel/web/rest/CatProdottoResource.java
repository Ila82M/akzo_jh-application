package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.serijakala.sj4.akzo.akzonobel.service.CatProdottoService;
import com.serijakala.sj4.akzo.akzonobel.web.rest.errors.BadRequestAlertException;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.HeaderUtil;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.PaginationUtil;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatProdottoDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CatProdotto.
 */
@RestController
@RequestMapping("/api")
public class CatProdottoResource {

    private final Logger log = LoggerFactory.getLogger(CatProdottoResource.class);

    private static final String ENTITY_NAME = "catProdotto";

    private final CatProdottoService catProdottoService;

    public CatProdottoResource(CatProdottoService catProdottoService) {
        this.catProdottoService = catProdottoService;
    }

    /**
     * POST  /cat-prodottos : Create a new catProdotto.
     *
     * @param catProdottoDTO the catProdottoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new catProdottoDTO, or with status 400 (Bad Request) if the catProdotto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cat-prodottos")
    @Timed
    public ResponseEntity<CatProdottoDTO> createCatProdotto(@RequestBody CatProdottoDTO catProdottoDTO) throws URISyntaxException {
        log.debug("REST request to save CatProdotto : {}", catProdottoDTO);
        if (catProdottoDTO.getId() != null) {
            throw new BadRequestAlertException("A new catProdotto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CatProdottoDTO result = catProdottoService.save(catProdottoDTO);
        return ResponseEntity.created(new URI("/api/cat-prodottos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cat-prodottos : Updates an existing catProdotto.
     *
     * @param catProdottoDTO the catProdottoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated catProdottoDTO,
     * or with status 400 (Bad Request) if the catProdottoDTO is not valid,
     * or with status 500 (Internal Server Error) if the catProdottoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cat-prodottos")
    @Timed
    public ResponseEntity<CatProdottoDTO> updateCatProdotto(@RequestBody CatProdottoDTO catProdottoDTO) throws URISyntaxException {
        log.debug("REST request to update CatProdotto : {}", catProdottoDTO);
        if (catProdottoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CatProdottoDTO result = catProdottoService.save(catProdottoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, catProdottoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cat-prodottos : get all the catProdottos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of catProdottos in body
     */
    @GetMapping("/cat-prodottos")
    @Timed
    public ResponseEntity<List<CatProdottoDTO>> getAllCatProdottos(Pageable pageable) {
        log.debug("REST request to get a page of CatProdottos");
        Page<CatProdottoDTO> page = catProdottoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cat-prodottos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cat-prodottos/:id : get the "id" catProdotto.
     *
     * @param id the id of the catProdottoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the catProdottoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cat-prodottos/{id}")
    @Timed
    public ResponseEntity<CatProdottoDTO> getCatProdotto(@PathVariable Long id) {
        log.debug("REST request to get CatProdotto : {}", id);
        Optional<CatProdottoDTO> catProdottoDTO = catProdottoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(catProdottoDTO);
    }

    /**
     * DELETE  /cat-prodottos/:id : delete the "id" catProdotto.
     *
     * @param id the id of the catProdottoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cat-prodottos/{id}")
    @Timed
    public ResponseEntity<Void> deleteCatProdotto(@PathVariable Long id) {
        log.debug("REST request to delete CatProdotto : {}", id);
        catProdottoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
