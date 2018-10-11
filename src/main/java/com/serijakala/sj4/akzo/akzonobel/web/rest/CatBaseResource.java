package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.serijakala.sj4.akzo.akzonobel.service.CatBaseService;
import com.serijakala.sj4.akzo.akzonobel.web.rest.errors.BadRequestAlertException;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.HeaderUtil;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.PaginationUtil;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatBaseDTO;
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
 * REST controller for managing CatBase.
 */
@RestController
@RequestMapping("/api")
public class CatBaseResource {

    private final Logger log = LoggerFactory.getLogger(CatBaseResource.class);

    private static final String ENTITY_NAME = "catBase";

    private final CatBaseService catBaseService;

    public CatBaseResource(CatBaseService catBaseService) {
        this.catBaseService = catBaseService;
    }

    /**
     * POST  /cat-bases : Create a new catBase.
     *
     * @param catBaseDTO the catBaseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new catBaseDTO, or with status 400 (Bad Request) if the catBase has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cat-bases")
    @Timed
    public ResponseEntity<CatBaseDTO> createCatBase(@RequestBody CatBaseDTO catBaseDTO) throws URISyntaxException {
        log.debug("REST request to save CatBase : {}", catBaseDTO);
        if (catBaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new catBase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CatBaseDTO result = catBaseService.save(catBaseDTO);
        return ResponseEntity.created(new URI("/api/cat-bases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cat-bases : Updates an existing catBase.
     *
     * @param catBaseDTO the catBaseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated catBaseDTO,
     * or with status 400 (Bad Request) if the catBaseDTO is not valid,
     * or with status 500 (Internal Server Error) if the catBaseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cat-bases")
    @Timed
    public ResponseEntity<CatBaseDTO> updateCatBase(@RequestBody CatBaseDTO catBaseDTO) throws URISyntaxException {
        log.debug("REST request to update CatBase : {}", catBaseDTO);
        if (catBaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CatBaseDTO result = catBaseService.save(catBaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, catBaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cat-bases : get all the catBases.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of catBases in body
     */
    @GetMapping("/cat-bases")
    @Timed
    public ResponseEntity<List<CatBaseDTO>> getAllCatBases(Pageable pageable) {
        log.debug("REST request to get a page of CatBases");
        Page<CatBaseDTO> page = catBaseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cat-bases");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cat-bases/:id : get the "id" catBase.
     *
     * @param id the id of the catBaseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the catBaseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cat-bases/{id}")
    @Timed
    public ResponseEntity<CatBaseDTO> getCatBase(@PathVariable Long id) {
        log.debug("REST request to get CatBase : {}", id);
        Optional<CatBaseDTO> catBaseDTO = catBaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(catBaseDTO);
    }

    /**
     * DELETE  /cat-bases/:id : delete the "id" catBase.
     *
     * @param id the id of the catBaseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cat-bases/{id}")
    @Timed
    public ResponseEntity<Void> deleteCatBase(@PathVariable Long id) {
        log.debug("REST request to delete CatBase : {}", id);
        catBaseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
