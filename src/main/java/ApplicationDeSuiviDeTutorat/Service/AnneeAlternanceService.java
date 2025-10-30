package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiAnneeAlternanceDTO;
import ApplicationDeSuiviDeTutorat.Models.Entities.AnneeAcademique;
import ApplicationDeSuiviDeTutorat.Models.Entities.AnneeAlternance;
import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Entities.Mission;
import ApplicationDeSuiviDeTutorat.Models.Enums.EtatApprenti;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import ApplicationDeSuiviDeTutorat.Repository.AnneeAcademiqueRepository;
import ApplicationDeSuiviDeTutorat.Repository.AnneeAlternanceRepository;
import ApplicationDeSuiviDeTutorat.Repository.ApprentiRepository;
import ApplicationDeSuiviDeTutorat.Repository.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnneeAlternanceService {
    private final AnneeAlternanceRepository anneeAlternanceRepository;
    private final AnneeAcademiqueRepository anneeAcademiqueRepository;
    private final ApprentiRepository apprentiRepository;
    private final MissionRepository missionRepository;

    public AnneeAlternanceService(
            AnneeAlternanceRepository anneeAlternanceRepository, AnneeAcademiqueRepository anneeAcademiqueRepository, ApprentiRepository apprentiRepository, MissionRepository missionRepository) {
        this.anneeAlternanceRepository = anneeAlternanceRepository;
        this.anneeAcademiqueRepository = anneeAcademiqueRepository;
        this.apprentiRepository = apprentiRepository;
        this.missionRepository = missionRepository;
    }

    public void creationAnneeAcademique(ApprentiAnneeAlternanceDTO apprentiAnneeAlternanceDTO, Apprenti apprenti) {
        AnneeAlternance anneeAlternance = new AnneeAlternance();

        anneeAlternance.setApprenti(apprenti);
        anneeAlternance.setAnneeAcademique(apprentiAnneeAlternanceDTO.getAnneeAcademique());
        anneeAlternance.setProgramme(apprentiAnneeAlternanceDTO.getProgramme());
        anneeAlternance.setEntreprise(apprentiAnneeAlternanceDTO.getEntreprise());
        anneeAlternance.setTuteurEntreprise(apprentiAnneeAlternanceDTO.getTuteurEntreprise());
        anneeAlternance.setTuteurPedagogique(apprentiAnneeAlternanceDTO.getTuteurPedagogique());
        anneeAlternance.setMission(apprentiAnneeAlternanceDTO.getMission());

        anneeAlternanceRepository.save(anneeAlternance);
    }

    // Determine la valeur suivante de Programme
    private Programme getNextProgramme(Programme currentProgramme) {
        Programme[] allProgrammes = Programme.values();
        int currentOrdinal = currentProgramme.ordinal();
        int maxOrdinal = allProgrammes.length - 1;
        return (currentOrdinal < maxOrdinal) ? allProgrammes[currentOrdinal + 1] : currentProgramme;
    }

    // Cree une copie avec la nouvelle annee academique
    private AnneeAlternance createUpdatedCopy(AnneeAlternance oldAA, AnneeAcademique targetYear, MissionRepository missionRepo) {
        AnneeAlternance newAA = new AnneeAlternance();
        newAA.setApprenti(oldAA.getApprenti());
        newAA.setEntreprise(oldAA.getEntreprise());
        newAA.setTuteurEntreprise(oldAA.getTuteurEntreprise());
        newAA.setTuteurPedagogique(oldAA.getTuteurPedagogique());

        // Variable change
        newAA.setProgramme(getNextProgramme(oldAA.getProgramme()));
        newAA.setAnneeAcademique(targetYear);
        if (oldAA.getMission() != null) {
            Mission oldMission = oldAA.getMission();
            Mission newMission = new Mission();
            newMission.setCommentaires(oldMission.getCommentaires() + " - Copie de l'annee precedente");
            newMission = missionRepo.save(newMission);
            newAA.setMission(newMission);
        }
        return newAA;
    }

    public void changementAnneeAcademique(Long newAnneeAcademiqueID){
        Optional<AnneeAcademique> newYearOpt = anneeAcademiqueRepository.findById(newAnneeAcademiqueID);

        if (!newYearOpt.isPresent()) {
            throw new IllegalArgumentException("Target AnneeAcademique ID not found.");
        }
        AnneeAcademique targetYear = newYearOpt.get();

        List<AnneeAlternance> currentActiveAlternances = anneeAlternanceRepository.findLastForAllActif();

        List<AnneeAlternance> newAlternancesToSave = new ArrayList<>();

        Programme[] allProgrammes = Programme.values();
        int maxOrdinal = allProgrammes.length - 1;

        for (AnneeAlternance currentAA : currentActiveAlternances) {
            Apprenti apprenti = currentAA.getApprenti();

            if (apprenti == null || !apprenti.getEtat().equals(EtatApprenti.ACTIF)) {
                continue;
            }

            Programme currentProgramme = currentAA.getProgramme();
            int currentOrdinal = currentProgramme.ordinal();

            if (currentOrdinal == maxOrdinal) {
                if (!apprenti.getEtat().equals(EtatApprenti.DIPLOME)) {
                    apprenti.setEtat(EtatApprenti.DIPLOME);
                    apprentiRepository.save(apprenti);
                    System.out.println("Apprenti " + apprenti.getId() + " est maintenant diplome.");
                }

            } else {
                AnneeAlternance newAA = createUpdatedCopy(currentAA, targetYear, this.missionRepository);
                newAlternancesToSave.add(newAA);
            }
        }

        if (!newAlternancesToSave.isEmpty()) {
            anneeAlternanceRepository.saveAll(newAlternancesToSave);
            System.out.println("Creation de " + newAlternancesToSave.size() + " nouvelles entree d'anneeAlternance pour les apprenties actifs.");
        }
    }
}

