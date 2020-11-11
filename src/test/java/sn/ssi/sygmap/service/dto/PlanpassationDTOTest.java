package sn.ssi.sygmap.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import sn.ssi.sygmap.web.rest.TestUtil;

public class PlanpassationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanpassationDTO.class);
        PlanpassationDTO planpassationDTO1 = new PlanpassationDTO();
        planpassationDTO1.setId(1L);
        PlanpassationDTO planpassationDTO2 = new PlanpassationDTO();
        assertThat(planpassationDTO1).isNotEqualTo(planpassationDTO2);
        planpassationDTO2.setId(planpassationDTO1.getId());
        assertThat(planpassationDTO1).isEqualTo(planpassationDTO2);
        planpassationDTO2.setId(2L);
        assertThat(planpassationDTO1).isNotEqualTo(planpassationDTO2);
        planpassationDTO1.setId(null);
        assertThat(planpassationDTO1).isNotEqualTo(planpassationDTO2);
    }
}
