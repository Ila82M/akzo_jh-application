package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.serijakala.sj4.akzo.akzonobel.service.CatTipocoloreService;
import com.serijakala.sj4.akzo.akzonobel.web.rest.errors.BadRequestAlertException;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.HeaderUtil;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.PaginationUtil;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatTipocoloreDTO;
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
     * @param catTipocoloreDTO the catTipocoloreDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new catTipocoloreDTO, or with status 400 (Bad Request) if the catTipocolore has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cat-tipocolores")
    @Timed
    public ResponseEntity<CatTipocoloreDTO> createCatTipocolore(@RequestBody CatTipocoloreDTO catTipocoloreDTO) throws URISyntaxException {
        log.debug("REST request to save CatTipocolore : {}", catTipocoloreDTO);
        if (catTipocoloreDTO.getId() != null) {
            throw new BadRequestAlertException("A new catTipocolore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CatTipocoloreDTO result = catTipocoloreService.save(catTipocoloreDTO);
        return ResponseEntity.created(new URI("/api/cat-tipocolores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cat-tipocolores : Updates an existing catTipocolore.
     *
     * @param catTipocoloreDTO the catTipocoloreDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated catTipocoloreDTO,
     * or with status 400 (Bad Request) if the catTipocoloreDTO is not valid,
     * or with status 500 (Internal Server Error) if the catTipocoloreDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cat-tipocolores")
    @Timed
    public ResponseEntity<CatTipocoloreDTO> updateCatTipocolore(@RequestBody CatTipocoloreDTO catTipocoloreDTO) throws URISyntaxException {
        log.debug("REST request to update CatTipocolore : {}", catTipocoloreDTO);
        if (catTipocoloreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CatTipocoloreDTO result = catTipocoloreService.save(catTipocoloreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, catTipocoloreDTO.getId().toString()))
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
    public ResponseEntity<List<CatTipocoloreDTO>> getAllCatTipocolores(Pageable pageable) {
        log.debug("REST request to get a page of CatTipocolores");
        Page<CatTipocoloreDTO> page = catTipocoloreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cat-tipocolores");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cat-tipocolores/:id : get the "id" catTipocolore.
     *
     * @param id the id of the catTipocoloreDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the catTipocoloreDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cat-tipocolores/{id}")
    @Timed
    public ResponseEntity<CatTipocoloreDTO> getCatTipocolore(@PathVariable Long id) {
        log.debug("REST request to get CatTipocolore : {}", id);
        Optional<CatTipocoloreDTO> catTipocoloreDTO = catTipocoloreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(catTipocoloreDTO);
    }

    /**
     * DELETE  /cat-tipocolores/:id : delete the "id" catTipocolore.
     *
     * @param id the id of the catTipocoloreDTO to delete
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
