package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.serijakala.sj4.akzo.akzonobel.service.ComuneService;
import com.serijakala.sj4.akzo.akzonobel.web.rest.errors.BadRequestAlertException;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.HeaderUtil;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.PaginationUtil;
import com.serijakala.sj4.akzo.akzonobel.service.dto.ComuneDTO;
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
     * @param comuneDTO the comuneDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new comuneDTO, or with status 400 (Bad Request) if the comune has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/comunes")
    @Timed
    public ResponseEntity<ComuneDTO> createComune(@RequestBody ComuneDTO comuneDTO) throws URISyntaxException {
        log.debug("REST request to save Comune : {}", comuneDTO);
        if (comuneDTO.getId() != null) {
            throw new BadRequestAlertException("A new comune cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComuneDTO result = comuneService.save(comuneDTO);
        return ResponseEntity.created(new URI("/api/comunes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /comunes : Updates an existing comune.
     *
     * @param comuneDTO the comuneDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated comuneDTO,
     * or with status 400 (Bad Request) if the comuneDTO is not valid,
     * or with status 500 (Internal Server Error) if the comuneDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/comunes")
    @Timed
    public ResponseEntity<ComuneDTO> updateComune(@RequestBody ComuneDTO comuneDTO) throws URISyntaxException {
        log.debug("REST request to update Comune : {}", comuneDTO);
        if (comuneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComuneDTO result = comuneService.save(comuneDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, comuneDTO.getId().toString()))
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
    public ResponseEntity<List<ComuneDTO>> getAllComunes(Pageable pageable) {
        log.debug("REST request to get a page of Comunes");
        Page<ComuneDTO> page = comuneService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/comunes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /comunes/:id : get the "id" comune.
     *
     * @param id the id of the comuneDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the comuneDTO, or with status 404 (Not Found)
     */
    @GetMapping("/comunes/{id}")
    @Timed
    public ResponseEntity<ComuneDTO> getComune(@PathVariable Long id) {
        log.debug("REST request to get Comune : {}", id);
        Optional<ComuneDTO> comuneDTO = comuneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(comuneDTO);
    }

    /**
     * DELETE  /comunes/:id : delete the "id" comune.
     *
     * @param id the id of the comuneDTO to delete
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
