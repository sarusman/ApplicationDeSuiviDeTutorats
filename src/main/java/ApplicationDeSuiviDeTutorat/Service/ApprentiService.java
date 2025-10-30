package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiDto;
import ApplicationDeSuiviDeTutorat.Models.Entities.AnneeAlternance;
import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Models.Entities.Visite;
import ApplicationDeSuiviDeTutorat.Repository.*;
import ApplicationDeSuiviDeTutorat.Repository.UtilisateurRepository;
import ApplicationDeSuiviDeTutorat.Repository.ApprentiRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ApprentiService {
    private final ApprentiRepository apprentiRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final AnneeAlternanceRepository anneeAlternanceRepository;
    private final VisiteRepository visiteRepository;

    public ApprentiService(ApprentiRepository apprentiRepository,
                           UtilisateurRepository utilisateurRepository,
                           AnneeAlternanceRepository anneeAlternanceRepository,
                           VisiteRepository visiteRepository) {
        this.apprentiRepository = apprentiRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.anneeAlternanceRepository = anneeAlternanceRepository;
        this.visiteRepository = visiteRepository;
    }

    public List<Apprenti> getAllApprentis() {
        return apprentiRepository.findAll();
    }

    public Optional<Apprenti> getApprentiById(Long id) {
        Optional<Apprenti> singleApprenti = apprentiRepository.findById(id);

        return Optional.ofNullable(
                singleApprenti.orElseThrow(
                        () -> new IllegalStateException("Apprenti with " + id + " does not exist")
                )
        );
    }

    /**
     * Crée un nouvel apprenti et l'associe à un tuteur pédagogique.
     * @param apprenti L'objet Apprenti à sauvegarder.
     * @return L'apprenti sauvegardé.
     */
    @Transactional
    public Apprenti createApprenti(Apprenti apprenti, Long tuteurId) {
        Utilisateur tuteurPedagogique = utilisateurRepository
                .findById(tuteurId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Utilisateur (Tuteur) non trouvé avec l'id : " + tuteurId)
                );

            // TODO : Create Alternance Year with Apprenti

//        anneeAlternance.setApprenti(apprenti);
//        anneeAlternance.setTuteurPedagogique(utilisateur);
//        createAnneeAlternance(anneeAlternance);
//        apprenti.setTuteurPedagogique(tuteurPedagogique);

        return apprentiRepository.save(apprenti);
    }

    private void createAnneeAlternance(AnneeAlternance anneeAlternance) {}

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
        apprentiRepository.deleteById(id);
    }

    public boolean existeAdresse(String adresseElectronique) {
        return apprentiRepository.existsByAdresseElectronique(adresseElectronique);
    }

    public boolean existeTelephone(String telephone) {
        return apprentiRepository.existsByTelephone(telephone);
    }

    public Optional<Visite> findDerniereVisite(Apprenti apprenti) {
        if (apprenti == null || apprenti.getId() == null) return Optional.empty();
        return visiteRepository.findDerniereNative(apprenti.getId(), LocalDate.now()
        );
    }

    public Optional<Visite> findProchaineVisite(Apprenti apprenti) {
        if (apprenti == null || apprenti.getId() == null) return Optional.empty();
        return visiteRepository.findProchaineNative(apprenti.getId(), LocalDateTime.now());
    }

    private ApprentiDto toDto(Apprenti a) {
        if (a == null) return null;

        String entrepriseNom = (a.getEntreprise() != null)
                ? a.getEntreprise().getNom()
                : null;

        String tuteurNom = (a.getTuteurPedagogique() != null)
                ? a.getTuteurPedagogique().getUsername()
                : null;

        return new ApprentiDto(
                a.getId(),
                a.getNom(),
                a.getPrenom(),
                a.getAdresseElectronique(),
                a.getTelephone(),
                entrepriseNom,
                tuteurNom
        );
    }

    public Optional<ApprentiDto> getApprentiDtoById(Long id) {
        return getApprentiById(id).map(this::toDto);
    }

    public List<ApprentiDto> getApprentisPourTuteur(Long tuteurId) {
        List<Apprenti> apprentis = apprentiRepository
                .findByTuteurPedagogique_Id(tuteurId);

        return apprentis.stream()
                .map(this::toDto)
                .toList();
    }
}