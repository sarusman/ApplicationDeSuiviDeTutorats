package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiAnneeAlternanceDTO;
import ApplicationDeSuiviDeTutorat.Models.Entities.*;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import ApplicationDeSuiviDeTutorat.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApprentiService {
    private final ApprentiRepository apprentiRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final AnneeAlternanceRepository anneeAlternanceRepository;
    private final VisiteRepository visiteRepository;
    // Ajout de la dépendance pour l'année académique
    private final AnneeAcademiqueRepository anneeAcademiqueRepository;


    public ApprentiService(ApprentiRepository apprentiRepository,
                           UtilisateurRepository utilisateurRepository,
                           AnneeAlternanceRepository anneeAlternanceRepository,
                           VisiteRepository visiteRepository,
                           AnneeAcademiqueRepository anneeAcademiqueRepository) { // Injection de dépendance
        this.apprentiRepository = apprentiRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.anneeAlternanceRepository = anneeAlternanceRepository;
        this.visiteRepository = visiteRepository;
        this.anneeAcademiqueRepository = anneeAcademiqueRepository; // Initialisation
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
    public Apprenti createApprenti(ApprentiAnneeAlternanceDTO dto, String tuteurId) {
        Apprenti apprenti = new Apprenti();
        apprenti.setNom(dto.nom());
        apprenti.setPrenom(dto.prenom());
        apprenti.setAdresseElectronique(dto.adresseElectronique());
        apprenti.setTelephone(dto.telephone());


        return apprentiRepository.save(apprenti);
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

    /**
     * Convertit un Apprenti en ApprentiAnneeAlternanceDTO.
     * Les données liées (tuteur, entreprise) sont récupérées depuis la dernière AnneeAlternance.
     */
//    private ApprentiAnneeAlternanceDTO toDto(Apprenti a, AnneeAcademique anneeAcademique) {
//        if (a == null) return null;
//
//        // Chercher la dernière année d'alternance pour obtenir les informations contextuelles
//        Optional<AnneeAlternance> derniereAnneeOpt = anneeAlternanceRepository
//                .findByApprentiId(a.getId())
//                .stream()
//                .max(Comparator.comparing(an -> an.getAnneeAcademique().getDate_debut())); // Supposant que AnneeAcademique a une année
//
//        Entreprise entreprise = null;
//        Utilisateur tuteur = null;
//
//        if (derniereAnneeOpt.isPresent()) {
//            AnneeAlternance derniereAnnee = derniereAnneeOpt.get();
//            if (derniereAnnee.getEntreprise() != null) {
//                entreprise = derniereAnnee.getEntreprise();
//            }
//            if (derniereAnnee.getTuteurPedagogique() != null) {
//                tuteur = derniereAnnee.getTuteurPedagogique();
//            }
//        }
//
//        return new ApprentiAnneeAlternanceDTO(
//                a.getId(),
//                a.getNom(),
//                a.getPrenom(),
//                a.getAdresseElectronique(),
//                a.getTelephone(),
//                anneeAcademique,
//
//
//        );
//    }


//    public Optional<ApprentiAnneeAlternanceDTO> getApprentiAnneeAlternanceDTOById(Long id) {
//        return getApprentiById(id).map(this::toDto);
//    }

    /**
     * Récupère la liste des apprentis (DTO) pour un tuteur donné pour l'année en cours.
     * @param tuteurId L'ID du tuteur.
     * @return Une liste de ApprentiAnneeAlternanceDTO.
     */
//    public List<ApprentiAnneeAlternanceDTO> getApprentisPourTuteur(Long tuteurId) {
//        // La logique pour trouver l'année "actuelle" est nécessaire.
//        // Ici, nous récupérons toutes les années d'alternance pour ce tuteur.
//        // Il faudrait idéalement filtrer par l'année académique en cours.
//        List<AnneeAlternance> anneesAlternance = anneeAlternanceRepository
//                .findByTuteurPedagogiqueId(tuteurId);
//
//        return anneesAlternance.stream()
//                .map(AnneeAlternance::getApprenti) // Extrait l'apprenti de chaque année d'alternance
//                .distinct() // Assure qu'on n'a pas de doublons si un apprenti a plusieurs années avec le même tuteur
//                .map(this::toDto) // Convertit chaque apprenti en DTO
//                .collect(Collectors.toList());
//    }
}