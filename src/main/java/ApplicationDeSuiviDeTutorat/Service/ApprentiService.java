package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.Entities.AnneeAlternance;
import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Entities.Visite;
import ApplicationDeSuiviDeTutorat.Repository.AnneeAlternanceRepository;
import ApplicationDeSuiviDeTutorat.Repository.ApprentiBilanRepository;
import ApplicationDeSuiviDeTutorat.Repository.UtilisateurRepository;
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
    private final AnneeAlternanceRepository anneeAlternanceRepository;

    public ApprentiService(ApprentiBilanRepository apprentiBilanRepository, UtilisateurRepository utilisateurRepository, AnneeAlternanceRepository anneeAlternanceRepository) {
        this.apprentiBilanRepository = apprentiBilanRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.anneeAlternanceRepository = anneeAlternanceRepository;
    }

    public List<Apprenti> getAllApprentis() {
        return apprentiBilanRepository.findAll();
    }

    public Optional<Apprenti> getApprentiById(Long id) {
        Optional<Apprenti> singleApprenti = apprentiBilanRepository.findById(id);

        return Optional.ofNullable(
                singleApprenti.orElseThrow(
                        () -> new IllegalStateException(
                                "Apprenti with " + id + " does not exist")));
    }

    /**
     * Crée un nouvel apprenti et l'associe à un tuteur pédagogique.
     * @param apprenti L'objet Apprenti à sauvegarder.
     * @return L'apprenti sauvegardé.
     */
    @Transactional
    public Apprenti createApprenti(Apprenti apprenti) {
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

        return apprentiBilanRepository.save(apprentiToUpdate);
    }

    /**
     * Trouve la dernière visite passée pour un apprenti donné.
     * @param apprenti L'apprenti pour lequel chercher la visite.
     * @return un Optional contenant la visite la plus récente, ou un Optional vide.
     */
    public Optional<Visite> findDerniereVisite(Apprenti apprenti) {
        if (apprenti == null || anneeAlternanceRepository.findAllByApprenti(apprenti.getId()).isEmpty()) {
            return Optional.empty();
        }
        LocalDate aujourdhui = LocalDate.now();
        return anneeAlternanceRepository.findAllByApprenti(apprenti.getId()).stream().findFirst()
                .map(anneeAlternance -> anneeAlternance.getVisites().stream()
                        .filter(v -> v.getDate() != null && v.getDate().isBefore(aujourdhui))
                        .max(Comparator.comparing(Visite::getDate)))
                .orElse(Optional.empty());
    }

    /**
     * Trouve la prochaine visite future pour un apprenti donné.
     * @param apprenti L'apprenti pour lequel chercher la visite.
     * @return un Optional contenant la visite future la plus proche, ou un Optional vide.
     */
    public Optional<Visite> findProchaineVisite(Apprenti apprenti) {
        if (apprenti == null || anneeAlternanceRepository.findAllByApprenti(apprenti.getId()).isEmpty()) {
            return Optional.empty();
        }
        LocalDate aujourdhui = LocalDate.now();
        return anneeAlternanceRepository.findAllByApprenti(apprenti.getId()).stream().findFirst()
                .map(anneeAlternance -> anneeAlternance.getVisites().stream()
                        .filter(v -> v.getDate() != null && v.getDate().isAfter(aujourdhui))
                        .min(Comparator.comparing(Visite::getDate)))
                .orElse(Optional.empty());
    }

    /**
     * Supprime un apprenti par son ID.
     * @param id L'ID de l'apprenti à supprimer.
     */
    @Transactional
    public void deleteApprentiById(Long id) {
        if (!apprentiBilanRepository.existsById(id)) {
            throw new EntityNotFoundException(STR."Apprenti non trouvé avec l'id : \{id}");
        }
        apprentiBilanRepository.deleteById(id);
    }

    public AnneeAlternance createAnneeAlternance(AnneeAlternance anneeAlternance) {
        return anneeAlternanceRepository.save(anneeAlternance);
    }

}