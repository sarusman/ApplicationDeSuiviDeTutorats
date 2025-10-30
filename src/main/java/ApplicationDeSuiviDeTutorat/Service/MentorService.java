package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Exceptions.DuplicateEmailException;
import ApplicationDeSuiviDeTutorat.Exceptions.DuplicateTelephoneException;
import ApplicationDeSuiviDeTutorat.Exceptions.MentorNotFoundException;
import ApplicationDeSuiviDeTutorat.Models.Entities.TuteurEntreprise;
import ApplicationDeSuiviDeTutorat.Repository.TuteurEntrepriseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MentorService {
    private final TuteurEntrepriseRepository mentorRepository;

    public MentorService(TuteurEntrepriseRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    public List<TuteurEntreprise> getAllMentors() {
        return mentorRepository.findAll();
    }

    public Optional<TuteurEntreprise> getMentorById(Long id) {
        return Optional.ofNullable(
                mentorRepository.findById(id)
                        .orElseThrow(() -> new MentorNotFoundException("Tuteur entreprise " + id + " introuvable"))
        );
    }

    @Transactional
    public TuteurEntreprise createMentor(TuteurEntreprise mentor) {
        if (mentor.getAdresseElectronique() != null
                && mentorRepository.existsByAdresseElectroniqueIgnoreCase(mentor.getAdresseElectronique())) {
            throw new DuplicateEmailException("Cet e-mail est déjà utilisé.");
        }
        if (mentor.getTelephone() != null
                && !mentor.getTelephone().isBlank()
                && mentorRepository.existsByTelephone(mentor.getTelephone())) {
            throw new DuplicateTelephoneException("Ce numéro de téléphone est déjà utilisé.");
        }
        return mentorRepository.save(mentor);
    }

    @Transactional
    public TuteurEntreprise updateMentorById(Long id, TuteurEntreprise updatedMentor) {
        TuteurEntreprise current = mentorRepository.findById(id)
                .orElseThrow(() -> new MentorNotFoundException("Tuteur entreprise non trouvé avec l'id : " + id));

        String newEmail = updatedMentor.getAdresseElectronique();
        String oldEmail = current.getAdresseElectronique();
        if (newEmail != null
                && !newEmail.equalsIgnoreCase(oldEmail)
                && mentorRepository.existsByAdresseElectroniqueIgnoreCase(newEmail)) {
            throw new DuplicateEmailException("Cet e-mail est déjà utilisé.");
        }

        String newTel = updatedMentor.getTelephone();
        String oldTel = current.getTelephone();
        if (newTel != null
                && !newTel.isBlank()
                && (oldTel == null || !newTel.equals(oldTel))
                && mentorRepository.existsByTelephone(newTel)) {
            throw new DuplicateTelephoneException("Ce numéro de téléphone est déjà utilisé.");
        }

        current.setNom(updatedMentor.getNom());
        current.setPrenom(updatedMentor.getPrenom());
        current.setPoste(updatedMentor.getPoste());
        current.setAdresseElectronique(newEmail);
        current.setTelephone(newTel);
        current.setRemarques(updatedMentor.getRemarques());
        current.setEntreprise(updatedMentor.getEntreprise());

        return mentorRepository.save(current);
    }

    @Transactional
    public void deleteMentorById(Long id) {
        if (!mentorRepository.existsById(id)) {
            throw new MentorNotFoundException("Tuteur entreprise non trouvé avec l'id : " + id);
        }
        mentorRepository.deleteById(id);
    }
}
