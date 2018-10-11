package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.serijakala.sj4.akzo.akzonobel.AkzoJhApplicationApp;

import com.serijakala.sj4.akzo.akzonobel.domain.CatLattina;
import com.serijakala.sj4.akzo.akzonobel.repository.CatLattinaRepository;
import com.serijakala.sj4.akzo.akzonobel.service.CatLattinaService;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatLattinaDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.CatLattinaMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.serijakala.sj4.akzo.akzonobel.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CatLattinaResource REST controller.
 *
 * @see CatLattinaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkzoJhApplicationApp.class)
public class CatLattinaResourceIntTest {

    private static final Long DEFAULT_LITRAGGIO = 1L;
    private static final Long UPDATED_LITRAGGIO = 2L;

    private static final String DEFAULT_CODICE = "AAAAAAAAAA";
    private static final String UPDATED_CODICE = "BBBBBBBBBB";

    private static final Long DEFAULT_PREZZO = 1L;
    private static final Long UPDATED_PREZZO = 2L;

    private static final Instant DEFAULT_DATA_UPDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_UPDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private CatLattinaRepository catLattinaRepository;

    @Autowired
    private CatLattinaMapper catLattinaMapper;
    
    @Autowired
    private CatLattinaService catLattinaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCatLattinaMockMvc;

    private CatLattina catLattina;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CatLattinaResource catLattinaResource = new CatLattinaResource(catLattinaService);
        this.restCatLattinaMockMvc = MockMvcBuilders.standaloneSetup(catLattinaResource)
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
    public static CatLattina createEntity(EntityManager em) {
        CatLattina catLattina = new CatLattina()
            .litraggio(DEFAULT_LITRAGGIO)
            .codice(DEFAULT_CODICE)
            .prezzo(DEFAULT_PREZZO)
            .dataUpdate(DEFAULT_DATA_UPDATE);
        return catLattina;
    }

    @Before
    public void initTest() {
        catLattina = createEntity(em);
    }

