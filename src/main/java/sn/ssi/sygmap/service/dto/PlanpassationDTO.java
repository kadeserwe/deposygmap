package sn.ssi.sygmap.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link sn.ssi.sygmap.domain.Planpassation} entity.
 */
public class PlanpassationDTO implements Serializable {
    
    private Long id;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private String commentaire;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlanpassationDTO)) {
            return false;
        }

        return id != null && id.equals(((PlanpassationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlanpassationDTO{" +
            "id=" + getId() +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", commentaire='" + getCommentaire() + "'" +
            "}";
    }
}
