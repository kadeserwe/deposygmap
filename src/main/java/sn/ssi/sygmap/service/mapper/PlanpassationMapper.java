package sn.ssi.sygmap.service.mapper;


import sn.ssi.sygmap.domain.*;
import sn.ssi.sygmap.service.dto.PlanpassationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Planpassation} and its DTO {@link PlanpassationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlanpassationMapper extends EntityMapper<PlanpassationDTO, Planpassation> {



    default Planpassation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Planpassation planpassation = new Planpassation();
        planpassation.setId(id);
        return planpassation;
    }
}
