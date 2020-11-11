package sn.ssi.sygmap.service;

import sn.ssi.sygmap.service.dto.PlanpassationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link sn.ssi.sygmap.domain.Planpassation}.
 */
public interface PlanpassationService {

    /**
     * Save a planpassation.
     *
     * @param planpassationDTO the entity to save.
     * @return the persisted entity.
     */
    PlanpassationDTO save(PlanpassationDTO planpassationDTO);

    /**
     * Get all the planpassations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PlanpassationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" planpassation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PlanpassationDTO> findOne(Long id);

    /**
     * Delete the "id" planpassation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
