package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.serijakala.sj4.akzo.akzonobel.AkzoJhApplicationApp;

import com.serijakala.sj4.akzo.akzonobel.domain.Comune;
import com.serijakala.sj4.akzo.akzonobel.repository.ComuneRepository;
import com.serijakala.sj4.akzo.akzonobel.service.ComuneService;
import com.serijakala.sj4.akzo.akzonobel.service.dto.ComuneDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.ComuneMapper;
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
 * Test class for the ComuneResource REST controller.
 *
 * @see ComuneResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkzoJhApplicationApp.class)
public class ComuneResourceIntTest {

    private static final Long DEFAULT_COD = 1L;
    private static final Long UPDATED_COD = 2L;

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private ComuneMapper comuneMapper;
    
    @Autowired
    private ComuneService comuneService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restComuneMockMvc;

    private Comune comune;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ComuneResource comuneResource = new ComuneResource(comuneService);
        this.restComuneMockMvc = MockMvcBuilders.standaloneSetup(comuneResource)
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
    public static Comune createEntity(EntityManager em) {
        Comune comune = new Comune()
            .cod(DEFAULT_COD)
            .nome(DEFAULT_NOME);
        return comune;
    }

    @Before
    public void initTest() {
        comune = createEntity(em);
    }

    @Test
    @Transactional
    public void createComune() throws Exception {
        int databaseSizeBeforeCreate = comuneRepository.findAll().size();

        // Create the Comune
        ComuneDTO comuneDTO = comuneMapper.toDto(comune);
        restComuneMockMvc.perform(post("/api/comunes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comuneDTO)))
            .andExpect(status().isCreated());

        // Validate the Comune in the database
        List<Comune> comuneList = comuneRepository.findAll();
        assertThat(comuneList).hasSize(databaseSizeBeforeCreate + 1);
        Comune testComune = comuneList.get(comuneList.size() - 1);
        assertThat(testComune.getCod()).isEqualTo(DEFAULT_COD);
        assertThat(testComune.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createComuneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = comuneRepository.findAll().size();

        // Create the Comune with an existing ID
        comune.setId(1L);
        ComuneDTO comuneDTO = comuneMapper.toDto(comune);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComuneMockMvc.perform(post("/api/comunes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comuneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Comune in the database
        List<Comune> comuneList = comuneRepository.findAll();
        assertThat(comuneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllComunes() throws Exception {
        // Initialize the database
        comuneRepository.saveAndFlush(comune);

        // Get all the comuneList
        restComuneMockMvc.perform(get("/api/comunes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(comune.getId().intValue())))
            .andExpect(jsonPath("$.[*].cod").value(hasItem(DEFAULT_COD.intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
    }
    
    @Test
    @Transactional
    public void getComune() throws Exception {
        // Initialize the database
        comuneRepository.saveAndFlush(comune);

        // Get the comune
        restComuneMockMvc.perform(get("/api/comunes/{id}", comune.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(comune.getId().intValue()))
            .andExpect(jsonPath("$.cod").value(DEFAULT_COD.intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingComune() throws Exception {
        // Get the comune
        restComuneMockMvc.perform(get("/api/comunes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComune() throws Exception {
        // Initialize the database
        comuneRepository.saveAndFlush(comune);

        int databaseSizeBeforeUpdate = comuneRepository.findAll().size();

        // Update the comune
        Comune updatedComune = comuneRepository.findById(comune.getId()).get();
        // Disconnect from session so that the updates on updatedComune are not directly saved in db
        em.detach(updatedComune);
        updatedComune
            .cod(UPDATED_COD)
            .nome(UPDATED_NOME);
        ComuneDTO comuneDTO = comuneMapper.toDto(updatedComune);

        restComuneMockMvc.perform(put("/api/comunes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comuneDTO)))
            .andExpect(status().isOk());

        // Validate the Comune in the database
        List<Comune> comuneList = comuneRepository.findAll();
        assertThat(comuneList).hasSize(databaseSizeBeforeUpdate);
        Comune testComune = comuneList.get(comuneList.size() - 1);
        assertThat(testComune.getCod()).isEqualTo(UPDATED_COD);
        assertThat(testComune.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingComune() throws Exception {
        int databaseSizeBeforeUpdate = comuneRepository.findAll().size();

        // Create the Comune
        ComuneDTO comuneDTO = comuneMapper.toDto(comune);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComuneMockMvc.perform(put("/api/comunes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comuneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Comune in the database
        List<Comune> comuneList = comuneRepository.findAll();
        assertThat(comuneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteComune() throws Exception {
        // Initialize the database
        comuneRepository.saveAndFlush(comune);

        int databaseSizeBeforeDelete = comuneRepository.findAll().size();

        // Get the comune
        restComuneMockMvc.perform(delete("/api/comunes/{id}", comune.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Comune> comuneList = comuneRepository.findAll();
        assertThat(comuneList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Comune.class);
        Comune comune1 = new Comune();
        comune1.setId(1L);
        Comune comune2 = new Comune();
        comune2.setId(comune1.getId());
        assertThat(comune1).isEqualTo(comune2);
        comune2.setId(2L);
        assertThat(comune1).isNotEqualTo(comune2);
        comune1.setId(null);
        assertThat(comune1).isNotEqualTo(comune2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComuneDTO.class);
        ComuneDTO comuneDTO1 = new ComuneDTO();
        comuneDTO1.setId(1L);
        ComuneDTO comuneDTO2 = new ComuneDTO();
        assertThat(comuneDTO1).isNotEqualTo(comuneDTO2);
        comuneDTO2.setId(comuneDTO1.getId());
        assertThat(comuneDTO1).isEqualTo(comuneDTO2);
        comuneDTO2.setId(2L);
        assertThat(comuneDTO1).isNotEqualTo(comuneDTO2);
        comuneDTO1.setId(null);
        assertThat(comuneDTO1).isNotEqualTo(comuneDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(comuneMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(comuneMapper.fromId(null)).isNull();
    }
}
