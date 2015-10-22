package com.pedro.quiz.web.rest;

import com.pedro.quiz.Application;
import com.pedro.quiz.domain.Reponse_joueur;
import com.pedro.quiz.repository.Reponse_joueurRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
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
 * Test class for the Reponse_joueurResource REST controller.
 *
 * @see Reponse_joueurResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class Reponse_joueurResourceTest {

    private static final String DEFAULT_LABEL = "AAAAA";
    private static final String UPDATED_LABEL = "BBBBB";
    private static final String DEFAULT_LABEL_REPONSE = "AAAAA";
    private static final String UPDATED_LABEL_REPONSE = "BBBBB";

    private static final Boolean DEFAULT_REPONSE_OK = false;
    private static final Boolean UPDATED_REPONSE_OK = true;

    @Inject
    private Reponse_joueurRepository reponse_joueurRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restReponse_joueurMockMvc;

    private Reponse_joueur reponse_joueur;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Reponse_joueurResource reponse_joueurResource = new Reponse_joueurResource();
        ReflectionTestUtils.setField(reponse_joueurResource, "reponse_joueurRepository", reponse_joueurRepository);
        this.restReponse_joueurMockMvc = MockMvcBuilders.standaloneSetup(reponse_joueurResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        reponse_joueur = new Reponse_joueur();
        reponse_joueur.setLabel(DEFAULT_LABEL);
        reponse_joueur.setLabel_reponse(DEFAULT_LABEL_REPONSE);
        reponse_joueur.setReponse_ok(DEFAULT_REPONSE_OK);
    }

    @Test
    @Transactional
    public void createReponse_joueur() throws Exception {
        int databaseSizeBeforeCreate = reponse_joueurRepository.findAll().size();

        // Create the Reponse_joueur

        restReponse_joueurMockMvc.perform(post("/api/reponse_joueurs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reponse_joueur)))
                .andExpect(status().isCreated());

        // Validate the Reponse_joueur in the database
        List<Reponse_joueur> reponse_joueurs = reponse_joueurRepository.findAll();
        assertThat(reponse_joueurs).hasSize(databaseSizeBeforeCreate + 1);
        Reponse_joueur testReponse_joueur = reponse_joueurs.get(reponse_joueurs.size() - 1);
        assertThat(testReponse_joueur.getLabel()).isEqualTo(DEFAULT_LABEL);
        assertThat(testReponse_joueur.getLabel_reponse()).isEqualTo(DEFAULT_LABEL_REPONSE);
        assertThat(testReponse_joueur.getReponse_ok()).isEqualTo(DEFAULT_REPONSE_OK);
    }

    @Test
    @Transactional
    public void getAllReponse_joueurs() throws Exception {
        // Initialize the database
        reponse_joueurRepository.saveAndFlush(reponse_joueur);

        // Get all the reponse_joueurs
        restReponse_joueurMockMvc.perform(get("/api/reponse_joueurs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(reponse_joueur.getId().intValue())))
                .andExpect(jsonPath("$.[*].label").value(hasItem(DEFAULT_LABEL.toString())))
                .andExpect(jsonPath("$.[*].label_reponse").value(hasItem(DEFAULT_LABEL_REPONSE.toString())))
                .andExpect(jsonPath("$.[*].reponse_ok").value(hasItem(DEFAULT_REPONSE_OK.booleanValue())));
    }

    @Test
    @Transactional
    public void getReponse_joueur() throws Exception {
        // Initialize the database
        reponse_joueurRepository.saveAndFlush(reponse_joueur);

        // Get the reponse_joueur
        restReponse_joueurMockMvc.perform(get("/api/reponse_joueurs/{id}", reponse_joueur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(reponse_joueur.getId().intValue()))
            .andExpect(jsonPath("$.label").value(DEFAULT_LABEL.toString()))
            .andExpect(jsonPath("$.label_reponse").value(DEFAULT_LABEL_REPONSE.toString()))
            .andExpect(jsonPath("$.reponse_ok").value(DEFAULT_REPONSE_OK.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingReponse_joueur() throws Exception {
        // Get the reponse_joueur
        restReponse_joueurMockMvc.perform(get("/api/reponse_joueurs/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReponse_joueur() throws Exception {
        // Initialize the database
        reponse_joueurRepository.saveAndFlush(reponse_joueur);

		int databaseSizeBeforeUpdate = reponse_joueurRepository.findAll().size();

        // Update the reponse_joueur
        reponse_joueur.setLabel(UPDATED_LABEL);
        reponse_joueur.setLabel_reponse(UPDATED_LABEL_REPONSE);
        reponse_joueur.setReponse_ok(UPDATED_REPONSE_OK);

        restReponse_joueurMockMvc.perform(put("/api/reponse_joueurs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reponse_joueur)))
                .andExpect(status().isOk());

        // Validate the Reponse_joueur in the database
        List<Reponse_joueur> reponse_joueurs = reponse_joueurRepository.findAll();
        assertThat(reponse_joueurs).hasSize(databaseSizeBeforeUpdate);
        Reponse_joueur testReponse_joueur = reponse_joueurs.get(reponse_joueurs.size() - 1);
        assertThat(testReponse_joueur.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testReponse_joueur.getLabel_reponse()).isEqualTo(UPDATED_LABEL_REPONSE);
        assertThat(testReponse_joueur.getReponse_ok()).isEqualTo(UPDATED_REPONSE_OK);
    }

    @Test
    @Transactional
    public void deleteReponse_joueur() throws Exception {
        // Initialize the database
        reponse_joueurRepository.saveAndFlush(reponse_joueur);

		int databaseSizeBeforeDelete = reponse_joueurRepository.findAll().size();

        // Get the reponse_joueur
        restReponse_joueurMockMvc.perform(delete("/api/reponse_joueurs/{id}", reponse_joueur.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Reponse_joueur> reponse_joueurs = reponse_joueurRepository.findAll();
        assertThat(reponse_joueurs).hasSize(databaseSizeBeforeDelete - 1);
    }
}
