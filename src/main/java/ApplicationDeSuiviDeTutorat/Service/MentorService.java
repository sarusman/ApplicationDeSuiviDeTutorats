package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.Entities.Tuteur;
import ApplicationDeSuiviDeTutorat.Repository.TuteurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MentorService {
    private final TuteurRepository mentorRepository;

    public MentorService(TuteurRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    public List<Tuteur> getAllMentors() {
        return mentorRepository.findAll();
    }

    public Optional<Tuteur> getMentorById(Long id) {
        Optional<Tuteur> singleMentor = mentorRepository.findById(id);

        return Optional.ofNullable(
                singleMentor.orElseThrow(
                        () -> new IllegalStateException(
                                "Mentor with " + id + " does not exist")));
    }

    @Transactional
    public Tuteur updateMentorById(Long id, Tuteur updatedMentor) {
        Tuteur mentorToUpdate = mentorRepository.findById(id).orElseThrow();

        if (mentorRepository != null) {
            BeanUtils.copyProperties(updatedMentor, mentorToUpdate, "id");
            return mentorRepository.save(mentorToUpdate);
        }

        return null;
    }
}
