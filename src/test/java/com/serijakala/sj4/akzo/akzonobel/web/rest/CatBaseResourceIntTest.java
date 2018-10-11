package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.serijakala.sj4.akzo.akzonobel.AkzoJhApplicationApp;

import com.serijakala.sj4.akzo.akzonobel.domain.CatBase;
import com.serijakala.sj4.akzo.akzonobel.repository.CatBaseRepository;
import com.serijakala.sj4.akzo.akzonobel.service.CatBaseService;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatBaseDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.CatBaseMapper;
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
 * Test class for the CatBaseResource REST controller.
 *
 * @see CatBaseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkzoJhApplicationApp.class)
public class CatBaseResourceIntTest {

    private static final String DEFAULT_CODBASE = "AAAAAAAAAA";
    private static final String UPDATED_CODBASE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE = "BBBBBBBBBB";

    @Autowired
    private CatBaseRepository catBaseRepository;

    @Autowired
    private CatBaseMapper catBaseMapper;
    
    @Autowired
    private CatBaseService catBaseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCatBaseMockMvc;

    private CatBase catBase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CatBaseResource catBaseResource = new CatBaseResource(catBaseService);
        this.restCatBaseMockMvc = MockMvcBuilders.standaloneSetup(catBaseResource)
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
    public static CatBase createEntity(EntityManager em) {
        CatBase catBase = new CatBase()
            .codbase(DEFAULT_CODBASE)
            .descrizione(DEFAULT_DESCRIZIONE);
        return catBase;
    }

    @Before
    public void initTest() {
        catBase = createEntity(em);
    }

    @Test
    @Transactional
    public void createCatBase() throws Exception {
        int databaseSizeBeforeCreate = catBaseRepository.findAll().size();

        // Create the CatBase
        CatBaseDTO catBaseDTO = catBaseMapper.toDto(catBase);
        restCatBaseMockMvc.perform(post("/api/cat-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catBaseDTO)))
            .andExpect(status().isCreated());

        // Validate the CatBase in the database
        List<CatBase> catBaseList = catBaseRepository.findAll();
        assertThat(catBaseList).hasSize(databaseSizeBeforeCreate + 1);
        CatBase testCatBase = catBaseList.get(catBaseList.size() - 1);
        assertThat(testCatBase.getCodbase()).isEqualTo(DEFAULT_CODBASE);
        assertThat(testCatBase.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
    }

    @Test
    @Transactional
    public void createCatBaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = catBaseRepository.findAll().size();

        // Create the CatBase with an existing ID
        catBase.setId(1L);
        CatBaseDTO catBaseDTO = catBaseMapper.toDto(catBase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCatBaseMockMvc.perform(post("/api/cat-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catBaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CatBase in the database
        List<CatBase> catBaseList = catBaseRepository.findAll();
        assertThat(catBaseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCatBases() throws Exception {
        // Initialize the database
        catBaseRepository.saveAndFlush(catBase);

        // Get all the catBaseList
        restCatBaseMockMvc.perform(get("/api/cat-bases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(catBase.getId().intValue())))
            .andExpect(jsonPath("$.[*].codbase").value(hasItem(DEFAULT_CODBASE.toString())))
            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE.toString())));
    }
    
    @Test
    @Transactional
    public void getCatBase() throws Exception {
        // Initialize the database
        catBaseRepository.saveAndFlush(catBase);

        // Get the catBase
        restCatBaseMockMvc.perform(get("/api/cat-bases/{id}", catBase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(catBase.getId().intValue()))
            .andExpect(jsonPath("$.codbase").value(DEFAULT_CODBASE.toString()))
            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCatBase() throws Exception {
        // Get the catBase
        restCatBaseMockMvc.perform(get("/api/cat-bases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCatBase() throws Exception {
        // Initialize the database
        catBaseRepository.saveAndFlush(catBase);

        int databaseSizeBeforeUpdate = catBaseRepository.findAll().size();

        // Update the catBase
        CatBase updatedCatBase = catBaseRepository.findById(catBase.getId()).get();
        // Disconnect from session so that the updates on updatedCatBase are not directly saved in db
        em.detach(updatedCatBase);
        updatedCatBase
            .codbase(UPDATED_CODBASE)
            .descrizione(UPDATED_DESCRIZIONE);
        CatBaseDTO catBaseDTO = catBaseMapper.toDto(updatedCatBase);

        restCatBaseMockMvc.perform(put("/api/cat-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catBaseDTO)))
            .andExpect(status().isOk());

        // Validate the CatBase in the database
        List<CatBase> catBaseList = catBaseRepository.findAll();
        assertThat(catBaseList).hasSize(databaseSizeBeforeUpdate);
        CatBase testCatBase = catBaseList.get(catBaseList.size() - 1);
        assertThat(testCatBase.getCodbase()).isEqualTo(UPDATED_CODBASE);
        assertThat(testCatBase.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
    }

    @Test
    @Transactional
    public void updateNonExistingCatBase() throws Exception {
        int databaseSizeBeforeUpdate = catBaseRepository.findAll().size();

        // Create the CatBase
        CatBaseDTO catBaseDTO = catBaseMapper.toDto(catBase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCatBaseMockMvc.perform(put("/api/cat-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catBaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CatBase in the database
        List<CatBase> catBaseList = catBaseRepository.findAll();
        assertThat(catBaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCatBase() throws Exception {
        // Initialize the database
        catBaseRepository.saveAndFlush(catBase);

        int databaseSizeBeforeDelete = catBaseRepository.findAll().size();

        // Get the catBase
        restCatBaseMockMvc.perform(delete("/api/cat-bases/{id}", catBase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CatBase> catBaseList = catBaseRepository.findAll();
        assertThat(catBaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatBase.class);
        CatBase catBase1 = new CatBase();
        catBase1.setId(1L);
        CatBase catBase2 = new CatBase();
        catBase2.setId(catBase1.getId());
        assertThat(catBase1).isEqualTo(catBase2);
        catBase2.setId(2L);
        assertThat(catBase1).isNotEqualTo(catBase2);
        catBase1.setId(null);
        assertThat(catBase1).isNotEqualTo(catBase2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatBaseDTO.class);
        CatBaseDTO catBaseDTO1 = new CatBaseDTO();
        catBaseDTO1.setId(1L);
        CatBaseDTO catBaseDTO2 = new CatBaseDTO();
        assertThat(catBaseDTO1).isNotEqualTo(catBaseDTO2);
        catBaseDTO2.setId(catBaseDTO1.getId());
        assertThat(catBaseDTO1).isEqualTo(catBaseDTO2);
        catBaseDTO2.setId(2L);
        assertThat(catBaseDTO1).isNotEqualTo(catBaseDTO2);
        catBaseDTO1.setId(null);
        assertThat(catBaseDTO1).isNotEqualTo(catBaseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(catBaseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(catBaseMapper.fromId(null)).isNull();
    }
}
