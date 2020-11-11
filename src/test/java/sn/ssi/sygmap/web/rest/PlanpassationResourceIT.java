package sn.ssi.sygmap.web.rest;

import sn.ssi.sygmap.SygmapApp;
import sn.ssi.sygmap.domain.Planpassation;
import sn.ssi.sygmap.repository.PlanpassationRepository;
import sn.ssi.sygmap.service.PlanpassationService;
import sn.ssi.sygmap.service.dto.PlanpassationDTO;
import sn.ssi.sygmap.service.mapper.PlanpassationMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PlanpassationResource} REST controller.
 */
@SpringBootTest(classes = SygmapApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PlanpassationResourceIT {

    private static final LocalDate DEFAULT_DATE_DEBUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEBUT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COMMENTAIRE = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTAIRE = "BBBBBBBBBB";

    @Autowired
    private PlanpassationRepository planpassationRepository;

    @Autowired
    private PlanpassationMapper planpassationMapper;

    @Autowired
    private PlanpassationService planpassationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlanpassationMockMvc;

    private Planpassation planpassation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Planpassation createEntity(EntityManager em) {
        Planpassation planpassation = new Planpassation()
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN)
            .commentaire(DEFAULT_COMMENTAIRE);
        return planpassation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Planpassation createUpdatedEntity(EntityManager em) {
        Planpassation planpassation = new Planpassation()
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .commentaire(UPDATED_COMMENTAIRE);
        return planpassation;
    }

    @BeforeEach
    public void initTest() {
        planpassation = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanpassation() throws Exception {
        int databaseSizeBeforeCreate = planpassationRepository.findAll().size();
        // Create the Planpassation
        PlanpassationDTO planpassationDTO = planpassationMapper.toDto(planpassation);
        restPlanpassationMockMvc.perform(post("/api/planpassations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(planpassationDTO)))
            .andExpect(status().isCreated());

        // Validate the Planpassation in the database
        List<Planpassation> planpassationList = planpassationRepository.findAll();
        assertThat(planpassationList).hasSize(databaseSizeBeforeCreate + 1);
        Planpassation testPlanpassation = planpassationList.get(planpassationList.size() - 1);
        assertThat(testPlanpassation.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testPlanpassation.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
        assertThat(testPlanpassation.getCommentaire()).isEqualTo(DEFAULT_COMMENTAIRE);
    }

    @Test
    @Transactional
    public void createPlanpassationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planpassationRepository.findAll().size();

        // Create the Planpassation with an existing ID
        planpassation.setId(1L);
        PlanpassationDTO planpassationDTO = planpassationMapper.toDto(planpassation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanpassationMockMvc.perform(post("/api/planpassations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(planpassationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Planpassation in the database
        List<Planpassation> planpassationList = planpassationRepository.findAll();
        assertThat(planpassationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPlanpassations() throws Exception {
        // Initialize the database
        planpassationRepository.saveAndFlush(planpassation);

        // Get all the planpassationList
        restPlanpassationMockMvc.perform(get("/api/planpassations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planpassation.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(DEFAULT_DATE_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(DEFAULT_DATE_FIN.toString())))
            .andExpect(jsonPath("$.[*].commentaire").value(hasItem(DEFAULT_COMMENTAIRE)));
    }
    
    @Test
    @Transactional
    public void getPlanpassation() throws Exception {
        // Initialize the database
        planpassationRepository.saveAndFlush(planpassation);

        // Get the planpassation
        restPlanpassationMockMvc.perform(get("/api/planpassations/{id}", planpassation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(planpassation.getId().intValue()))
            .andExpect(jsonPath("$.dateDebut").value(DEFAULT_DATE_DEBUT.toString()))
            .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()))
            .andExpect(jsonPath("$.commentaire").value(DEFAULT_COMMENTAIRE));
    }
    @Test
    @Transactional
    public void getNonExistingPlanpassation() throws Exception {
        // Get the planpassation
        restPlanpassationMockMvc.perform(get("/api/planpassations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanpassation() throws Exception {
        // Initialize the database
        planpassationRepository.saveAndFlush(planpassation);

        int databaseSizeBeforeUpdate = planpassationRepository.findAll().size();

        // Update the planpassation
        Planpassation updatedPlanpassation = planpassationRepository.findById(planpassation.getId()).get();
        // Disconnect from session so that the updates on updatedPlanpassation are not directly saved in db
        em.detach(updatedPlanpassation);
        updatedPlanpassation
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .commentaire(UPDATED_COMMENTAIRE);
        PlanpassationDTO planpassationDTO = planpassationMapper.toDto(updatedPlanpassation);

        restPlanpassationMockMvc.perform(put("/api/planpassations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(planpassationDTO)))
            .andExpect(status().isOk());

        // Validate the Planpassation in the database
        List<Planpassation> planpassationList = planpassationRepository.findAll();
        assertThat(planpassationList).hasSize(databaseSizeBeforeUpdate);
        Planpassation testPlanpassation = planpassationList.get(planpassationList.size() - 1);
        assertThat(testPlanpassation.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testPlanpassation.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testPlanpassation.getCommentaire()).isEqualTo(UPDATED_COMMENTAIRE);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanpassation() throws Exception {
        int databaseSizeBeforeUpdate = planpassationRepository.findAll().size();

        // Create the Planpassation
        PlanpassationDTO planpassationDTO = planpassationMapper.toDto(planpassation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanpassationMockMvc.perform(put("/api/planpassations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(planpassationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Planpassation in the database
        List<Planpassation> planpassationList = planpassationRepository.findAll();
        assertThat(planpassationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlanpassation() throws Exception {
        // Initialize the database
        planpassationRepository.saveAndFlush(planpassation);

        int databaseSizeBeforeDelete = planpassationRepository.findAll().size();

        // Delete the planpassation
        restPlanpassationMockMvc.perform(delete("/api/planpassations/{id}", planpassation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Planpassation> planpassationList = planpassationRepository.findAll();
        assertThat(planpassationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
