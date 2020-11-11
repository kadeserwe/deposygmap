package sn.ssi.sygmap.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import sn.ssi.sygmap.web.rest.TestUtil;

public class PlanpassationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Planpassation.class);
        Planpassation planpassation1 = new Planpassation();
        planpassation1.setId(1L);
        Planpassation planpassation2 = new Planpassation();
        planpassation2.setId(planpassation1.getId());
        assertThat(planpassation1).isEqualTo(planpassation2);
        planpassation2.setId(2L);
        assertThat(planpassation1).isNotEqualTo(planpassation2);
        planpassation1.setId(null);
        assertThat(planpassation1).isNotEqualTo(planpassation2);
    }
}
