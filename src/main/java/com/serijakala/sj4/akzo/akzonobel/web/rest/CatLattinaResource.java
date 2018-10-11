package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.serijakala.sj4.akzo.akzonobel.domain.CatLattina;
import com.serijakala.sj4.akzo.akzonobel.service.CatLattinaService;
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
 * REST controller for managing CatLattina.
 */
@RestController
@RequestMapping("/api")
public class CatLattinaResource {

    private final Logger log = LoggerFactory.getLogger(CatLattinaResource.class);

    private static final String ENTITY_NAME = "catLattina";

    private final CatLattinaService catLattinaService;

    public CatLattinaResource(CatLattinaService catLattinaService) {
        this.catLattinaService = catLattinaService;
    }

    /**
     * POST  /cat-lattinas : Create a new catLattina.
     *
     * @param catLattina the catLattina to create
     * @return the ResponseEntity with status 201 (Created) and with body the new catLattina, or with status 400 (Bad Request) if the catLattina has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cat-lattinas")
    @Timed
    public ResponseEntity<CatLattina> createCatLattina(@RequestBody CatLattina catLattina) throws URISyntaxException {
        log.debug("REST request to save CatLattina : {}", catLattina);
        if (catLattina.getId() != null) {
            throw new BadRequestAlertException("A new catLattina cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CatLattina result = catLattinaService.save(catLattina);
        return ResponseEntity.created(new URI("/api/cat-lattinas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cat-lattinas : Updates an existing catLattina.
     *
     * @param catLattina the catLattina to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated catLattina,
     * or with status 400 (Bad Request) if the catLattina is not valid,
     * or with status 500 (Internal Server Error) if the catLattina couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cat-lattinas")
    @Timed
    public ResponseEntity<CatLattina> updateCatLattina(@RequestBody CatLattina catLattina) throws URISyntaxException {
        log.debug("REST request to update CatLattina : {}", catLattina);
        if (catLattina.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CatLattina result = catLattinaService.save(catLattina);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, catLattina.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cat-lattinas : get all the catLattinas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of catLattinas in body
     */
    @GetMapping("/cat-lattinas")
    @Timed
    public ResponseEntity<List<CatLattina>> getAllCatLattinas(Pageable pageable) {
        log.debug("REST request to get a page of CatLattinas");
        Page<CatLattina> page = catLattinaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cat-lattinas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cat-lattinas/:id : get the "id" catLattina.
     *
     * @param id the id of the catLattina to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the catLattina, or with status 404 (Not Found)
     */
    @GetMapping("/cat-lattinas/{id}")
    @Timed
    public ResponseEntity<CatLattina> getCatLattina(@PathVariable Long id) {
        log.debug("REST request to get CatLattina : {}", id);
        Optional<CatLattina> catLattina = catLattinaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(catLattina);
    }

    /**
     * DELETE  /cat-lattinas/:id : delete the "id" catLattina.
     *
     * @param id the id of the catLattina to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cat-lattinas/{id}")
    @Timed
    public ResponseEntity<Void> deleteCatLattina(@PathVariable Long id) {
        log.debug("REST request to delete CatLattina : {}", id);
        catLattinaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
