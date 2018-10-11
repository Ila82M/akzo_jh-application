package com.serijakala.sj4.akzo.akzonobel.web.rest;

import com.serijakala.sj4.akzo.akzonobel.AkzoJhApplicationApp;

import com.serijakala.sj4.akzo.akzonobel.domain.Provincia;
import com.serijakala.sj4.akzo.akzonobel.repository.ProvinciaRepository;
import com.serijakala.sj4.akzo.akzonobel.service.ProvinciaService;
import com.serijakala.sj4.akzo.akzonobel.service.dto.ProvinciaDTO;
import com.serijakala.sj4.akzo.akzonobel.service.mapper.ProvinciaMapper;
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
 * Test class for the ProvinciaResource REST controller.
 *
 * @see ProvinciaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkzoJhApplicationApp.class)
public class ProvinciaResourceIntTest {

    private static final Long DEFAULT_COD_PROVINCIA = 1L;
    private static final Long UPDATED_COD_PROVINCIA = 2L;

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_SIGLA = "AAAAAAAAAA";
    private static final String UPDATED_SIGLA = "BBBBBBBBBB";

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private ProvinciaMapper provinciaMapper;
    
    @Autowired
    private ProvinciaService provinciaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProvinciaMockMvc;

    private Provincia provincia;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProvinciaResource provinciaResource = new ProvinciaResource(provinciaService);
        this.restProvinciaMockMvc = MockMvcBuilders.standaloneSetup(provinciaResource)
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
    public static Provincia createEntity(EntityManager em) {
        Provincia provincia = new Provincia()
            .codProvincia(DEFAULT_COD_PROVINCIA)
            .nome(DEFAULT_NOME)
            .sigla(DEFAULT_SIGLA);
        return provincia;
    }

    @Before
    public void initTest() {
        provincia = createEntity(em);
    }

    @Test
    @Transactional
    public void createProvincia() throws Exception {
        int databaseSizeBeforeCreate = provinciaRepository.findAll().size();

        // Create the Provincia
        ProvinciaDTO provinciaDTO = provinciaMapper.toDto(provincia);
        restProvinciaMockMvc.perform(post("/api/provincias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinciaDTO)))
            .andExpect(status().isCreated());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeCreate + 1);
        Provincia testProvincia = provinciaList.get(provinciaList.size() - 1);
        assertThat(testProvincia.getCodProvincia()).isEqualTo(DEFAULT_COD_PROVINCIA);
        assertThat(testProvincia.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testProvincia.getSigla()).isEqualTo(DEFAULT_SIGLA);
    }

    @Test
    @Transactional
    public void createProvinciaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = provinciaRepository.findAll().size();

        // Create the Provincia with an existing ID
        provincia.setId(1L);
        ProvinciaDTO provinciaDTO = provinciaMapper.toDto(provincia);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProvinciaMockMvc.perform(post("/api/provincias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinciaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProvincias() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        // Get all the provinciaList
        restProvinciaMockMvc.perform(get("/api/provincias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(provincia.getId().intValue())))
            .andExpect(jsonPath("$.[*].codProvincia").value(hasItem(DEFAULT_COD_PROVINCIA.intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].sigla").value(hasItem(DEFAULT_SIGLA.toString())));
    }
    
    @Test
    @Transactional
    public void getProvincia() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        // Get the provincia
        restProvinciaMockMvc.perform(get("/api/provincias/{id}", provincia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(provincia.getId().intValue()))
            .andExpect(jsonPath("$.codProvincia").value(DEFAULT_COD_PROVINCIA.intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.sigla").value(DEFAULT_SIGLA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProvincia() throws Exception {
        // Get the provincia
        restProvinciaMockMvc.perform(get("/api/provincias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProvincia() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        int databaseSizeBeforeUpdate = provinciaRepository.findAll().size();

        // Update the provincia
        Provincia updatedProvincia = provinciaRepository.findById(provincia.getId()).get();
        // Disconnect from session so that the updates on updatedProvincia are not directly saved in db
        em.detach(updatedProvincia);
        updatedProvincia
            .codProvincia(UPDATED_COD_PROVINCIA)
            .nome(UPDATED_NOME)
            .sigla(UPDATED_SIGLA);
        ProvinciaDTO provinciaDTO = provinciaMapper.toDto(updatedProvincia);

        restProvinciaMockMvc.perform(put("/api/provincias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinciaDTO)))
            .andExpect(status().isOk());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeUpdate);
        Provincia testProvincia = provinciaList.get(provinciaList.size() - 1);
        assertThat(testProvincia.getCodProvincia()).isEqualTo(UPDATED_COD_PROVINCIA);
        assertThat(testProvincia.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testProvincia.getSigla()).isEqualTo(UPDATED_SIGLA);
    }

    @Test
    @Transactional
    public void updateNonExistingProvincia() throws Exception {
        int databaseSizeBeforeUpdate = provinciaRepository.findAll().size();

        // Create the Provincia
        ProvinciaDTO provinciaDTO = provinciaMapper.toDto(provincia);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProvinciaMockMvc.perform(put("/api/provincias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinciaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProvincia() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        int databaseSizeBeforeDelete = provinciaRepository.findAll().size();

        // Get the provincia
        restProvinciaMockMvc.perform(delete("/api/provincias/{id}", provincia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Provincia.class);
        Provincia provincia1 = new Provincia();
        provincia1.setId(1L);
        Provincia provincia2 = new Provincia();
        provincia2.setId(provincia1.getId());
        assertThat(provincia1).isEqualTo(provincia2);
        provincia2.setId(2L);
        assertThat(provincia1).isNotEqualTo(provincia2);
        provincia1.setId(null);
        assertThat(provincia1).isNotEqualTo(provincia2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProvinciaDTO.class);
        ProvinciaDTO provinciaDTO1 = new ProvinciaDTO();
        provinciaDTO1.setId(1L);
        ProvinciaDTO provinciaDTO2 = new ProvinciaDTO();
        assertThat(provinciaDTO1).isNotEqualTo(provinciaDTO2);
        provinciaDTO2.setId(provinciaDTO1.getId());
        assertThat(provinciaDTO1).isEqualTo(provinciaDTO2);
        provinciaDTO2.setId(2L);
        assertThat(provinciaDTO1).isNotEqualTo(provinciaDTO2);
        provinciaDTO1.setId(null);
        assertThat(provinciaDTO1).isNotEqualTo(provinciaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(provinciaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(provinciaMapper.fromId(null)).isNull();
    }
}
