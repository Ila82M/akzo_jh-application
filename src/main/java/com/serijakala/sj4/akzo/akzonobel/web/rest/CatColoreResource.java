package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.serijakala.sj4.akzo.akzonobel.service.CatColoreService;
import com.serijakala.sj4.akzo.akzonobel.web.rest.errors.BadRequestAlertException;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.HeaderUtil;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.PaginationUtil;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatColoreDTO;
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
 * REST controller for managing CatColore.
 */
@RestController
@RequestMapping("/api")
public class CatColoreResource {

    private final Logger log = LoggerFactory.getLogger(CatColoreResource.class);

    private static final String ENTITY_NAME = "catColore";

    private final CatColoreService catColoreService;

    public CatColoreResource(CatColoreService catColoreService) {
        this.catColoreService = catColoreService;
    }

    /**
     * POST  /cat-colores : Create a new catColore.
     *
     * @param catColoreDTO the catColoreDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new catColoreDTO, or with status 400 (Bad Request) if the catColore has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cat-colores")
    @Timed
    public ResponseEntity<CatColoreDTO> createCatColore(@RequestBody CatColoreDTO catColoreDTO) throws URISyntaxException {
        log.debug("REST request to save CatColore : {}", catColoreDTO);
        if (catColoreDTO.getId() != null) {
            throw new BadRequestAlertException("A new catColore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CatColoreDTO result = catColoreService.save(catColoreDTO);
        return ResponseEntity.created(new URI("/api/cat-colores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cat-colores : Updates an existing catColore.
     *
     * @param catColoreDTO the catColoreDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated catColoreDTO,
     * or with status 400 (Bad Request) if the catColoreDTO is not valid,
     * or with status 500 (Internal Server Error) if the catColoreDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cat-colores")
    @Timed
    public ResponseEntity<CatColoreDTO> updateCatColore(@RequestBody CatColoreDTO catColoreDTO) throws URISyntaxException {
        log.debug("REST request to update CatColore : {}", catColoreDTO);
        if (catColoreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CatColoreDTO result = catColoreService.save(catColoreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, catColoreDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cat-colores : get all the catColores.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of catColores in body
     */
    @GetMapping("/cat-colores")
    @Timed
    public ResponseEntity<List<CatColoreDTO>> getAllCatColores(Pageable pageable) {
        log.debug("REST request to get a page of CatColores");
        Page<CatColoreDTO> page = catColoreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cat-colores");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cat-colores/:id : get the "id" catColore.
     *
     * @param id the id of the catColoreDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the catColoreDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cat-colores/{id}")
    @Timed
    public ResponseEntity<CatColoreDTO> getCatColore(@PathVariable Long id) {
        log.debug("REST request to get CatColore : {}", id);
        Optional<CatColoreDTO> catColoreDTO = catColoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(catColoreDTO);
    }

    /**
     * DELETE  /cat-colores/:id : delete the "id" catColore.
     *
     * @param id the id of the catColoreDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cat-colores/{id}")
    @Timed
    public ResponseEntity<Void> deleteCatColore(@PathVariable Long id) {
        log.debug("REST request to delete CatColore : {}", id);
        catColoreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
