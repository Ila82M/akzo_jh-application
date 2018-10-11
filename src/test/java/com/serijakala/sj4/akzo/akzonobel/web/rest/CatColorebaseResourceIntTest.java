package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.serijakala.sj4.akzo.akzonobel.AkzoJhApplicationApp;

import com.serijakala.sj4.akzo.akzonobel.domain.CatColorebase;
import com.serijakala.sj4.akzo.akzonobel.repository.CatColorebaseRepository;
import com.serijakala.sj4.akzo.akzonobel.service.CatColorebaseService;
import com.serijakala.sj4.akzo.akzonobel.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.serijakala.sj4.akzo.akzonobel.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CatColorebaseResource REST controller.
 *
 * @see CatColorebaseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkzoJhApplicationApp.class)
public class CatColorebaseResourceIntTest {

    private static final Long DEFAULT_RESA = 1L;
    private static final Long UPDATED_RESA = 2L;

    @Autowired
    private CatColorebaseRepository catColorebaseRepository;
    
    @Autowired
    private CatColorebaseService catColorebaseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCatColorebaseMockMvc;

    private CatColorebase catColorebase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CatColorebaseResource catColorebaseResource = new CatColorebaseResource(catColorebaseService);
        this.restCatColorebaseMockMvc = MockMvcBuilders.standaloneSetup(catColorebaseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CatColorebase createEntity(EntityManager em) {
        CatColorebase catColorebase = new CatColorebase()
            .resa(DEFAULT_RESA);
        return catColorebase;
    }

    @Before
    public void initTest() {
        catColorebase = createEntity(em);
    }

    @Test
    @Transactional
    public void createCatColorebase() throws Exception {
        int databaseSizeBeforeCreate = catColorebaseRepository.findAll().size();

        // Create the CatColorebase
        restCatColorebaseMockMvc.perform(post("/api/cat-colorebases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catColorebase)))
            .andExpect(status().isCreated());

        // Validate the CatColorebase in the database
        List<CatColorebase> catColorebaseList = catColorebaseRepository.findAll();
        assertThat(catColorebaseList).hasSize(databaseSizeBeforeCreate + 1);
        CatColorebase testCatColorebase = catColorebaseList.get(catColorebaseList.size() - 1);
        assertThat(testCatColorebase.getResa()).isEqualTo(DEFAULT_RESA);
    }

    @Test
    @Transactional
    public void createCatColorebaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = catColorebaseRepository.findAll().size();

        // Create the CatColorebase with an existing ID
        catColorebase.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCatColorebaseMockMvc.perform(post("/api/cat-colorebases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catColorebase)))
            .andExpect(status().isBadRequest());

        // Validate the CatColorebase in the database
        List<CatColorebase> catColorebaseList = catColorebaseRepository.findAll();
        assertThat(catColorebaseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCatColorebases() throws Exception {
        // Initialize the database
        catColorebaseRepository.saveAndFlush(catColorebase);

        // Get all the catColorebaseList
        restCatColorebaseMockMvc.perform(get("/api/cat-colorebases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(catColorebase.getId().intValue())))
            .andExpect(jsonPath("$.[*].resa").value(hasItem(DEFAULT_RESA.intValue())));
    }
    
    @Test
    @Transactional
    public void getCatColorebase() throws Exception {
        // Initialize the database
        catColorebaseRepository.saveAndFlush(catColorebase);

        // Get the catColorebase
        restCatColorebaseMockMvc.perform(get("/api/cat-colorebases/{id}", catColorebase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(catColorebase.getId().intValue()))
            .andExpect(jsonPath("$.resa").value(DEFAULT_RESA.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCatColorebase() throws Exception {
        // Get the catColorebase
        restCatColorebaseMockMvc.perform(get("/api/cat-colorebases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCatColorebase() throws Exception {
        // Initialize the database
        catColorebaseService.save(catColorebase);

        int databaseSizeBeforeUpdate = catColorebaseRepository.findAll().size();

        // Update the catColorebase
        CatColorebase updatedCatColorebase = catColorebaseRepository.findById(catColorebase.getId()).get();
        // Disconnect from session so that the updates on updatedCatColorebase are not directly saved in db
        em.detach(updatedCatColorebase);
        updatedCatColorebase
            .resa(UPDATED_RESA);

        restCatColorebaseMockMvc.perform(put("/api/cat-colorebases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCatColorebase)))
            .andExpect(status().isOk());

        // Validate the CatColorebase in the database
        List<CatColorebase> catColorebaseList = catColorebaseRepository.findAll();
        assertThat(catColorebaseList).hasSize(databaseSizeBeforeUpdate);
        CatColorebase testCatColorebase = catColorebaseList.get(catColorebaseList.size() - 1);
        assertThat(testCatColorebase.getResa()).isEqualTo(UPDATED_RESA);
    }

    @Test
    @Transactional
    public void updateNonExistingCatColorebase() throws Exception {
        int databaseSizeBeforeUpdate = catColorebaseRepository.findAll().size();

        // Create the CatColorebase

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCatColorebaseMockMvc.perform(put("/api/cat-colorebases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catColorebase)))
            .andExpect(status().isBadRequest());

        // Validate the CatColorebase in the database
        List<CatColorebase> catColorebaseList = catColorebaseRepository.findAll();
        assertThat(catColorebaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCatColorebase() throws Exception {
        // Initialize the database
        catColorebaseService.save(catColorebase);

        int databaseSizeBeforeDelete = catColorebaseRepository.findAll().size();

        // Get the catColorebase
        restCatColorebaseMockMvc.perform(delete("/api/cat-colorebases/{id}", catColorebase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CatColorebase> catColorebaseList = catColorebaseRepository.findAll();
        assertThat(catColorebaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatColorebase.class);
        CatColorebase catColorebase1 = new CatColorebase();
        catColorebase1.setId(1L);
        CatColorebase catColorebase2 = new CatColorebase();
        catColorebase2.setId(catColorebase1.getId());
        assertThat(catColorebase1).isEqualTo(catColorebase2);
        catColorebase2.setId(2L);
        assertThat(catColorebase1).isNotEqualTo(catColorebase2);
        catColorebase1.setId(null);
        assertThat(catColorebase1).isNotEqualTo(catColorebase2);
    }
}
