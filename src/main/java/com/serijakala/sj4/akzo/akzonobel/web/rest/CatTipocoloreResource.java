package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.serijakala.sj4.akzo.akzonobel.domain.CatTipocolore;
import com.serijakala.sj4.akzo.akzonobel.service.CatTipocoloreService;
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
 * REST controller for managing CatTipocolore.
 */
@RestController
@RequestMapping("/api")
public class CatTipocoloreResource {

    private final Logger log = LoggerFactory.getLogger(CatTipocoloreResource.class);

    private static final String ENTITY_NAME = "catTipocolore";

    private final CatTipocoloreService catTipocoloreService;

    public CatTipocoloreResource(CatTipocoloreService catTipocoloreService) {
        this.catTipocoloreService = catTipocoloreService;
    }

    /**
     * POST  /cat-tipocolores : Create a new catTipocolore.
     *
     * @param catTipocolore the catTipocolore to create
     * @return the ResponseEntity with status 201 (Created) and with body the new catTipocolore, or with status 400 (Bad Request) if the catTipocolore has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cat-tipocolores")
    @Timed
    public ResponseEntity<CatTipocolore> createCatTipocolore(@RequestBody CatTipocolore catTipocolore) throws URISyntaxException {
        log.debug("REST request to save CatTipocolore : {}", catTipocolore);
        if (catTipocolore.getId() != null) {
            throw new BadRequestAlertException("A new catTipocolore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CatTipocolore result = catTipocoloreService.save(catTipocolore);
        return ResponseEntity.created(new URI("/api/cat-tipocolores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cat-tipocolores : Updates an existing catTipocolore.
     *
     * @param catTipocolore the catTipocolore to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated catTipocolore,
     * or with status 400 (Bad Request) if the catTipocolore is not valid,
     * or with status 500 (Internal Server Error) if the catTipocolore couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cat-tipocolores")
    @Timed
    public ResponseEntity<CatTipocolore> updateCatTipocolore(@RequestBody CatTipocolore catTipocolore) throws URISyntaxException {
        log.debug("REST request to update CatTipocolore : {}", catTipocolore);
        if (catTipocolore.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CatTipocolore result = catTipocoloreService.save(catTipocolore);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, catTipocolore.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cat-tipocolores : get all the catTipocolores.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of catTipocolores in body
     */
    @GetMapping("/cat-tipocolores")
    @Timed
    public ResponseEntity<List<CatTipocolore>> getAllCatTipocolores(Pageable pageable) {
        log.debug("REST request to get a page of CatTipocolores");
        Page<CatTipocolore> page = catTipocoloreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cat-tipocolores");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cat-tipocolores/:id : get the "id" catTipocolore.
     *
     * @param id the id of the catTipocolore to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the catTipocolore, or with status 404 (Not Found)
     */
    @GetMapping("/cat-tipocolores/{id}")
    @Timed
    public ResponseEntity<CatTipocolore> getCatTipocolore(@PathVariable Long id) {
        log.debug("REST request to get CatTipocolore : {}", id);
        Optional<CatTipocolore> catTipocolore = catTipocoloreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(catTipocolore);
    }

    /**
     * DELETE  /cat-tipocolores/:id : delete the "id" catTipocolore.
     *
     * @param id the id of the catTipocolore to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cat-tipocolores/{id}")
    @Timed
    public ResponseEntity<Void> deleteCatTipocolore(@PathVariable Long id) {
        log.debug("REST request to delete CatTipocolore : {}", id);
        catTipocoloreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
