package sn.ssi.sygmap.service.impl;

import sn.ssi.sygmap.service.PlanpassationService;
import sn.ssi.sygmap.domain.Planpassation;
import sn.ssi.sygmap.repository.PlanpassationRepository;
import sn.ssi.sygmap.service.dto.PlanpassationDTO;
import sn.ssi.sygmap.service.mapper.PlanpassationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Planpassation}.
 */
@Service
@Transactional
public class PlanpassationServiceImpl implements PlanpassationService {

    private final Logger log = LoggerFactory.getLogger(PlanpassationServiceImpl.class);

    private final PlanpassationRepository planpassationRepository;

    private final PlanpassationMapper planpassationMapper;

    public PlanpassationServiceImpl(PlanpassationRepository planpassationRepository, PlanpassationMapper planpassationMapper) {
        this.planpassationRepository = planpassationRepository;
        this.planpassationMapper = planpassationMapper;
    }

    @Override
    public PlanpassationDTO save(PlanpassationDTO planpassationDTO) {
        log.debug("Request to save Planpassation : {}", planpassationDTO);
        Planpassation planpassation = planpassationMapper.toEntity(planpassationDTO);
        planpassation = planpassationRepository.save(planpassation);
        return planpassationMapper.toDto(planpassation);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PlanpassationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Planpassations");
        return planpassationRepository.findAll(pageable)
            .map(planpassationMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PlanpassationDTO> findOne(Long id) {
        log.debug("Request to get Planpassation : {}", id);
        return planpassationRepository.findById(id)
            .map(planpassationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Planpassation : {}", id);
        planpassationRepository.deleteById(id);
    }
}
