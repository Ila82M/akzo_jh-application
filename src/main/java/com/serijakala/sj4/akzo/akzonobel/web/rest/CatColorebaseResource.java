package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.serijakala.sj4.akzo.akzonobel.domain.CatColorebase;
import com.serijakala.sj4.akzo.akzonobel.service.CatColorebaseService;
import com.serijakala.sj4.akzo.akzonobel.web.rest.errors.BadRequestAlertException;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.HeaderUtil;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.PaginationUtil;
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
 * REST controller for managing CatColorebase.
 */
@RestController
@RequestMapping("/api")
public class CatColorebaseResource {

    private final Logger log = LoggerFactory.getLogger(CatColorebaseResource.class);

    private static final String ENTITY_NAME = "catColorebase";

    private final CatColorebaseService catColorebaseService;

    public CatColorebaseResource(CatColorebaseService catColorebaseService) {
        this.catColorebaseService = catColorebaseService;
    }

    /**
     * POST  /cat-colorebases : Create a new catColorebase.
     *
     * @param catColorebase the catColorebase to create
     * @return the ResponseEntity with status 201 (Created) and with body the new catColorebase, or with status 400 (Bad Request) if the catColorebase has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cat-colorebases")
    @Timed
    public ResponseEntity<CatColorebase> createCatColorebase(@RequestBody CatColorebase catColorebase) throws URISyntaxException {
        log.debug("REST request to save CatColorebase : {}", catColorebase);
        if (catColorebase.getId() != null) {
            throw new BadRequestAlertException("A new catColorebase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CatColorebase result = catColorebaseService.save(catColorebase);
        return ResponseEntity.created(new URI("/api/cat-colorebases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cat-colorebases : Updates an existing catColorebase.
     *
     * @param catColorebase the catColorebase to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated catColorebase,
     * or with status 400 (Bad Request) if the catColorebase is not valid,
     * or with status 500 (Internal Server Error) if the catColorebase couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cat-colorebases")
    @Timed
    public ResponseEntity<CatColorebase> updateCatColorebase(@RequestBody CatColorebase catColorebase) throws URISyntaxException {
        log.debug("REST request to update CatColorebase : {}", catColorebase);
        if (catColorebase.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CatColorebase result = catColorebaseService.save(catColorebase);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, catColorebase.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cat-colorebases : get all the catColorebases.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of catColorebases in body
     */
    @GetMapping("/cat-colorebases")
    @Timed
    public ResponseEntity<List<CatColorebase>> getAllCatColorebases(Pageable pageable) {
        log.debug("REST request to get a page of CatColorebases");
        Page<CatColorebase> page = catColorebaseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cat-colorebases");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cat-colorebases/:id : get the "id" catColorebase.
     *
     * @param id the id of the catColorebase to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the catColorebase, or with status 404 (Not Found)
     */
    @GetMapping("/cat-colorebases/{id}")
    @Timed
    public ResponseEntity<CatColorebase> getCatColorebase(@PathVariable Long id) {
        log.debug("REST request to get CatColorebase : {}", id);
        Optional<CatColorebase> catColorebase = catColorebaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(catColorebase);
    }

    /**
     * DELETE  /cat-colorebases/:id : delete the "id" catColorebase.
     *
     * @param id the id of the catColorebase to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cat-colorebases/{id}")
    @Timed
    public ResponseEntity<Void> deleteCatColorebase(@PathVariable Long id) {
        log.debug("REST request to delete CatColorebase : {}", id);
        catColorebaseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
