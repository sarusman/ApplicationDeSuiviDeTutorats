package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiAnneeAlternanceDTO;
import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiDetailDTO;
import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiTabDTO;
import ApplicationDeSuiviDeTutorat.Models.Entities.*;
import ApplicationDeSuiviDeTutorat.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ApprentiService {
    private final ApprentiRepository apprentiRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final AnneeAlternanceRepository anneeAlternanceRepository;
    private final VisiteRepository visiteRepository;
    // Ajout de la dépendance pour l'année académique
    private final AnneeAcademiqueRepository anneeAcademiqueRepository;
    private final AnneeAlternanceService anneeAlternanceService;


    public ApprentiService(ApprentiRepository apprentiRepository,
                           UtilisateurRepository utilisateurRepository,
                           AnneeAlternanceRepository anneeAlternanceRepository,
                           VisiteRepository visiteRepository,
                           AnneeAcademiqueRepository anneeAcademiqueRepository, AnneeAlternanceService anneeAlternanceService) { // Injection de dépendance
        this.apprentiRepository = apprentiRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.anneeAlternanceRepository = anneeAlternanceRepository;
        this.visiteRepository = visiteRepository;
        this.anneeAcademiqueRepository = anneeAcademiqueRepository; // Initialisation
        this.anneeAlternanceService = anneeAlternanceService;
    }

    public List<Apprenti> getAllApprentis() {
        return apprentiRepository.findAll();
    }

    public Optional<Apprenti> getApprentiById(Long id) {
        return Optional.ofNullable(
                apprentiRepository.findById(id).orElseThrow(
                        () -> new IllegalStateException("Apprenti with id " + id + " does not exist")
                )
        );
    }

    @Transactional
    public Apprenti createApprenti(ApprentiAnneeAlternanceDTO dto, Long tuteurId) {
        Apprenti apprenti = new Apprenti();
        apprenti.setNom(dto.getNom());
        apprenti.setPrenom(dto.getPrenom());
        apprenti.setAdresseElectronique(dto.getAdresseElectronique());
        apprenti.setTelephone(dto.getTelephone());

        apprentiRepository.save(apprenti);
        dto.setTuteurPedagogique(utilisateurRepository.findById(tuteurId).orElseThrow());
        anneeAlternanceService.creationAnneeAcademique(dto, apprenti);

        return apprenti;
    }

    @Transactional
    public Apprenti updateApprentiBilanById(Long id, Apprenti updatedApprenti) {
        Apprenti apprentiToUpdate = apprentiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Apprenti non trouvé avec l'id : " + id));

        apprentiToUpdate.setNom(updatedApprenti.getNom());
        apprentiToUpdate.setPrenom(updatedApprenti.getPrenom());
        apprentiToUpdate.setAdresseElectronique(updatedApprenti.getAdresseElectronique());
        apprentiToUpdate.setTelephone(updatedApprenti.getTelephone());

        return apprentiRepository.save(apprentiToUpdate);
    }

    @Transactional
    public void deleteApprentiById(Long id) {
        if (!apprentiRepository.existsById(id)) {
            throw new EntityNotFoundException("Apprenti non trouvé avec l'id : " + id);
        }
        // Il faudrait aussi gérer la suppression en cascade des AnneeAlternance associées
        // selon la configuration de la relation (e.g., orphanRemoval=true)
        apprentiRepository.deleteById(id);
    }

    public boolean existeAdresse(String adresseElectronique) {
        return apprentiRepository.existsByAdresseElectronique(adresseElectronique);
    }

    public boolean existeTelephone(String telephone) {
        return apprentiRepository.existsByTelephone(telephone);
    }

    // Ces deux méthodes dépendent de la logique de votre repository.
    // La requête native doit être adaptée pour chercher les visites
    // à travers les AnneeAlternance liées à l'apprenti.
    public Optional<Visite> findDerniereVisite(Apprenti apprenti) {
        if (apprenti == null || apprenti.getId() == null) return Optional.empty();
        return visiteRepository.findDerniereNative(apprenti.getId(), LocalDate.now());
    }

    public Optional<Visite> findProchaineVisite(Apprenti apprenti) {
        if (apprenti == null || apprenti.getId() == null) return Optional.empty();
        return visiteRepository.findProchaineNative(apprenti.getId(), LocalDate.from(LocalDateTime.now()));
    }

    public List<ApprentiTabDTO> toTabDTO(Long tuteurId) {
        String entrepriseRaisonSociale = "";
        String missionMotsCles = "NA";
        String anneeAcademiqueDisplay = "";
        String programmeStr = "";

        // Fetch the relevant AnneeAlternance to get related entities
        List<AnneeAlternance> currentAlternanceOpt = anneeAlternanceRepository.findLastForAllActif();
        List<AnneeAlternance> anneeAlternanceTuteur = currentAlternanceOpt.stream().filter(
                anneeAlternance -> Objects.equals(anneeAlternance.getTuteurPedagogique() != null ? anneeAlternance.getTuteurPedagogique().getId() : null, tuteurId)).toList();

        List<ApprentiTabDTO> returnList = new ArrayList<>();

        if (!anneeAlternanceTuteur.isEmpty()) {
            for (AnneeAlternance anneeAlternance : anneeAlternanceTuteur) {
                // 1. Entreprise
                ApprentiTabDTO current = new ApprentiTabDTO(anneeAlternance.getApprenti().getId(),
                        anneeAlternance.getApprenti().getNom(),
                        anneeAlternance.getApprenti().getPrenom(),
                        anneeAlternance.getApprenti().getAdresseElectronique(),
                        anneeAlternance.getApprenti().getTelephone(),
                        anneeAlternance.getProgramme() != null ? anneeAlternance.getProgramme().name() : programmeStr,
                        anneeAlternance.getEntreprise() != null ? anneeAlternance.getEntreprise().getRaisonSociale() : entrepriseRaisonSociale,
                        anneeAlternance.getMission() != null ? anneeAlternance.getMission().getMotsCles() : missionMotsCles,
                        anneeAlternance.getAnneeAcademique() != null ? anneeAlternance.getAnneeAcademique().name() : anneeAcademiqueDisplay);

                returnList.add(current);
            }
        }

        return returnList;
    }

    public ApprentiDetailDTO toDetailDTO(Apprenti apprenti) {

        AnneeAlternance currentAlternanceOpt = anneeAlternanceRepository.findLastById(apprenti.getId());
        ApprentiDetailDTO apprentiDetailDTO = new ApprentiDetailDTO();

        if (currentAlternanceOpt != null) {
            apprentiDetailDTO.setApprenti(currentAlternanceOpt.getApprenti());
            apprentiDetailDTO.setAnneeAlternance(currentAlternanceOpt);
        }

        if (apprentiDetailDTO == null) throw new NullPointerException("Apprenti details not found");
        if (apprentiDetailDTO.getApprenti() == null) throw  new NullPointerException("Apprenti null");
        if (apprentiDetailDTO.getAnneeAlternance() == null) throw  new NullPointerException("Annee Alternance null");
        return apprentiDetailDTO;
    }

}