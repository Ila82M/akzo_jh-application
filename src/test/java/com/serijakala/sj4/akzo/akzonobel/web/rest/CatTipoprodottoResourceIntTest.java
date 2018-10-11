package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.serijakala.sj4.akzo.akzonobel.AkzoJhApplicationApp;

import com.serijakala.sj4.akzo.akzonobel.domain.CatTipoprodotto;
import com.serijakala.sj4.akzo.akzonobel.repository.CatTipoprodottoRepository;
import com.serijakala.sj4.akzo.akzonobel.service.CatTipoprodottoService;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatTipoprodottoDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.CatTipoprodottoMapper;
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
 * Test class for the CatTipoprodottoResource REST controller.
 *
 * @see CatTipoprodottoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkzoJhApplicationApp.class)
public class CatTipoprodottoResourceIntTest {

    private static final String DEFAULT_DESCRIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE = "BBBBBBBBBB";

    @Autowired
    private CatTipoprodottoRepository catTipoprodottoRepository;

    @Autowired
    private CatTipoprodottoMapper catTipoprodottoMapper;
    
    @Autowired
    private CatTipoprodottoService catTipoprodottoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCatTipoprodottoMockMvc;

    private CatTipoprodotto catTipoprodotto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CatTipoprodottoResource catTipoprodottoResource = new CatTipoprodottoResource(catTipoprodottoService);
        this.restCatTipoprodottoMockMvc = MockMvcBuilders.standaloneSetup(catTipoprodottoResource)
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
    public static CatTipoprodotto createEntity(EntityManager em) {
        CatTipoprodotto catTipoprodotto = new CatTipoprodotto()
            .descrizione(DEFAULT_DESCRIZIONE);
        return catTipoprodotto;
    }

    @Before
    public void initTest() {
        catTipoprodotto = createEntity(em);
    }

