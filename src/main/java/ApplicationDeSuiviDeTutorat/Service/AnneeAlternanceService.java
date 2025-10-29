package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.Entities.AnneeAcademique;
import ApplicationDeSuiviDeTutorat.Repository.AnneeAcademiqueRepository;
import ApplicationDeSuiviDeTutorat.Repository.AnneeAlternanceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnneeAlternanceService {
    AnneeAlternanceRepository anneeAlternanceRepository;
    AnneeAcademiqueRepository anneeAcademiqueRepository;

    public AnneeAlternanceService(){

    }

    public void creationAnneeAcademique(AnneeAcademique anneeAcademique){
        anneeAcademiqueRepository.save(anneeAcademique);
    }

    public void changementAnneeAcademique(Long newAnneeAcademiqueID, Long oldAnneeAcademiqueID){
        Optional<AnneeAcademique> newCurrent = anneeAcademiqueRepository.findById(newAnneeAcademiqueID);


    }
}
