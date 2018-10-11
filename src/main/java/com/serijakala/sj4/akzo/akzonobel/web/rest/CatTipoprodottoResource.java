package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.serijakala.sj4.akzo.akzonobel.service.CatTipoprodottoService;
import com.serijakala.sj4.akzo.akzonobel.web.rest.errors.BadRequestAlertException;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.HeaderUtil;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.PaginationUtil;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatTipoprodottoDTO;
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
 * REST controller for managing CatTipoprodotto.
 */
@RestController
@RequestMapping("/api")
public class CatTipoprodottoResource {

    private final Logger log = LoggerFactory.getLogger(CatTipoprodottoResource.class);

    private static final String ENTITY_NAME = "catTipoprodotto";

    private final CatTipoprodottoService catTipoprodottoService;

    public CatTipoprodottoResource(CatTipoprodottoService catTipoprodottoService) {
        this.catTipoprodottoService = catTipoprodottoService;
    }

    /**
     * POST  /cat-tipoprodottos : Create a new catTipoprodotto.
     *
     * @param catTipoprodottoDTO the catTipoprodottoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new catTipoprodottoDTO, or with status 400 (Bad Request) if the catTipoprodotto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cat-tipoprodottos")
    @Timed
    public ResponseEntity<CatTipoprodottoDTO> createCatTipoprodotto(@RequestBody CatTipoprodottoDTO catTipoprodottoDTO) throws URISyntaxException {
        log.debug("REST request to save CatTipoprodotto : {}", catTipoprodottoDTO);
        if (catTipoprodottoDTO.getId() != null) {
            throw new BadRequestAlertException("A new catTipoprodotto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CatTipoprodottoDTO result = catTipoprodottoService.save(catTipoprodottoDTO);
        return ResponseEntity.created(new URI("/api/cat-tipoprodottos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cat-tipoprodottos : Updates an existing catTipoprodotto.
     *
     * @param catTipoprodottoDTO the catTipoprodottoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated catTipoprodottoDTO,
     * or with status 400 (Bad Request) if the catTipoprodottoDTO is not valid,
     * or with status 500 (Internal Server Error) if the catTipoprodottoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cat-tipoprodottos")
    @Timed
    public ResponseEntity<CatTipoprodottoDTO> updateCatTipoprodotto(@RequestBody CatTipoprodottoDTO catTipoprodottoDTO) throws URISyntaxException {
        log.debug("REST request to update CatTipoprodotto : {}", catTipoprodottoDTO);
        if (catTipoprodottoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CatTipoprodottoDTO result = catTipoprodottoService.save(catTipoprodottoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, catTipoprodottoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cat-tipoprodottos : get all the catTipoprodottos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of catTipoprodottos in body
     */
    @GetMapping("/cat-tipoprodottos")
    @Timed
    public ResponseEntity<List<CatTipoprodottoDTO>> getAllCatTipoprodottos(Pageable pageable) {
        log.debug("REST request to get a page of CatTipoprodottos");
        Page<CatTipoprodottoDTO> page = catTipoprodottoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cat-tipoprodottos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cat-tipoprodottos/:id : get the "id" catTipoprodotto.
     *
     * @param id the id of the catTipoprodottoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the catTipoprodottoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cat-tipoprodottos/{id}")
    @Timed
    public ResponseEntity<CatTipoprodottoDTO> getCatTipoprodotto(@PathVariable Long id) {
        log.debug("REST request to get CatTipoprodotto : {}", id);
        Optional<CatTipoprodottoDTO> catTipoprodottoDTO = catTipoprodottoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(catTipoprodottoDTO);
    }

    /**
     * DELETE  /cat-tipoprodottos/:id : delete the "id" catTipoprodotto.
     *
     * @param id the id of the catTipoprodottoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cat-tipoprodottos/{id}")
    @Timed
    public ResponseEntity<Void> deleteCatTipoprodotto(@PathVariable Long id) {
        log.debug("REST request to delete CatTipoprodotto : {}", id);
        catTipoprodottoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
