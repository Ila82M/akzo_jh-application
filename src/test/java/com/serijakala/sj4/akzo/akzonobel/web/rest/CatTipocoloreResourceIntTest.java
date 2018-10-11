package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.serijakala.sj4.akzo.akzonobel.AkzoJhApplicationApp;

import com.serijakala.sj4.akzo.akzonobel.domain.CatTipocolore;
import com.serijakala.sj4.akzo.akzonobel.repository.CatTipocoloreRepository;
import com.serijakala.sj4.akzo.akzonobel.service.CatTipocoloreService;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatTipocoloreDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.CatTipocoloreMapper;
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
 * Test class for the CatTipocoloreResource REST controller.
 *
 * @see CatTipocoloreResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkzoJhApplicationApp.class)
public class CatTipocoloreResourceIntTest {

    private static final Integer DEFAULT_ID_PRODOTTO = 1;
    private static final Integer UPDATED_ID_PRODOTTO = 2;

    private static final String DEFAULT_DESCRIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE = "BBBBBBBBBB";

    @Autowired
    private CatTipocoloreRepository catTipocoloreRepository;

    @Autowired
    private CatTipocoloreMapper catTipocoloreMapper;
    
    @Autowired
    private CatTipocoloreService catTipocoloreService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCatTipocoloreMockMvc;

    private CatTipocolore catTipocolore;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CatTipocoloreResource catTipocoloreResource = new CatTipocoloreResource(catTipocoloreService);
        this.restCatTipocoloreMockMvc = MockMvcBuilders.standaloneSetup(catTipocoloreResource)
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
    public static CatTipocolore createEntity(EntityManager em) {
        CatTipocolore catTipocolore = new CatTipocolore()
            .idProdotto(DEFAULT_ID_PRODOTTO)
            .descrizione(DEFAULT_DESCRIZIONE);
        return catTipocolore;
    }

    @Before
    public void initTest() {
        catTipocolore = createEntity(em);
    }

