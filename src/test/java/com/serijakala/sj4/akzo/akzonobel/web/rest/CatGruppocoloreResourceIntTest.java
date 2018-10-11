package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.serijakala.sj4.akzo.akzonobel.AkzoJhApplicationApp;

import com.serijakala.sj4.akzo.akzonobel.domain.CatGruppocolore;
import com.serijakala.sj4.akzo.akzonobel.repository.CatGruppocoloreRepository;
import com.serijakala.sj4.akzo.akzonobel.service.CatGruppocoloreService;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatGruppocoloreDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.CatGruppocoloreMapper;
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
 * Test class for the CatGruppocoloreResource REST controller.
 *
 * @see CatGruppocoloreResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkzoJhApplicationApp.class)
public class CatGruppocoloreResourceIntTest {

    private static final String DEFAULT_DESCRIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE = "BBBBBBBBBB";

    @Autowired
    private CatGruppocoloreRepository catGruppocoloreRepository;

    @Autowired
    private CatGruppocoloreMapper catGruppocoloreMapper;
    
    @Autowired
    private CatGruppocoloreService catGruppocoloreService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCatGruppocoloreMockMvc;

    private CatGruppocolore catGruppocolore;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CatGruppocoloreResource catGruppocoloreResource = new CatGruppocoloreResource(catGruppocoloreService);
        this.restCatGruppocoloreMockMvc = MockMvcBuilders.standaloneSetup(catGruppocoloreResource)
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
    public static CatGruppocolore createEntity(EntityManager em) {
        CatGruppocolore catGruppocolore = new CatGruppocolore()
            .descrizione(DEFAULT_DESCRIZIONE);
        return catGruppocolore;
    }

    @Before
    public void initTest() {
        catGruppocolore = createEntity(em);
    }

