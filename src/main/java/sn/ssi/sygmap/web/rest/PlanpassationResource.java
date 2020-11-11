package sn.ssi.sygmap.web.rest;

import sn.ssi.sygmap.service.PlanpassationService;
import sn.ssi.sygmap.web.rest.errors.BadRequestAlertException;
import sn.ssi.sygmap.service.dto.PlanpassationDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link sn.ssi.sygmap.domain.Planpassation}.
 */
@RestController
@RequestMapping("/api")
public class PlanpassationResource {

    private final Logger log = LoggerFactory.getLogger(PlanpassationResource.class);

    private static final String ENTITY_NAME = "planpassation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlanpassationService planpassationService;

    public PlanpassationResource(PlanpassationService planpassationService) {
        this.planpassationService = planpassationService;
    }

    /**
     * {@code POST  /planpassations} : Create a new planpassation.
     *
     * @param planpassationDTO the planpassationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new planpassationDTO, or with status {@code 400 (Bad Request)} if the planpassation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/planpassations")
    public ResponseEntity<PlanpassationDTO> createPlanpassation(@RequestBody PlanpassationDTO planpassationDTO) throws URISyntaxException {
        log.debug("REST request to save Planpassation : {}", planpassationDTO);
        if (planpassationDTO.getId() != null) {
            throw new BadRequestAlertException("A new planpassation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanpassationDTO result = planpassationService.save(planpassationDTO);
        return ResponseEntity.created(new URI("/api/planpassations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /planpassations} : Updates an existing planpassation.
     *
     * @param planpassationDTO the planpassationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planpassationDTO,
     * or with status {@code 400 (Bad Request)} if the planpassationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the planpassationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/planpassations")
    public ResponseEntity<PlanpassationDTO> updatePlanpassation(@RequestBody PlanpassationDTO planpassationDTO) throws URISyntaxException {
        log.debug("REST request to update Planpassation : {}", planpassationDTO);
        if (planpassationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanpassationDTO result = planpassationService.save(planpassationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, planpassationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /planpassations} : get all the planpassations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of planpassations in body.
     */
    @GetMapping("/planpassations")
    public ResponseEntity<List<PlanpassationDTO>> getAllPlanpassations(Pageable pageable) {
        log.debug("REST request to get a page of Planpassations");
        Page<PlanpassationDTO> page = planpassationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /planpassations/:id} : get the "id" planpassation.
     *
     * @param id the id of the planpassationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the planpassationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/planpassations/{id}")
    public ResponseEntity<PlanpassationDTO> getPlanpassation(@PathVariable Long id) {
        log.debug("REST request to get Planpassation : {}", id);
        Optional<PlanpassationDTO> planpassationDTO = planpassationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planpassationDTO);
    }

    /**
     * {@code DELETE  /planpassations/:id} : delete the "id" planpassation.
     *
     * @param id the id of the planpassationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/planpassations/{id}")
    public ResponseEntity<Void> deletePlanpassation(@PathVariable Long id) {
        log.debug("REST request to delete Planpassation : {}", id);
        planpassationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
