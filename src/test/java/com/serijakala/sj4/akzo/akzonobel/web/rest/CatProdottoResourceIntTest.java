package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.serijakala.sj4.akzo.akzonobel.AkzoJhApplicationApp;

import com.serijakala.sj4.akzo.akzonobel.domain.CatProdotto;
import com.serijakala.sj4.akzo.akzonobel.repository.CatProdottoRepository;
import com.serijakala.sj4.akzo.akzonobel.service.CatProdottoService;
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
 * Test class for the CatProdottoResource REST controller.
 *
 * @see CatProdottoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkzoJhApplicationApp.class)
public class CatProdottoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE = "BBBBBBBBBB";

    private static final String DEFAULT_SOTTOTIPO = "AAAAAAAAAA";
    private static final String UPDATED_SOTTOTIPO = "BBBBBBBBBB";

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_SCHEDA_TECNICA = "AAAAAAAAAA";
    private static final String UPDATED_SCHEDA_TECNICA = "BBBBBBBBBB";

    private static final String DEFAULT_SCHEDA_SICUREZZA = "AAAAAAAAAA";
    private static final String UPDATED_SCHEDA_SICUREZZA = "BBBBBBBBBB";

    private static final String DEFAULT_IMG = "AAAAAAAAAA";
    private static final String UPDATED_IMG = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATA_UPDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_UPDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PUBBLICATO = "AAAAAAAAAA";
    private static final String UPDATED_PUBBLICATO = "BBBBBBBBBB";

    private static final String DEFAULT_MISURA = "AAAAAAAAAA";
    private static final String UPDATED_MISURA = "BBBBBBBBBB";

    @Autowired
    private CatProdottoRepository catProdottoRepository;
    
    @Autowired
    private CatProdottoService catProdottoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCatProdottoMockMvc;

    private CatProdotto catProdotto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CatProdottoResource catProdottoResource = new CatProdottoResource(catProdottoService);
        this.restCatProdottoMockMvc = MockMvcBuilders.standaloneSetup(catProdottoResource)
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
    public static CatProdotto createEntity(EntityManager em) {
        CatProdotto catProdotto = new CatProdotto()
            .nome(DEFAULT_NOME)
            .descrizione(DEFAULT_DESCRIZIONE)
            .sottotipo(DEFAULT_SOTTOTIPO)
            .note(DEFAULT_NOTE)
            .schedaTecnica(DEFAULT_SCHEDA_TECNICA)
            .schedaSicurezza(DEFAULT_SCHEDA_SICUREZZA)
            .img(DEFAULT_IMG)
            .dataUpdate(DEFAULT_DATA_UPDATE)
            .pubblicato(DEFAULT_PUBBLICATO)
            .misura(DEFAULT_MISURA);
        return catProdotto;
    }

    @Before
    public void initTest() {
        catProdotto = createEntity(em);
    }

    @Test
    @Transactional
    public void createCatProdotto() throws Exception {
        int databaseSizeBeforeCreate = catProdottoRepository.findAll().size();

        // Create the CatProdotto
        restCatProdottoMockMvc.perform(post("/api/cat-prodottos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catProdotto)))
            .andExpect(status().isCreated());

        // Validate the CatProdotto in the database
        List<CatProdotto> catProdottoList = catProdottoRepository.findAll();
        assertThat(catProdottoList).hasSize(databaseSizeBeforeCreate + 1);
        CatProdotto testCatProdotto = catProdottoList.get(catProdottoList.size() - 1);
        assertThat(testCatProdotto.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testCatProdotto.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
        assertThat(testCatProdotto.getSottotipo()).isEqualTo(DEFAULT_SOTTOTIPO);
        assertThat(testCatProdotto.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testCatProdotto.getSchedaTecnica()).isEqualTo(DEFAULT_SCHEDA_TECNICA);
        assertThat(testCatProdotto.getSchedaSicurezza()).isEqualTo(DEFAULT_SCHEDA_SICUREZZA);
        assertThat(testCatProdotto.getImg()).isEqualTo(DEFAULT_IMG);
        assertThat(testCatProdotto.getDataUpdate()).isEqualTo(DEFAULT_DATA_UPDATE);
        assertThat(testCatProdotto.getPubblicato()).isEqualTo(DEFAULT_PUBBLICATO);
        assertThat(testCatProdotto.getMisura()).isEqualTo(DEFAULT_MISURA);
    }

    @Test
    @Transactional
    public void createCatProdottoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = catProdottoRepository.findAll().size();

        // Create the CatProdotto with an existing ID
        catProdotto.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCatProdottoMockMvc.perform(post("/api/cat-prodottos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catProdotto)))
            .andExpect(status().isBadRequest());

        // Validate the CatProdotto in the database
        List<CatProdotto> catProdottoList = catProdottoRepository.findAll();
        assertThat(catProdottoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCatProdottos() throws Exception {
        // Initialize the database
        catProdottoRepository.saveAndFlush(catProdotto);

        // Get all the catProdottoList
        restCatProdottoMockMvc.perform(get("/api/cat-prodottos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(catProdotto.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE.toString())))
            .andExpect(jsonPath("$.[*].sottotipo").value(hasItem(DEFAULT_SOTTOTIPO.toString())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())))
            .andExpect(jsonPath("$.[*].schedaTecnica").value(hasItem(DEFAULT_SCHEDA_TECNICA.toString())))
            .andExpect(jsonPath("$.[*].schedaSicurezza").value(hasItem(DEFAULT_SCHEDA_SICUREZZA.toString())))
            .andExpect(jsonPath("$.[*].img").value(hasItem(DEFAULT_IMG.toString())))
            .andExpect(jsonPath("$.[*].dataUpdate").value(hasItem(DEFAULT_DATA_UPDATE.toString())))
            .andExpect(jsonPath("$.[*].pubblicato").value(hasItem(DEFAULT_PUBBLICATO.toString())))
            .andExpect(jsonPath("$.[*].misura").value(hasItem(DEFAULT_MISURA.toString())));
    }
    
    @Test
    @Transactional
    public void getCatProdotto() throws Exception {
        // Initialize the database
        catProdottoRepository.saveAndFlush(catProdotto);

        // Get the catProdotto
        restCatProdottoMockMvc.perform(get("/api/cat-prodottos/{id}", catProdotto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(catProdotto.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE.toString()))
            .andExpect(jsonPath("$.sottotipo").value(DEFAULT_SOTTOTIPO.toString()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()))
            .andExpect(jsonPath("$.schedaTecnica").value(DEFAULT_SCHEDA_TECNICA.toString()))
            .andExpect(jsonPath("$.schedaSicurezza").value(DEFAULT_SCHEDA_SICUREZZA.toString()))
            .andExpect(jsonPath("$.img").value(DEFAULT_IMG.toString()))
            .andExpect(jsonPath("$.dataUpdate").value(DEFAULT_DATA_UPDATE.toString()))
            .andExpect(jsonPath("$.pubblicato").value(DEFAULT_PUBBLICATO.toString()))
            .andExpect(jsonPath("$.misura").value(DEFAULT_MISURA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCatProdotto() throws Exception {
        // Get the catProdotto
        restCatProdottoMockMvc.perform(get("/api/cat-prodottos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCatProdotto() throws Exception {
        // Initialize the database
        catProdottoService.save(catProdotto);

        int databaseSizeBeforeUpdate = catProdottoRepository.findAll().size();

        // Update the catProdotto
        CatProdotto updatedCatProdotto = catProdottoRepository.findById(catProdotto.getId()).get();
        // Disconnect from session so that the updates on updatedCatProdotto are not directly saved in db
        em.detach(updatedCatProdotto);
        updatedCatProdotto
            .nome(UPDATED_NOME)
            .descrizione(UPDATED_DESCRIZIONE)
            .sottotipo(UPDATED_SOTTOTIPO)
            .note(UPDATED_NOTE)
            .schedaTecnica(UPDATED_SCHEDA_TECNICA)
            .schedaSicurezza(UPDATED_SCHEDA_SICUREZZA)
            .img(UPDATED_IMG)
            .dataUpdate(UPDATED_DATA_UPDATE)
            .pubblicato(UPDATED_PUBBLICATO)
            .misura(UPDATED_MISURA);

        restCatProdottoMockMvc.perform(put("/api/cat-prodottos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCatProdotto)))
            .andExpect(status().isOk());

        // Validate the CatProdotto in the database
        List<CatProdotto> catProdottoList = catProdottoRepository.findAll();
        assertThat(catProdottoList).hasSize(databaseSizeBeforeUpdate);
        CatProdotto testCatProdotto = catProdottoList.get(catProdottoList.size() - 1);
        assertThat(testCatProdotto.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testCatProdotto.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
        assertThat(testCatProdotto.getSottotipo()).isEqualTo(UPDATED_SOTTOTIPO);
        assertThat(testCatProdotto.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testCatProdotto.getSchedaTecnica()).isEqualTo(UPDATED_SCHEDA_TECNICA);
        assertThat(testCatProdotto.getSchedaSicurezza()).isEqualTo(UPDATED_SCHEDA_SICUREZZA);
        assertThat(testCatProdotto.getImg()).isEqualTo(UPDATED_IMG);
        assertThat(testCatProdotto.getDataUpdate()).isEqualTo(UPDATED_DATA_UPDATE);
        assertThat(testCatProdotto.getPubblicato()).isEqualTo(UPDATED_PUBBLICATO);
        assertThat(testCatProdotto.getMisura()).isEqualTo(UPDATED_MISURA);
    }

    @Test
    @Transactional
    public void updateNonExistingCatProdotto() throws Exception {
        int databaseSizeBeforeUpdate = catProdottoRepository.findAll().size();

        // Create the CatProdotto

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCatProdottoMockMvc.perform(put("/api/cat-prodottos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catProdotto)))
            .andExpect(status().isBadRequest());

        // Validate the CatProdotto in the database
        List<CatProdotto> catProdottoList = catProdottoRepository.findAll();
        assertThat(catProdottoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCatProdotto() throws Exception {
        // Initialize the database
        catProdottoService.save(catProdotto);

        int databaseSizeBeforeDelete = catProdottoRepository.findAll().size();

        // Get the catProdotto
        restCatProdottoMockMvc.perform(delete("/api/cat-prodottos/{id}", catProdotto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CatProdotto> catProdottoList = catProdottoRepository.findAll();
        assertThat(catProdottoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatProdotto.class);
        CatProdotto catProdotto1 = new CatProdotto();
        catProdotto1.setId(1L);
        CatProdotto catProdotto2 = new CatProdotto();
        catProdotto2.setId(catProdotto1.getId());
        assertThat(catProdotto1).isEqualTo(catProdotto2);
        catProdotto2.setId(2L);
        assertThat(catProdotto1).isNotEqualTo(catProdotto2);
        catProdotto1.setId(null);
        assertThat(catProdotto1).isNotEqualTo(catProdotto2);
    }
}