    @Test
    @Transactional
    public void createCatLattina() throws Exception {
        int databaseSizeBeforeCreate = catLattinaRepository.findAll().size();

        // Create the CatLattina
        CatLattinaDTO catLattinaDTO = catLattinaMapper.toDto(catLattina);
        restCatLattinaMockMvc.perform(post("/api/cat-lattinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catLattinaDTO)))
            .andExpect(status().isCreated());

        // Validate the CatLattina in the database
        List<CatLattina> catLattinaList = catLattinaRepository.findAll();
        assertThat(catLattinaList).hasSize(databaseSizeBeforeCreate + 1);
        CatLattina testCatLattina = catLattinaList.get(catLattinaList.size() - 1);
        assertThat(testCatLattina.getLitraggio()).isEqualTo(DEFAULT_LITRAGGIO);
        assertThat(testCatLattina.getCodice()).isEqualTo(DEFAULT_CODICE);
        assertThat(testCatLattina.getPrezzo()).isEqualTo(DEFAULT_PREZZO);
        assertThat(testCatLattina.getDataUpdate()).isEqualTo(DEFAULT_DATA_UPDATE);
    }

    @Test
    @Transactional
    public void createCatLattinaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = catLattinaRepository.findAll().size();

        // Create the CatLattina with an existing ID
        catLattina.setId(1L);
        CatLattinaDTO catLattinaDTO = catLattinaMapper.toDto(catLattina);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCatLattinaMockMvc.perform(post("/api/cat-lattinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catLattinaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CatLattina in the database
        List<CatLattina> catLattinaList = catLattinaRepository.findAll();
        assertThat(catLattinaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCatLattinas() throws Exception {
        // Initialize the database
        catLattinaRepository.saveAndFlush(catLattina);

        // Get all the catLattinaList
        restCatLattinaMockMvc.perform(get("/api/cat-lattinas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(catLattina.getId().intValue())))
            .andExpect(jsonPath("$.[*].litraggio").value(hasItem(DEFAULT_LITRAGGIO.intValue())))
            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE.toString())))
            .andExpect(jsonPath("$.[*].prezzo").value(hasItem(DEFAULT_PREZZO.intValue())))
            .andExpect(jsonPath("$.[*].dataUpdate").value(hasItem(DEFAULT_DATA_UPDATE.toString())));
    }
    
    @Test
    @Transactional
    public void getCatLattina() throws Exception {
        // Initialize the database
        catLattinaRepository.saveAndFlush(catLattina);

        // Get the catLattina
        restCatLattinaMockMvc.perform(get("/api/cat-lattinas/{id}", catLattina.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(catLattina.getId().intValue()))
            .andExpect(jsonPath("$.litraggio").value(DEFAULT_LITRAGGIO.intValue()))
            .andExpect(jsonPath("$.codice").value(DEFAULT_CODICE.toString()))
            .andExpect(jsonPath("$.prezzo").value(DEFAULT_PREZZO.intValue()))
            .andExpect(jsonPath("$.dataUpdate").value(DEFAULT_DATA_UPDATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCatLattina() throws Exception {
        // Get the catLattina
        restCatLattinaMockMvc.perform(get("/api/cat-lattinas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCatLattina() throws Exception {
        // Initialize the database
        catLattinaRepository.saveAndFlush(catLattina);

        int databaseSizeBeforeUpdate = catLattinaRepository.findAll().size();

        // Update the catLattina
        CatLattina updatedCatLattina = catLattinaRepository.findById(catLattina.getId()).get();
        // Disconnect from session so that the updates on updatedCatLattina are not directly saved in db
        em.detach(updatedCatLattina);
        updatedCatLattina
            .litraggio(UPDATED_LITRAGGIO)
            .codice(UPDATED_CODICE)
            .prezzo(UPDATED_PREZZO)
            .dataUpdate(UPDATED_DATA_UPDATE);
        CatLattinaDTO catLattinaDTO = catLattinaMapper.toDto(updatedCatLattina);

        restCatLattinaMockMvc.perform(put("/api/cat-lattinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catLattinaDTO)))
            .andExpect(status().isOk());

        // Validate the CatLattina in the database
        List<CatLattina> catLattinaList = catLattinaRepository.findAll();
        assertThat(catLattinaList).hasSize(databaseSizeBeforeUpdate);
        CatLattina testCatLattina = catLattinaList.get(catLattinaList.size() - 1);
        assertThat(testCatLattina.getLitraggio()).isEqualTo(UPDATED_LITRAGGIO);
        assertThat(testCatLattina.getCodice()).isEqualTo(UPDATED_CODICE);
        assertThat(testCatLattina.getPrezzo()).isEqualTo(UPDATED_PREZZO);
        assertThat(testCatLattina.getDataUpdate()).isEqualTo(UPDATED_DATA_UPDATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCatLattina() throws Exception {
        int databaseSizeBeforeUpdate = catLattinaRepository.findAll().size();

        // Create the CatLattina
        CatLattinaDTO catLattinaDTO = catLattinaMapper.toDto(catLattina);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCatLattinaMockMvc.perform(put("/api/cat-lattinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catLattinaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CatLattina in the database
        List<CatLattina> catLattinaList = catLattinaRepository.findAll();
        assertThat(catLattinaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCatLattina() throws Exception {
        // Initialize the database
        catLattinaRepository.saveAndFlush(catLattina);

        int databaseSizeBeforeDelete = catLattinaRepository.findAll().size();

        // Get the catLattina
        restCatLattinaMockMvc.perform(delete("/api/cat-lattinas/{id}", catLattina.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CatLattina> catLattinaList = catLattinaRepository.findAll();
        assertThat(catLattinaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatLattina.class);
        CatLattina catLattina1 = new CatLattina();
        catLattina1.setId(1L);
        CatLattina catLattina2 = new CatLattina();
        catLattina2.setId(catLattina1.getId());
        assertThat(catLattina1).isEqualTo(catLattina2);
        catLattina2.setId(2L);
        assertThat(catLattina1).isNotEqualTo(catLattina2);
        catLattina1.setId(null);
        assertThat(catLattina1).isNotEqualTo(catLattina2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatLattinaDTO.class);
        CatLattinaDTO catLattinaDTO1 = new CatLattinaDTO();
        catLattinaDTO1.setId(1L);
        CatLattinaDTO catLattinaDTO2 = new CatLattinaDTO();
        assertThat(catLattinaDTO1).isNotEqualTo(catLattinaDTO2);
        catLattinaDTO2.setId(catLattinaDTO1.getId());
        assertThat(catLattinaDTO1).isEqualTo(catLattinaDTO2);
        catLattinaDTO2.setId(2L);
        assertThat(catLattinaDTO1).isNotEqualTo(catLattinaDTO2);
        catLattinaDTO1.setId(null);
        assertThat(catLattinaDTO1).isNotEqualTo(catLattinaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(catLattinaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(catLattinaMapper.fromId(null)).isNull();
    }
}