    @Test
    @Transactional
    public void createCatTipoprodotto() throws Exception {
        int databaseSizeBeforeCreate = catTipoprodottoRepository.findAll().size();

        // Create the CatTipoprodotto
        CatTipoprodottoDTO catTipoprodottoDTO = catTipoprodottoMapper.toDto(catTipoprodotto);
        restCatTipoprodottoMockMvc.perform(post("/api/cat-tipoprodottos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catTipoprodottoDTO)))
            .andExpect(status().isCreated());

        // Validate the CatTipoprodotto in the database
        List<CatTipoprodotto> catTipoprodottoList = catTipoprodottoRepository.findAll();
        assertThat(catTipoprodottoList).hasSize(databaseSizeBeforeCreate + 1);
        CatTipoprodotto testCatTipoprodotto = catTipoprodottoList.get(catTipoprodottoList.size() - 1);
        assertThat(testCatTipoprodotto.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
    }

    @Test
    @Transactional
    public void createCatTipoprodottoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = catTipoprodottoRepository.findAll().size();

        // Create the CatTipoprodotto with an existing ID
        catTipoprodotto.setId(1L);
        CatTipoprodottoDTO catTipoprodottoDTO = catTipoprodottoMapper.toDto(catTipoprodotto);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCatTipoprodottoMockMvc.perform(post("/api/cat-tipoprodottos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catTipoprodottoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CatTipoprodotto in the database
        List<CatTipoprodotto> catTipoprodottoList = catTipoprodottoRepository.findAll();
        assertThat(catTipoprodottoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCatTipoprodottos() throws Exception {
        // Initialize the database
        catTipoprodottoRepository.saveAndFlush(catTipoprodotto);

        // Get all the catTipoprodottoList
        restCatTipoprodottoMockMvc.perform(get("/api/cat-tipoprodottos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(catTipoprodotto.getId().intValue())))
            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE.toString())));
    }
    
    @Test
    @Transactional
    public void getCatTipoprodotto() throws Exception {
        // Initialize the database
        catTipoprodottoRepository.saveAndFlush(catTipoprodotto);

        // Get the catTipoprodotto
        restCatTipoprodottoMockMvc.perform(get("/api/cat-tipoprodottos/{id}", catTipoprodotto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(catTipoprodotto.getId().intValue()))
            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCatTipoprodotto() throws Exception {
        // Get the catTipoprodotto
        restCatTipoprodottoMockMvc.perform(get("/api/cat-tipoprodottos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCatTipoprodotto() throws Exception {
        // Initialize the database
        catTipoprodottoRepository.saveAndFlush(catTipoprodotto);

        int databaseSizeBeforeUpdate = catTipoprodottoRepository.findAll().size();

        // Update the catTipoprodotto
        CatTipoprodotto updatedCatTipoprodotto = catTipoprodottoRepository.findById(catTipoprodotto.getId()).get();
        // Disconnect from session so that the updates on updatedCatTipoprodotto are not directly saved in db
        em.detach(updatedCatTipoprodotto);
        updatedCatTipoprodotto
            .descrizione(UPDATED_DESCRIZIONE);
        CatTipoprodottoDTO catTipoprodottoDTO = catTipoprodottoMapper.toDto(updatedCatTipoprodotto);

        restCatTipoprodottoMockMvc.perform(put("/api/cat-tipoprodottos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catTipoprodottoDTO)))
            .andExpect(status().isOk());

        // Validate the CatTipoprodotto in the database
        List<CatTipoprodotto> catTipoprodottoList = catTipoprodottoRepository.findAll();
        assertThat(catTipoprodottoList).hasSize(databaseSizeBeforeUpdate);
        CatTipoprodotto testCatTipoprodotto = catTipoprodottoList.get(catTipoprodottoList.size() - 1);
        assertThat(testCatTipoprodotto.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
    }

    @Test
    @Transactional
    public void updateNonExistingCatTipoprodotto() throws Exception {
        int databaseSizeBeforeUpdate = catTipoprodottoRepository.findAll().size();

        // Create the CatTipoprodotto
        CatTipoprodottoDTO catTipoprodottoDTO = catTipoprodottoMapper.toDto(catTipoprodotto);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCatTipoprodottoMockMvc.perform(put("/api/cat-tipoprodottos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catTipoprodottoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CatTipoprodotto in the database
        List<CatTipoprodotto> catTipoprodottoList = catTipoprodottoRepository.findAll();
        assertThat(catTipoprodottoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCatTipoprodotto() throws Exception {
        // Initialize the database
        catTipoprodottoRepository.saveAndFlush(catTipoprodotto);

        int databaseSizeBeforeDelete = catTipoprodottoRepository.findAll().size();

        // Get the catTipoprodotto
        restCatTipoprodottoMockMvc.perform(delete("/api/cat-tipoprodottos/{id}", catTipoprodotto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CatTipoprodotto> catTipoprodottoList = catTipoprodottoRepository.findAll();
        assertThat(catTipoprodottoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatTipoprodotto.class);
        CatTipoprodotto catTipoprodotto1 = new CatTipoprodotto();
        catTipoprodotto1.setId(1L);
        CatTipoprodotto catTipoprodotto2 = new CatTipoprodotto();
        catTipoprodotto2.setId(catTipoprodotto1.getId());
        assertThat(catTipoprodotto1).isEqualTo(catTipoprodotto2);
        catTipoprodotto2.setId(2L);
        assertThat(catTipoprodotto1).isNotEqualTo(catTipoprodotto2);
        catTipoprodotto1.setId(null);
        assertThat(catTipoprodotto1).isNotEqualTo(catTipoprodotto2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatTipoprodottoDTO.class);
        CatTipoprodottoDTO catTipoprodottoDTO1 = new CatTipoprodottoDTO();
        catTipoprodottoDTO1.setId(1L);
        CatTipoprodottoDTO catTipoprodottoDTO2 = new CatTipoprodottoDTO();
        assertThat(catTipoprodottoDTO1).isNotEqualTo(catTipoprodottoDTO2);
        catTipoprodottoDTO2.setId(catTipoprodottoDTO1.getId());
        assertThat(catTipoprodottoDTO1).isEqualTo(catTipoprodottoDTO2);
        catTipoprodottoDTO2.setId(2L);
        assertThat(catTipoprodottoDTO1).isNotEqualTo(catTipoprodottoDTO2);
        catTipoprodottoDTO1.setId(null);
        assertThat(catTipoprodottoDTO1).isNotEqualTo(catTipoprodottoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(catTipoprodottoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(catTipoprodottoMapper.fromId(null)).isNull();
    }
}
