package ApplicationDeSuiviDeTutorat.Models.DTO;

import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Entities.Entreprise;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;

import java.util.List;

public record DashboardDto(
        Utilisateur utilisateur,
        List<Apprenti> apprentis,
        Programme[] programmes,
        List<Entreprise> entreprises
) {}
