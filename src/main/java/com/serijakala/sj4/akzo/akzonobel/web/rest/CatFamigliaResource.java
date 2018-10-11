package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.serijakala.sj4.akzo.akzonobel.service.CatFamigliaService;
import com.serijakala.sj4.akzo.akzonobel.web.rest.errors.BadRequestAlertException;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.HeaderUtil;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.PaginationUtil;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatFamigliaDTO;
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
 * REST controller for managing CatFamiglia.
 */
@RestController
@RequestMapping("/api")
public class CatFamigliaResource {

    private final Logger log = LoggerFactory.getLogger(CatFamigliaResource.class);

    private static final String ENTITY_NAME = "catFamiglia";

    private final CatFamigliaService catFamigliaService;

    public CatFamigliaResource(CatFamigliaService catFamigliaService) {
        this.catFamigliaService = catFamigliaService;
    }

    /**
     * POST  /cat-famiglias : Create a new catFamiglia.
     *
     * @param catFamigliaDTO the catFamigliaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new catFamigliaDTO, or with status 400 (Bad Request) if the catFamiglia has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cat-famiglias")
    @Timed
    public ResponseEntity<CatFamigliaDTO> createCatFamiglia(@RequestBody CatFamigliaDTO catFamigliaDTO) throws URISyntaxException {
        log.debug("REST request to save CatFamiglia : {}", catFamigliaDTO);
        if (catFamigliaDTO.getId() != null) {
            throw new BadRequestAlertException("A new catFamiglia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CatFamigliaDTO result = catFamigliaService.save(catFamigliaDTO);
        return ResponseEntity.created(new URI("/api/cat-famiglias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cat-famiglias : Updates an existing catFamiglia.
     *
     * @param catFamigliaDTO the catFamigliaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated catFamigliaDTO,
     * or with status 400 (Bad Request) if the catFamigliaDTO is not valid,
     * or with status 500 (Internal Server Error) if the catFamigliaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cat-famiglias")
    @Timed
    public ResponseEntity<CatFamigliaDTO> updateCatFamiglia(@RequestBody CatFamigliaDTO catFamigliaDTO) throws URISyntaxException {
        log.debug("REST request to update CatFamiglia : {}", catFamigliaDTO);
        if (catFamigliaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CatFamigliaDTO result = catFamigliaService.save(catFamigliaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, catFamigliaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cat-famiglias : get all the catFamiglias.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of catFamiglias in body
     */
    @GetMapping("/cat-famiglias")
    @Timed
    public ResponseEntity<List<CatFamigliaDTO>> getAllCatFamiglias(Pageable pageable) {
        log.debug("REST request to get a page of CatFamiglias");
        Page<CatFamigliaDTO> page = catFamigliaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cat-famiglias");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cat-famiglias/:id : get the "id" catFamiglia.
     *
     * @param id the id of the catFamigliaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the catFamigliaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cat-famiglias/{id}")
    @Timed
    public ResponseEntity<CatFamigliaDTO> getCatFamiglia(@PathVariable Long id) {
        log.debug("REST request to get CatFamiglia : {}", id);
        Optional<CatFamigliaDTO> catFamigliaDTO = catFamigliaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(catFamigliaDTO);
    }

    /**
     * DELETE  /cat-famiglias/:id : delete the "id" catFamiglia.
     *
     * @param id the id of the catFamigliaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cat-famiglias/{id}")
    @Timed
    public ResponseEntity<Void> deleteCatFamiglia(@PathVariable Long id) {
        log.debug("REST request to delete CatFamiglia : {}", id);
        catFamigliaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
