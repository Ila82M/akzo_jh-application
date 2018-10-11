package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.serijakala.sj4.akzo.akzonobel.domain.Comune;
import com.serijakala.sj4.akzo.akzonobel.service.ComuneService;
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
 * REST controller for managing Comune.
 */
@RestController
@RequestMapping("/api")
public class ComuneResource {

    private final Logger log = LoggerFactory.getLogger(ComuneResource.class);

    private static final String ENTITY_NAME = "comune";

    private final ComuneService comuneService;

    public ComuneResource(ComuneService comuneService) {
        this.comuneService = comuneService;
    }

    /**
     * POST  /comunes : Create a new comune.
     *
     * @param comune the comune to create
     * @return the ResponseEntity with status 201 (Created) and with body the new comune, or with status 400 (Bad Request) if the comune has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/comunes")
    @Timed
    public ResponseEntity<Comune> createComune(@RequestBody Comune comune) throws URISyntaxException {
        log.debug("REST request to save Comune : {}", comune);
        if (comune.getId() != null) {
            throw new BadRequestAlertException("A new comune cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Comune result = comuneService.save(comune);
        return ResponseEntity.created(new URI("/api/comunes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /comunes : Updates an existing comune.
     *
     * @param comune the comune to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated comune,
     * or with status 400 (Bad Request) if the comune is not valid,
     * or with status 500 (Internal Server Error) if the comune couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/comunes")
    @Timed
    public ResponseEntity<Comune> updateComune(@RequestBody Comune comune) throws URISyntaxException {
        log.debug("REST request to update Comune : {}", comune);
        if (comune.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Comune result = comuneService.save(comune);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, comune.getId().toString()))
            .body(result);
    }

    /**
     * GET  /comunes : get all the comunes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of comunes in body
     */
    @GetMapping("/comunes")
    @Timed
    public ResponseEntity<List<Comune>> getAllComunes(Pageable pageable) {
        log.debug("REST request to get a page of Comunes");
        Page<Comune> page = comuneService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/comunes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /comunes/:id : get the "id" comune.
     *
     * @param id the id of the comune to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the comune, or with status 404 (Not Found)
     */
    @GetMapping("/comunes/{id}")
    @Timed
    public ResponseEntity<Comune> getComune(@PathVariable Long id) {
        log.debug("REST request to get Comune : {}", id);
        Optional<Comune> comune = comuneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(comune);
    }

    /**
     * DELETE  /comunes/:id : delete the "id" comune.
     *
     * @param id the id of the comune to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/comunes/{id}")
    @Timed
    public ResponseEntity<Void> deleteComune(@PathVariable Long id) {
        log.debug("REST request to delete Comune : {}", id);
        comuneService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
