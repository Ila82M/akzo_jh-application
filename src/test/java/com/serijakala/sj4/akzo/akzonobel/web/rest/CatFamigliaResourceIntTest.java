package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.serijakala.sj4.akzo.akzonobel.AkzoJhApplicationApp;

import com.serijakala.sj4.akzo.akzonobel.domain.CatFamiglia;
import com.serijakala.sj4.akzo.akzonobel.repository.CatFamigliaRepository;
import com.serijakala.sj4.akzo.akzonobel.service.CatFamigliaService;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatFamigliaDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.CatFamigliaMapper;
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
 * Test class for the CatFamigliaResource REST controller.
 *
 * @see CatFamigliaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkzoJhApplicationApp.class)
public class CatFamigliaResourceIntTest {

    private static final String DEFAULT_MARCHIO = "AAAAAAAAAA";
    private static final String UPDATED_MARCHIO = "BBBBBBBBBB";

    private static final String DEFAULT_ATTIVO = "AAAAAAAAAA";
    private static final String UPDATED_ATTIVO = "BBBBBBBBBB";

    @Autowired
    private CatFamigliaRepository catFamigliaRepository;

    @Autowired
    private CatFamigliaMapper catFamigliaMapper;
    
    @Autowired
    private CatFamigliaService catFamigliaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCatFamigliaMockMvc;

    private CatFamiglia catFamiglia;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CatFamigliaResource catFamigliaResource = new CatFamigliaResource(catFamigliaService);
        this.restCatFamigliaMockMvc = MockMvcBuilders.standaloneSetup(catFamigliaResource)
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
    public static CatFamiglia createEntity(EntityManager em) {
        CatFamiglia catFamiglia = new CatFamiglia()
            .marchio(DEFAULT_MARCHIO)
            .attivo(DEFAULT_ATTIVO);
        return catFamiglia;
    }

    @Before
    public void initTest() {
        catFamiglia = createEntity(em);
    }

