package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiDto;
import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Models.Entities.Visite;
import ApplicationDeSuiviDeTutorat.Repository.ApprentiBilanRepository;
import ApplicationDeSuiviDeTutorat.repository.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ApprentiService {

    private final ApprentiBilanRepository apprentiBilanRepository;
    private final UtilisateurRepository utilisateurRepository;

    public ApprentiService(ApprentiBilanRepository apprentiBilanRepository,
                           UtilisateurRepository utilisateurRepository) {
        this.apprentiBilanRepository = apprentiBilanRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Apprenti> getAllApprentis() {
        return apprentiBilanRepository.findAll();
    }

    public Optional<Apprenti> getApprentiById(Long id) {
        Optional<Apprenti> singleApprenti = apprentiBilanRepository.findById(id);
        return Optional.ofNullable(
                singleApprenti.orElseThrow(
                        () -> new IllegalStateException("Apprenti with " + id + " does not exist")
                )
        );
    }

    @Transactional
    public Apprenti createApprenti(Apprenti apprenti, Long tuteurId) {
        Utilisateur tuteurPedagogique = utilisateurRepository
                .findById(tuteurId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Utilisateur (Tuteur) non trouvé avec l'id : " + tuteurId)
                );

        apprenti.setTuteurPedagogique(tuteurPedagogique);

        return apprentiBilanRepository.save(apprenti);
    }

    @Transactional
    public Apprenti updateApprentiBilanById(Long id, Apprenti updatedApprenti) {
        Apprenti apprentiToUpdate = apprentiBilanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Apprenti non trouvé avec l'id : " + id));

        apprentiToUpdate.setNom(updatedApprenti.getNom());
        apprentiToUpdate.setPrenom(updatedApprenti.getPrenom());
        apprentiToUpdate.setAdresseElectronique(updatedApprenti.getAdresseElectronique());
        apprentiToUpdate.setTelephone(updatedApprenti.getTelephone());
        apprentiToUpdate.setProgramme(updatedApprenti.getProgramme());

        return apprentiBilanRepository.save(apprentiToUpdate);
    }

    @Transactional
    public void deleteApprentiById(Long id) {
        if (!apprentiBilanRepository.existsById(id)) {
            throw new EntityNotFoundException("Apprenti non trouvé avec l'id : " + id);
        }
        apprentiBilanRepository.deleteById(id);
    }

    public boolean existeAdresse(String adresseElectronique) {
        return apprentiBilanRepository.existsByAdresseElectronique(adresseElectronique);
    }

    public boolean existeTelephone(String telephone) {
        return apprentiBilanRepository.existsByTelephone(telephone);
    }

    public Optional<Visite> findDerniereVisite(Apprenti apprenti) {
        if (apprenti == null || apprenti.getVisites() == null) {
            return Optional.empty();
        }
        LocalDate aujourdhui = LocalDate.now();
        return apprenti.getVisites().stream()
                .filter(v -> v.getDate() != null && v.getDate().isBefore(aujourdhui))
                .max(Comparator.comparing(Visite::getDate));
    }

    public Optional<Visite> findProchaineVisite(Apprenti apprenti) {
        if (apprenti == null || apprenti.getVisites() == null) {
            return Optional.empty();
        }
        LocalDate aujourdhui = LocalDate.now();
        return apprenti.getVisites().stream()
                .filter(v -> v.getDate() != null && v.getDate().isAfter(aujourdhui))
                .min(Comparator.comparing(Visite::getDate));
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
        List<Apprenti> apprentis = apprentiBilanRepository
                .findByTuteurPedagogique_Id(tuteurId);

        return apprentis.stream()
                .map(this::toDto)
                .toList();
    }
}
