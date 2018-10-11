package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.serijakala.sj4.akzo.akzonobel.service.CatLattinaService;
import com.serijakala.sj4.akzo.akzonobel.web.rest.errors.BadRequestAlertException;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.HeaderUtil;
import com.serijakala.sj4.akzo.akzonobel.web.rest.util.PaginationUtil;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatLattinaDTO;
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
     * @param catLattinaDTO the catLattinaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new catLattinaDTO, or with status 400 (Bad Request) if the catLattina has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cat-lattinas")
    @Timed
    public ResponseEntity<CatLattinaDTO> createCatLattina(@RequestBody CatLattinaDTO catLattinaDTO) throws URISyntaxException {
        log.debug("REST request to save CatLattina : {}", catLattinaDTO);
        if (catLattinaDTO.getId() != null) {
            throw new BadRequestAlertException("A new catLattina cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CatLattinaDTO result = catLattinaService.save(catLattinaDTO);
        return ResponseEntity.created(new URI("/api/cat-lattinas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cat-lattinas : Updates an existing catLattina.
     *
     * @param catLattinaDTO the catLattinaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated catLattinaDTO,
     * or with status 400 (Bad Request) if the catLattinaDTO is not valid,
     * or with status 500 (Internal Server Error) if the catLattinaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cat-lattinas")
    @Timed
    public ResponseEntity<CatLattinaDTO> updateCatLattina(@RequestBody CatLattinaDTO catLattinaDTO) throws URISyntaxException {
        log.debug("REST request to update CatLattina : {}", catLattinaDTO);
        if (catLattinaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CatLattinaDTO result = catLattinaService.save(catLattinaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, catLattinaDTO.getId().toString()))
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
    public ResponseEntity<List<CatLattinaDTO>> getAllCatLattinas(Pageable pageable) {
        log.debug("REST request to get a page of CatLattinas");
        Page<CatLattinaDTO> page = catLattinaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cat-lattinas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cat-lattinas/:id : get the "id" catLattina.
     *
     * @param id the id of the catLattinaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the catLattinaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cat-lattinas/{id}")
    @Timed
    public ResponseEntity<CatLattinaDTO> getCatLattina(@PathVariable Long id) {
        log.debug("REST request to get CatLattina : {}", id);
        Optional<CatLattinaDTO> catLattinaDTO = catLattinaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(catLattinaDTO);
    }

    /**
     * DELETE  /cat-lattinas/:id : delete the "id" catLattina.
     *
     * @param id the id of the catLattinaDTO to delete
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
