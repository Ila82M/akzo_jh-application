package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.serijakala.sj4.akzo.akzonobel.AkzoJhApplicationApp;

import com.serijakala.sj4.akzo.akzonobel.domain.CatColore;
import com.serijakala.sj4.akzo.akzonobel.repository.CatColoreRepository;
import com.serijakala.sj4.akzo.akzonobel.service.CatColoreService;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatColoreDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.CatColoreMapper;
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
 * Test class for the CatColoreResource REST controller.
 *
 * @see CatColoreResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkzoJhApplicationApp.class)
public class CatColoreResourceIntTest {

    private static final String DEFAULT_DESCRIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE = "BBBBBBBBBB";

    @Autowired
    private CatColoreRepository catColoreRepository;

    @Autowired
    private CatColoreMapper catColoreMapper;
    
    @Autowired
    private CatColoreService catColoreService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCatColoreMockMvc;

    private CatColore catColore;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CatColoreResource catColoreResource = new CatColoreResource(catColoreService);
        this.restCatColoreMockMvc = MockMvcBuilders.standaloneSetup(catColoreResource)
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
    public static CatColore createEntity(EntityManager em) {
        CatColore catColore = new CatColore()
            .descrizione(DEFAULT_DESCRIZIONE);
        return catColore;
    }

    @Before
    public void initTest() {
        catColore = createEntity(em);
    }

    @Test
    @Transactional
    public void createCatColore() throws Exception {
        int databaseSizeBeforeCreate = catColoreRepository.findAll().size();

        // Create the CatColore
        CatColoreDTO catColoreDTO = catColoreMapper.toDto(catColore);
        restCatColoreMockMvc.perform(post("/api/cat-colores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catColoreDTO)))
            .andExpect(status().isCreated());

        // Validate the CatColore in the database
        List<CatColore> catColoreList = catColoreRepository.findAll();
        assertThat(catColoreList).hasSize(databaseSizeBeforeCreate + 1);
        CatColore testCatColore = catColoreList.get(catColoreList.size() - 1);
        assertThat(testCatColore.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
    }

    @Test
    @Transactional
    public void createCatColoreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = catColoreRepository.findAll().size();

        // Create the CatColore with an existing ID
        catColore.setId(1L);
        CatColoreDTO catColoreDTO = catColoreMapper.toDto(catColore);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCatColoreMockMvc.perform(post("/api/cat-colores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catColoreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CatColore in the database
        List<CatColore> catColoreList = catColoreRepository.findAll();
        assertThat(catColoreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCatColores() throws Exception {
        // Initialize the database
        catColoreRepository.saveAndFlush(catColore);

        // Get all the catColoreList
        restCatColoreMockMvc.perform(get("/api/cat-colores?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(catColore.getId().intValue())))
            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE.toString())));
    }
    
    @Test
    @Transactional
    public void getCatColore() throws Exception {
        // Initialize the database
        catColoreRepository.saveAndFlush(catColore);

        // Get the catColore
        restCatColoreMockMvc.perform(get("/api/cat-colores/{id}", catColore.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(catColore.getId().intValue()))
            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCatColore() throws Exception {
        // Get the catColore
        restCatColoreMockMvc.perform(get("/api/cat-colores/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCatColore() throws Exception {
        // Initialize the database
        catColoreRepository.saveAndFlush(catColore);

        int databaseSizeBeforeUpdate = catColoreRepository.findAll().size();

        // Update the catColore
        CatColore updatedCatColore = catColoreRepository.findById(catColore.getId()).get();
        // Disconnect from session so that the updates on updatedCatColore are not directly saved in db
        em.detach(updatedCatColore);
        updatedCatColore
            .descrizione(UPDATED_DESCRIZIONE);
        CatColoreDTO catColoreDTO = catColoreMapper.toDto(updatedCatColore);

        restCatColoreMockMvc.perform(put("/api/cat-colores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catColoreDTO)))
            .andExpect(status().isOk());

        // Validate the CatColore in the database
        List<CatColore> catColoreList = catColoreRepository.findAll();
        assertThat(catColoreList).hasSize(databaseSizeBeforeUpdate);
        CatColore testCatColore = catColoreList.get(catColoreList.size() - 1);
        assertThat(testCatColore.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
    }

    @Test
    @Transactional
    public void updateNonExistingCatColore() throws Exception {
        int databaseSizeBeforeUpdate = catColoreRepository.findAll().size();

        // Create the CatColore
        CatColoreDTO catColoreDTO = catColoreMapper.toDto(catColore);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCatColoreMockMvc.perform(put("/api/cat-colores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catColoreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CatColore in the database
        List<CatColore> catColoreList = catColoreRepository.findAll();
        assertThat(catColoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCatColore() throws Exception {
        // Initialize the database
        catColoreRepository.saveAndFlush(catColore);

        int databaseSizeBeforeDelete = catColoreRepository.findAll().size();

        // Get the catColore
        restCatColoreMockMvc.perform(delete("/api/cat-colores/{id}", catColore.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CatColore> catColoreList = catColoreRepository.findAll();
        assertThat(catColoreList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatColore.class);
        CatColore catColore1 = new CatColore();
        catColore1.setId(1L);
        CatColore catColore2 = new CatColore();
        catColore2.setId(catColore1.getId());
        assertThat(catColore1).isEqualTo(catColore2);
        catColore2.setId(2L);
        assertThat(catColore1).isNotEqualTo(catColore2);
        catColore1.setId(null);
        assertThat(catColore1).isNotEqualTo(catColore2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatColoreDTO.class);
        CatColoreDTO catColoreDTO1 = new CatColoreDTO();
        catColoreDTO1.setId(1L);
        CatColoreDTO catColoreDTO2 = new CatColoreDTO();
        assertThat(catColoreDTO1).isNotEqualTo(catColoreDTO2);
        catColoreDTO2.setId(catColoreDTO1.getId());
        assertThat(catColoreDTO1).isEqualTo(catColoreDTO2);
        catColoreDTO2.setId(2L);
        assertThat(catColoreDTO1).isNotEqualTo(catColoreDTO2);
        catColoreDTO1.setId(null);
        assertThat(catColoreDTO1).isNotEqualTo(catColoreDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(catColoreMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(catColoreMapper.fromId(null)).isNull();
    }
}
