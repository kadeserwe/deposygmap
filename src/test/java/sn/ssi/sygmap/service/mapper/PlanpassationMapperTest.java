package sn.ssi.sygmap.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PlanpassationMapperTest {

    private PlanpassationMapper planpassationMapper;

    @BeforeEach
    public void setUp() {
        planpassationMapper = new PlanpassationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(planpassationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(planpassationMapper.fromId(null)).isNull();
    }
}