    @Test
    @Transactional
    public void createCatFamiglia() throws Exception {
        int databaseSizeBeforeCreate = catFamigliaRepository.findAll().size();

        // Create the CatFamiglia
        CatFamigliaDTO catFamigliaDTO = catFamigliaMapper.toDto(catFamiglia);
        restCatFamigliaMockMvc.perform(post("/api/cat-famiglias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catFamigliaDTO)))
            .andExpect(status().isCreated());

        // Validate the CatFamiglia in the database
        List<CatFamiglia> catFamigliaList = catFamigliaRepository.findAll();
        assertThat(catFamigliaList).hasSize(databaseSizeBeforeCreate + 1);
        CatFamiglia testCatFamiglia = catFamigliaList.get(catFamigliaList.size() - 1);
        assertThat(testCatFamiglia.getMarchio()).isEqualTo(DEFAULT_MARCHIO);
        assertThat(testCatFamiglia.getAttivo()).isEqualTo(DEFAULT_ATTIVO);
    }

    @Test
    @Transactional
    public void createCatFamigliaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = catFamigliaRepository.findAll().size();

        // Create the CatFamiglia with an existing ID
        catFamiglia.setId(1L);
        CatFamigliaDTO catFamigliaDTO = catFamigliaMapper.toDto(catFamiglia);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCatFamigliaMockMvc.perform(post("/api/cat-famiglias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catFamigliaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CatFamiglia in the database
        List<CatFamiglia> catFamigliaList = catFamigliaRepository.findAll();
        assertThat(catFamigliaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCatFamiglias() throws Exception {
        // Initialize the database
        catFamigliaRepository.saveAndFlush(catFamiglia);

        // Get all the catFamigliaList
        restCatFamigliaMockMvc.perform(get("/api/cat-famiglias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(catFamiglia.getId().intValue())))
            .andExpect(jsonPath("$.[*].marchio").value(hasItem(DEFAULT_MARCHIO.toString())))
            .andExpect(jsonPath("$.[*].attivo").value(hasItem(DEFAULT_ATTIVO.toString())));
    }
    
    @Test
    @Transactional
    public void getCatFamiglia() throws Exception {
        // Initialize the database
        catFamigliaRepository.saveAndFlush(catFamiglia);

        // Get the catFamiglia
        restCatFamigliaMockMvc.perform(get("/api/cat-famiglias/{id}", catFamiglia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(catFamiglia.getId().intValue()))
            .andExpect(jsonPath("$.marchio").value(DEFAULT_MARCHIO.toString()))
            .andExpect(jsonPath("$.attivo").value(DEFAULT_ATTIVO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCatFamiglia() throws Exception {
        // Get the catFamiglia
        restCatFamigliaMockMvc.perform(get("/api/cat-famiglias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCatFamiglia() throws Exception {
        // Initialize the database
        catFamigliaRepository.saveAndFlush(catFamiglia);

        int databaseSizeBeforeUpdate = catFamigliaRepository.findAll().size();

        // Update the catFamiglia
        CatFamiglia updatedCatFamiglia = catFamigliaRepository.findById(catFamiglia.getId()).get();
        // Disconnect from session so that the updates on updatedCatFamiglia are not directly saved in db
        em.detach(updatedCatFamiglia);
        updatedCatFamiglia
            .marchio(UPDATED_MARCHIO)
            .attivo(UPDATED_ATTIVO);
        CatFamigliaDTO catFamigliaDTO = catFamigliaMapper.toDto(updatedCatFamiglia);

        restCatFamigliaMockMvc.perform(put("/api/cat-famiglias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catFamigliaDTO)))
            .andExpect(status().isOk());

        // Validate the CatFamiglia in the database
        List<CatFamiglia> catFamigliaList = catFamigliaRepository.findAll();
        assertThat(catFamigliaList).hasSize(databaseSizeBeforeUpdate);
        CatFamiglia testCatFamiglia = catFamigliaList.get(catFamigliaList.size() - 1);
        assertThat(testCatFamiglia.getMarchio()).isEqualTo(UPDATED_MARCHIO);
        assertThat(testCatFamiglia.getAttivo()).isEqualTo(UPDATED_ATTIVO);
    }

    @Test
    @Transactional
    public void updateNonExistingCatFamiglia() throws Exception {
        int databaseSizeBeforeUpdate = catFamigliaRepository.findAll().size();

        // Create the CatFamiglia
        CatFamigliaDTO catFamigliaDTO = catFamigliaMapper.toDto(catFamiglia);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCatFamigliaMockMvc.perform(put("/api/cat-famiglias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catFamigliaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CatFamiglia in the database
        List<CatFamiglia> catFamigliaList = catFamigliaRepository.findAll();
        assertThat(catFamigliaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCatFamiglia() throws Exception {
        // Initialize the database
        catFamigliaRepository.saveAndFlush(catFamiglia);

        int databaseSizeBeforeDelete = catFamigliaRepository.findAll().size();

        // Get the catFamiglia
        restCatFamigliaMockMvc.perform(delete("/api/cat-famiglias/{id}", catFamiglia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CatFamiglia> catFamigliaList = catFamigliaRepository.findAll();
        assertThat(catFamigliaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatFamiglia.class);
        CatFamiglia catFamiglia1 = new CatFamiglia();
        catFamiglia1.setId(1L);
        CatFamiglia catFamiglia2 = new CatFamiglia();
        catFamiglia2.setId(catFamiglia1.getId());
        assertThat(catFamiglia1).isEqualTo(catFamiglia2);
        catFamiglia2.setId(2L);
        assertThat(catFamiglia1).isNotEqualTo(catFamiglia2);
        catFamiglia1.setId(null);
        assertThat(catFamiglia1).isNotEqualTo(catFamiglia2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatFamigliaDTO.class);
        CatFamigliaDTO catFamigliaDTO1 = new CatFamigliaDTO();
        catFamigliaDTO1.setId(1L);
        CatFamigliaDTO catFamigliaDTO2 = new CatFamigliaDTO();
        assertThat(catFamigliaDTO1).isNotEqualTo(catFamigliaDTO2);
        catFamigliaDTO2.setId(catFamigliaDTO1.getId());
        assertThat(catFamigliaDTO1).isEqualTo(catFamigliaDTO2);
        catFamigliaDTO2.setId(2L);
        assertThat(catFamigliaDTO1).isNotEqualTo(catFamigliaDTO2);
        catFamigliaDTO1.setId(null);
        assertThat(catFamigliaDTO1).isNotEqualTo(catFamigliaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(catFamigliaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(catFamigliaMapper.fromId(null)).isNull();
    }
}
