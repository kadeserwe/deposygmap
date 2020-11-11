package sn.ssi.sygmap.repository;

import sn.ssi.sygmap.domain.Planpassation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Planpassation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanpassationRepository extends JpaRepository<Planpassation, Long> {
}
