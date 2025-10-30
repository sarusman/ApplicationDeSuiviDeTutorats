package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.Entities.TuteurEntreprise;
import ApplicationDeSuiviDeTutorat.repository.TuteurEntrepriseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
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
        Optional<TuteurEntreprise> singleMentor = mentorRepository.findById(id);

        return Optional.ofNullable(
                singleMentor.orElseThrow(
                        () -> new IllegalStateException(
                                "Mentor with " + id + " does not exist")));
    }

    @Transactional
    public TuteurEntreprise updateMentorById(Long id, TuteurEntreprise updatedMentor) {
        TuteurEntreprise mentorToUpdate = mentorRepository.findById(id).orElseThrow();

        if (mentorRepository != null) {
            BeanUtils.copyProperties(updatedMentor, mentorToUpdate, "id");
            return mentorRepository.save(mentorToUpdate);
        }

        return null;
    }
}
