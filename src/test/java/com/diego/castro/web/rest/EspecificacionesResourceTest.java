package com.diego.castro.web.rest;

import com.diego.castro.Application;
import com.diego.castro.domain.Especificaciones;
import com.diego.castro.repository.EspecificacionesRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the EspecificacionesResource REST controller.
 *
 * @see EspecificacionesResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class EspecificacionesResourceTest {


    private static final Integer DEFAULT_REBOTES = 0;
    private static final Integer UPDATED_REBOTES = 1;

    private static final Integer DEFAULT_PASES = 0;
    private static final Integer UPDATED_PASES = 1;

    private static final Integer DEFAULT_PUNTOS = 0;
    private static final Integer UPDATED_PUNTOS = 1;

    private static final Integer DEFAULT_FALTAS = 0;
    private static final Integer UPDATED_FALTAS = 1;

    @Inject
    private EspecificacionesRepository especificacionesRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private MockMvc restEspecificacionesMockMvc;

    private Especificaciones especificaciones;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EspecificacionesResource especificacionesResource = new EspecificacionesResource();
        ReflectionTestUtils.setField(especificacionesResource, "especificacionesRepository", especificacionesRepository);
        this.restEspecificacionesMockMvc = MockMvcBuilders.standaloneSetup(especificacionesResource).setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        especificaciones = new Especificaciones();
        especificaciones.setRebotes(DEFAULT_REBOTES);
        especificaciones.setPases(DEFAULT_PASES);
        especificaciones.setPuntos(DEFAULT_PUNTOS);
        especificaciones.setFaltas(DEFAULT_FALTAS);
    }

    @Test
    @Transactional
    public void createEspecificaciones() throws Exception {
        int databaseSizeBeforeCreate = especificacionesRepository.findAll().size();

        // Create the Especificaciones

        restEspecificacionesMockMvc.perform(post("/api/especificacioness")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(especificaciones)))
                .andExpect(status().isCreated());

        // Validate the Especificaciones in the database
        List<Especificaciones> especificacioness = especificacionesRepository.findAll();
        assertThat(especificacioness).hasSize(databaseSizeBeforeCreate + 1);
        Especificaciones testEspecificaciones = especificacioness.get(especificacioness.size() - 1);
        assertThat(testEspecificaciones.getRebotes()).isEqualTo(DEFAULT_REBOTES);
        assertThat(testEspecificaciones.getPases()).isEqualTo(DEFAULT_PASES);
        assertThat(testEspecificaciones.getPuntos()).isEqualTo(DEFAULT_PUNTOS);
        assertThat(testEspecificaciones.getFaltas()).isEqualTo(DEFAULT_FALTAS);
    }

    @Test
    @Transactional
    public void getAllEspecificacioness() throws Exception {
        // Initialize the database
        especificacionesRepository.saveAndFlush(especificaciones);

        // Get all the especificacioness
        restEspecificacionesMockMvc.perform(get("/api/especificacioness"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(especificaciones.getId().intValue())))
                .andExpect(jsonPath("$.[*].rebotes").value(hasItem(DEFAULT_REBOTES)))
                .andExpect(jsonPath("$.[*].pases").value(hasItem(DEFAULT_PASES)))
                .andExpect(jsonPath("$.[*].puntos").value(hasItem(DEFAULT_PUNTOS)))
                .andExpect(jsonPath("$.[*].faltas").value(hasItem(DEFAULT_FALTAS)));
    }

    @Test
    @Transactional
    public void getEspecificaciones() throws Exception {
        // Initialize the database
        especificacionesRepository.saveAndFlush(especificaciones);

        // Get the especificaciones
        restEspecificacionesMockMvc.perform(get("/api/especificacioness/{id}", especificaciones.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(especificaciones.getId().intValue()))
            .andExpect(jsonPath("$.rebotes").value(DEFAULT_REBOTES))
            .andExpect(jsonPath("$.pases").value(DEFAULT_PASES))
            .andExpect(jsonPath("$.puntos").value(DEFAULT_PUNTOS))
            .andExpect(jsonPath("$.faltas").value(DEFAULT_FALTAS));
    }

    @Test
    @Transactional
    public void getNonExistingEspecificaciones() throws Exception {
        // Get the especificaciones
        restEspecificacionesMockMvc.perform(get("/api/especificacioness/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEspecificaciones() throws Exception {
        // Initialize the database
        especificacionesRepository.saveAndFlush(especificaciones);

		int databaseSizeBeforeUpdate = especificacionesRepository.findAll().size();

        // Update the especificaciones
        especificaciones.setRebotes(UPDATED_REBOTES);
        especificaciones.setPases(UPDATED_PASES);
        especificaciones.setPuntos(UPDATED_PUNTOS);
        especificaciones.setFaltas(UPDATED_FALTAS);
        

        restEspecificacionesMockMvc.perform(put("/api/especificacioness")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(especificaciones)))
                .andExpect(status().isOk());

        // Validate the Especificaciones in the database
        List<Especificaciones> especificacioness = especificacionesRepository.findAll();
        assertThat(especificacioness).hasSize(databaseSizeBeforeUpdate);
        Especificaciones testEspecificaciones = especificacioness.get(especificacioness.size() - 1);
        assertThat(testEspecificaciones.getRebotes()).isEqualTo(UPDATED_REBOTES);
        assertThat(testEspecificaciones.getPases()).isEqualTo(UPDATED_PASES);
        assertThat(testEspecificaciones.getPuntos()).isEqualTo(UPDATED_PUNTOS);
        assertThat(testEspecificaciones.getFaltas()).isEqualTo(UPDATED_FALTAS);
    }

    @Test
    @Transactional
    public void deleteEspecificaciones() throws Exception {
        // Initialize the database
        especificacionesRepository.saveAndFlush(especificaciones);

		int databaseSizeBeforeDelete = especificacionesRepository.findAll().size();

        // Get the especificaciones
        restEspecificacionesMockMvc.perform(delete("/api/especificacioness/{id}", especificaciones.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Especificaciones> especificacioness = especificacionesRepository.findAll();
        assertThat(especificacioness).hasSize(databaseSizeBeforeDelete - 1);
    }
}