    @Test
    @Transactional
    public void createCatTipocolore() throws Exception {
        int databaseSizeBeforeCreate = catTipocoloreRepository.findAll().size();

        // Create the CatTipocolore
        CatTipocoloreDTO catTipocoloreDTO = catTipocoloreMapper.toDto(catTipocolore);
        restCatTipocoloreMockMvc.perform(post("/api/cat-tipocolores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catTipocoloreDTO)))
            .andExpect(status().isCreated());

        // Validate the CatTipocolore in the database
        List<CatTipocolore> catTipocoloreList = catTipocoloreRepository.findAll();
        assertThat(catTipocoloreList).hasSize(databaseSizeBeforeCreate + 1);
        CatTipocolore testCatTipocolore = catTipocoloreList.get(catTipocoloreList.size() - 1);
        assertThat(testCatTipocolore.getIdProdotto()).isEqualTo(DEFAULT_ID_PRODOTTO);
        assertThat(testCatTipocolore.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
    }

    @Test
    @Transactional
    public void createCatTipocoloreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = catTipocoloreRepository.findAll().size();

        // Create the CatTipocolore with an existing ID
        catTipocolore.setId(1L);
        CatTipocoloreDTO catTipocoloreDTO = catTipocoloreMapper.toDto(catTipocolore);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCatTipocoloreMockMvc.perform(post("/api/cat-tipocolores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catTipocoloreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CatTipocolore in the database
        List<CatTipocolore> catTipocoloreList = catTipocoloreRepository.findAll();
        assertThat(catTipocoloreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCatTipocolores() throws Exception {
        // Initialize the database
        catTipocoloreRepository.saveAndFlush(catTipocolore);

        // Get all the catTipocoloreList
        restCatTipocoloreMockMvc.perform(get("/api/cat-tipocolores?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(catTipocolore.getId().intValue())))
            .andExpect(jsonPath("$.[*].idProdotto").value(hasItem(DEFAULT_ID_PRODOTTO)))
            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE.toString())));
    }
    
    @Test
    @Transactional
    public void getCatTipocolore() throws Exception {
        // Initialize the database
        catTipocoloreRepository.saveAndFlush(catTipocolore);

        // Get the catTipocolore
        restCatTipocoloreMockMvc.perform(get("/api/cat-tipocolores/{id}", catTipocolore.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(catTipocolore.getId().intValue()))
            .andExpect(jsonPath("$.idProdotto").value(DEFAULT_ID_PRODOTTO))
            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCatTipocolore() throws Exception {
        // Get the catTipocolore
        restCatTipocoloreMockMvc.perform(get("/api/cat-tipocolores/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCatTipocolore() throws Exception {
        // Initialize the database
        catTipocoloreRepository.saveAndFlush(catTipocolore);

        int databaseSizeBeforeUpdate = catTipocoloreRepository.findAll().size();

        // Update the catTipocolore
        CatTipocolore updatedCatTipocolore = catTipocoloreRepository.findById(catTipocolore.getId()).get();
        // Disconnect from session so that the updates on updatedCatTipocolore are not directly saved in db
        em.detach(updatedCatTipocolore);
        updatedCatTipocolore
            .idProdotto(UPDATED_ID_PRODOTTO)
            .descrizione(UPDATED_DESCRIZIONE);
        CatTipocoloreDTO catTipocoloreDTO = catTipocoloreMapper.toDto(updatedCatTipocolore);

        restCatTipocoloreMockMvc.perform(put("/api/cat-tipocolores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catTipocoloreDTO)))
            .andExpect(status().isOk());

        // Validate the CatTipocolore in the database
        List<CatTipocolore> catTipocoloreList = catTipocoloreRepository.findAll();
        assertThat(catTipocoloreList).hasSize(databaseSizeBeforeUpdate);
        CatTipocolore testCatTipocolore = catTipocoloreList.get(catTipocoloreList.size() - 1);
        assertThat(testCatTipocolore.getIdProdotto()).isEqualTo(UPDATED_ID_PRODOTTO);
        assertThat(testCatTipocolore.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
    }

    @Test
    @Transactional
    public void updateNonExistingCatTipocolore() throws Exception {
        int databaseSizeBeforeUpdate = catTipocoloreRepository.findAll().size();

        // Create the CatTipocolore
        CatTipocoloreDTO catTipocoloreDTO = catTipocoloreMapper.toDto(catTipocolore);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCatTipocoloreMockMvc.perform(put("/api/cat-tipocolores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catTipocoloreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CatTipocolore in the database
        List<CatTipocolore> catTipocoloreList = catTipocoloreRepository.findAll();
        assertThat(catTipocoloreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCatTipocolore() throws Exception {
        // Initialize the database
        catTipocoloreRepository.saveAndFlush(catTipocolore);

        int databaseSizeBeforeDelete = catTipocoloreRepository.findAll().size();

        // Get the catTipocolore
        restCatTipocoloreMockMvc.perform(delete("/api/cat-tipocolores/{id}", catTipocolore.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CatTipocolore> catTipocoloreList = catTipocoloreRepository.findAll();
        assertThat(catTipocoloreList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatTipocolore.class);
        CatTipocolore catTipocolore1 = new CatTipocolore();
        catTipocolore1.setId(1L);
        CatTipocolore catTipocolore2 = new CatTipocolore();
        catTipocolore2.setId(catTipocolore1.getId());
        assertThat(catTipocolore1).isEqualTo(catTipocolore2);
        catTipocolore2.setId(2L);
        assertThat(catTipocolore1).isNotEqualTo(catTipocolore2);
        catTipocolore1.setId(null);
        assertThat(catTipocolore1).isNotEqualTo(catTipocolore2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatTipocoloreDTO.class);
        CatTipocoloreDTO catTipocoloreDTO1 = new CatTipocoloreDTO();
        catTipocoloreDTO1.setId(1L);
        CatTipocoloreDTO catTipocoloreDTO2 = new CatTipocoloreDTO();
        assertThat(catTipocoloreDTO1).isNotEqualTo(catTipocoloreDTO2);
        catTipocoloreDTO2.setId(catTipocoloreDTO1.getId());
        assertThat(catTipocoloreDTO1).isEqualTo(catTipocoloreDTO2);
        catTipocoloreDTO2.setId(2L);
        assertThat(catTipocoloreDTO1).isNotEqualTo(catTipocoloreDTO2);
        catTipocoloreDTO1.setId(null);
        assertThat(catTipocoloreDTO1).isNotEqualTo(catTipocoloreDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(catTipocoloreMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(catTipocoloreMapper.fromId(null)).isNull();
    }
}