    @Test
    @Transactional
    public void createCatGruppocolore() throws Exception {
        int databaseSizeBeforeCreate = catGruppocoloreRepository.findAll().size();

        // Create the CatGruppocolore
        CatGruppocoloreDTO catGruppocoloreDTO = catGruppocoloreMapper.toDto(catGruppocolore);
        restCatGruppocoloreMockMvc.perform(post("/api/cat-gruppocolores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catGruppocoloreDTO)))
            .andExpect(status().isCreated());

        // Validate the CatGruppocolore in the database
        List<CatGruppocolore> catGruppocoloreList = catGruppocoloreRepository.findAll();
        assertThat(catGruppocoloreList).hasSize(databaseSizeBeforeCreate + 1);
        CatGruppocolore testCatGruppocolore = catGruppocoloreList.get(catGruppocoloreList.size() - 1);
        assertThat(testCatGruppocolore.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
    }

    @Test
    @Transactional
    public void createCatGruppocoloreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = catGruppocoloreRepository.findAll().size();

        // Create the CatGruppocolore with an existing ID
        catGruppocolore.setId(1L);
        CatGruppocoloreDTO catGruppocoloreDTO = catGruppocoloreMapper.toDto(catGruppocolore);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCatGruppocoloreMockMvc.perform(post("/api/cat-gruppocolores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catGruppocoloreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CatGruppocolore in the database
        List<CatGruppocolore> catGruppocoloreList = catGruppocoloreRepository.findAll();
        assertThat(catGruppocoloreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCatGruppocolores() throws Exception {
        // Initialize the database
        catGruppocoloreRepository.saveAndFlush(catGruppocolore);

        // Get all the catGruppocoloreList
        restCatGruppocoloreMockMvc.perform(get("/api/cat-gruppocolores?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(catGruppocolore.getId().intValue())))
            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE.toString())));
    }
    
    @Test
    @Transactional
    public void getCatGruppocolore() throws Exception {
        // Initialize the database
        catGruppocoloreRepository.saveAndFlush(catGruppocolore);

        // Get the catGruppocolore
        restCatGruppocoloreMockMvc.perform(get("/api/cat-gruppocolores/{id}", catGruppocolore.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(catGruppocolore.getId().intValue()))
            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCatGruppocolore() throws Exception {
        // Get the catGruppocolore
        restCatGruppocoloreMockMvc.perform(get("/api/cat-gruppocolores/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCatGruppocolore() throws Exception {
        // Initialize the database
        catGruppocoloreRepository.saveAndFlush(catGruppocolore);

        int databaseSizeBeforeUpdate = catGruppocoloreRepository.findAll().size();

        // Update the catGruppocolore
        CatGruppocolore updatedCatGruppocolore = catGruppocoloreRepository.findById(catGruppocolore.getId()).get();
        // Disconnect from session so that the updates on updatedCatGruppocolore are not directly saved in db
        em.detach(updatedCatGruppocolore);
        updatedCatGruppocolore
            .descrizione(UPDATED_DESCRIZIONE);
        CatGruppocoloreDTO catGruppocoloreDTO = catGruppocoloreMapper.toDto(updatedCatGruppocolore);

        restCatGruppocoloreMockMvc.perform(put("/api/cat-gruppocolores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catGruppocoloreDTO)))
            .andExpect(status().isOk());

        // Validate the CatGruppocolore in the database
        List<CatGruppocolore> catGruppocoloreList = catGruppocoloreRepository.findAll();
        assertThat(catGruppocoloreList).hasSize(databaseSizeBeforeUpdate);
        CatGruppocolore testCatGruppocolore = catGruppocoloreList.get(catGruppocoloreList.size() - 1);
        assertThat(testCatGruppocolore.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
    }

    @Test
    @Transactional
    public void updateNonExistingCatGruppocolore() throws Exception {
        int databaseSizeBeforeUpdate = catGruppocoloreRepository.findAll().size();

        // Create the CatGruppocolore
        CatGruppocoloreDTO catGruppocoloreDTO = catGruppocoloreMapper.toDto(catGruppocolore);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCatGruppocoloreMockMvc.perform(put("/api/cat-gruppocolores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catGruppocoloreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CatGruppocolore in the database
        List<CatGruppocolore> catGruppocoloreList = catGruppocoloreRepository.findAll();
        assertThat(catGruppocoloreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCatGruppocolore() throws Exception {
        // Initialize the database
        catGruppocoloreRepository.saveAndFlush(catGruppocolore);

        int databaseSizeBeforeDelete = catGruppocoloreRepository.findAll().size();

        // Get the catGruppocolore
        restCatGruppocoloreMockMvc.perform(delete("/api/cat-gruppocolores/{id}", catGruppocolore.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CatGruppocolore> catGruppocoloreList = catGruppocoloreRepository.findAll();
        assertThat(catGruppocoloreList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatGruppocolore.class);
        CatGruppocolore catGruppocolore1 = new CatGruppocolore();
        catGruppocolore1.setId(1L);
        CatGruppocolore catGruppocolore2 = new CatGruppocolore();
        catGruppocolore2.setId(catGruppocolore1.getId());
        assertThat(catGruppocolore1).isEqualTo(catGruppocolore2);
        catGruppocolore2.setId(2L);
        assertThat(catGruppocolore1).isNotEqualTo(catGruppocolore2);
        catGruppocolore1.setId(null);
        assertThat(catGruppocolore1).isNotEqualTo(catGruppocolore2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatGruppocoloreDTO.class);
        CatGruppocoloreDTO catGruppocoloreDTO1 = new CatGruppocoloreDTO();
        catGruppocoloreDTO1.setId(1L);
        CatGruppocoloreDTO catGruppocoloreDTO2 = new CatGruppocoloreDTO();
        assertThat(catGruppocoloreDTO1).isNotEqualTo(catGruppocoloreDTO2);
        catGruppocoloreDTO2.setId(catGruppocoloreDTO1.getId());
        assertThat(catGruppocoloreDTO1).isEqualTo(catGruppocoloreDTO2);
        catGruppocoloreDTO2.setId(2L);
        assertThat(catGruppocoloreDTO1).isNotEqualTo(catGruppocoloreDTO2);
        catGruppocoloreDTO1.setId(null);
        assertThat(catGruppocoloreDTO1).isNotEqualTo(catGruppocoloreDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(catGruppocoloreMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(catGruppocoloreMapper.fromId(null)).isNull();
    }
}
