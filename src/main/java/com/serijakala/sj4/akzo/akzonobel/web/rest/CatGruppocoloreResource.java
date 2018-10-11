package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.serijakala.sj4.akzo.akzonobel.domain.CatGruppocolore;
import com.serijakala.sj4.akzo.akzonobel.service.CatGruppocoloreService;
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
 * REST controller for managing CatGruppocolore.
 */
@RestController
@RequestMapping("/api")
public class CatGruppocoloreResource {

    private final Logger log = LoggerFactory.getLogger(CatGruppocoloreResource.class);

    private static final String ENTITY_NAME = "catGruppocolore";

    private final CatGruppocoloreService catGruppocoloreService;

    public CatGruppocoloreResource(CatGruppocoloreService catGruppocoloreService) {
        this.catGruppocoloreService = catGruppocoloreService;
    }

    /**
     * POST  /cat-gruppocolores : Create a new catGruppocolore.
     *
     * @param catGruppocolore the catGruppocolore to create
     * @return the ResponseEntity with status 201 (Created) and with body the new catGruppocolore, or with status 400 (Bad Request) if the catGruppocolore has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cat-gruppocolores")
    @Timed
    public ResponseEntity<CatGruppocolore> createCatGruppocolore(@RequestBody CatGruppocolore catGruppocolore) throws URISyntaxException {
        log.debug("REST request to save CatGruppocolore : {}", catGruppocolore);
        if (catGruppocolore.getId() != null) {
            throw new BadRequestAlertException("A new catGruppocolore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CatGruppocolore result = catGruppocoloreService.save(catGruppocolore);
        return ResponseEntity.created(new URI("/api/cat-gruppocolores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cat-gruppocolores : Updates an existing catGruppocolore.
     *
     * @param catGruppocolore the catGruppocolore to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated catGruppocolore,
     * or with status 400 (Bad Request) if the catGruppocolore is not valid,
     * or with status 500 (Internal Server Error) if the catGruppocolore couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cat-gruppocolores")
    @Timed
    public ResponseEntity<CatGruppocolore> updateCatGruppocolore(@RequestBody CatGruppocolore catGruppocolore) throws URISyntaxException {
        log.debug("REST request to update CatGruppocolore : {}", catGruppocolore);
        if (catGruppocolore.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CatGruppocolore result = catGruppocoloreService.save(catGruppocolore);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, catGruppocolore.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cat-gruppocolores : get all the catGruppocolores.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of catGruppocolores in body
     */
    @GetMapping("/cat-gruppocolores")
    @Timed
    public ResponseEntity<List<CatGruppocolore>> getAllCatGruppocolores(Pageable pageable) {
        log.debug("REST request to get a page of CatGruppocolores");
        Page<CatGruppocolore> page = catGruppocoloreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cat-gruppocolores");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cat-gruppocolores/:id : get the "id" catGruppocolore.
     *
     * @param id the id of the catGruppocolore to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the catGruppocolore, or with status 404 (Not Found)
     */
    @GetMapping("/cat-gruppocolores/{id}")
    @Timed
    public ResponseEntity<CatGruppocolore> getCatGruppocolore(@PathVariable Long id) {
        log.debug("REST request to get CatGruppocolore : {}", id);
        Optional<CatGruppocolore> catGruppocolore = catGruppocoloreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(catGruppocolore);
    }

    /**
     * DELETE  /cat-gruppocolores/:id : delete the "id" catGruppocolore.
     *
     * @param id the id of the catGruppocolore to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cat-gruppocolores/{id}")
    @Timed
    public ResponseEntity<Void> deleteCatGruppocolore(@PathVariable Long id) {
        log.debug("REST request to delete CatGruppocolore : {}", id);
        catGruppocoloreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
