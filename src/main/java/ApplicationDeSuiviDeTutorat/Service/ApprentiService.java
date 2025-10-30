package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Exceptions.ApprentiNotFoundException;
import ApplicationDeSuiviDeTutorat.Exceptions.DuplicateEmailException;
import ApplicationDeSuiviDeTutorat.Exceptions.DuplicateTelephoneException;
import ApplicationDeSuiviDeTutorat.Exceptions.TuteurNotFoundException;
import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiDto;
import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Models.Entities.Visite;
import ApplicationDeSuiviDeTutorat.Repository.ApprentiBilanRepository;
import ApplicationDeSuiviDeTutorat.Repository.UtilisateurRepository;
import ApplicationDeSuiviDeTutorat.Repository.VisiteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(Transactional.TxType.SUPPORTS)
public class ApprentiService {

    private final ApprentiBilanRepository apprentiBilanRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final VisiteRepository visiteRepository;

    public List<Apprenti> getAllApprentis() {
        return apprentiBilanRepository.findAll();
    }

    public Optional<Apprenti> getApprentiById(Long id) {
        Optional<Apprenti> singleApprenti = apprentiBilanRepository.findById(id);
        return Optional.ofNullable(singleApprenti.orElseThrow(() ->
                new ApprentiNotFoundException("Apprenti with " + id + " does not exist")));
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Apprenti createApprenti(Apprenti apprenti, Long tuteurId) {
        Utilisateur tuteurPedagogique = utilisateurRepository
                .findById(tuteurId)
                .orElseThrow(() -> new TuteurNotFoundException("Utilisateur (Tuteur) non trouvé avec l'id : " + tuteurId));

        if (apprenti.getAdresseElectronique() != null &&
                apprentiBilanRepository.existsByAdresseElectronique(apprenti.getAdresseElectronique())) {
            throw new DuplicateEmailException("Cet e-mail est déjà utilisé.");
        }

        if (apprenti.getTelephone() != null &&
                !apprenti.getTelephone().isBlank() &&
                apprentiBilanRepository.existsByTelephone(apprenti.getTelephone())) {
            throw new DuplicateTelephoneException("Ce numéro de téléphone est déjà utilisé.");
        }

        apprenti.setTuteurPedagogique(tuteurPedagogique);
        return apprentiBilanRepository.save(apprenti);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Apprenti updateApprentiBilanById(Long id, Apprenti updatedApprenti) {
        Apprenti apprentiToUpdate = apprentiBilanRepository.findById(id)
                .orElseThrow(() -> new ApprentiNotFoundException("Apprenti non trouvé avec l'id : " + id));

        String newEmail = updatedApprenti.getAdresseElectronique();
        String oldEmail = apprentiToUpdate.getAdresseElectronique();
        if (newEmail != null && !newEmail.equalsIgnoreCase(oldEmail)
                && apprentiBilanRepository.existsByAdresseElectronique(newEmail)) {
            throw new DuplicateEmailException("Cet e-mail est déjà utilisé.");
        }

        String newTel = updatedApprenti.getTelephone();
        String oldTel = apprentiToUpdate.getTelephone();
        if (newTel != null && !newTel.isBlank()
                && (oldTel == null || !newTel.equals(oldTel))
                && apprentiBilanRepository.existsByTelephone(newTel)) {
            throw new DuplicateTelephoneException("Ce numéro de téléphone est déjà utilisé.");
        }

        apprentiToUpdate.setNom(updatedApprenti.getNom());
        apprentiToUpdate.setPrenom(updatedApprenti.getPrenom());
        apprentiToUpdate.setAdresseElectronique(newEmail);
        apprentiToUpdate.setTelephone(newTel);
        apprentiToUpdate.setProgramme(updatedApprenti.getProgramme());

        return apprentiBilanRepository.save(apprentiToUpdate);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteApprentiById(Long id) {
        if (!apprentiBilanRepository.existsById(id)) {
            throw new ApprentiNotFoundException("Apprenti non trouvé avec l'id : " + id);
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
        if (apprenti == null || apprenti.getId() == null) return Optional.empty();
        return visiteRepository.findDerniereNative(apprenti.getId(), LocalDate.now());
    }

    public Optional<Visite> findProchaineVisite(Apprenti apprenti) {
        if (apprenti == null || apprenti.getId() == null) return Optional.empty();
        return visiteRepository.findProchaineNative(apprenti.getId(), LocalDate.now());
    }

    private ApprentiDto toDto(Apprenti a) {
        if (a == null) return null;
        String entrepriseNom = (a.getEntreprise() != null) ? a.getEntreprise().getNom() : null;
        String tuteurNom = (a.getTuteurPedagogique() != null) ? a.getTuteurPedagogique().getUsername() : null;
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
        List<Apprenti> apprentis = apprentiBilanRepository.findByTuteurPedagogique_Id(tuteurId);
        return apprentis.stream().map(this::toDto).toList();
    }
}
