package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.Entities.Tuteur;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MentorService {
    private final MentorRepository mentorRepository;

    public MentorService(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }

    public Optional<Mentor> getMentorById(Integer id) {
        Optional<Mentor> singleMentor = mentorRepository.findById(id);

        return Optional.ofNullable(
                singleMentor.orElseThrow(
                        () -> new IllegalStateException(
                                "Mentor with " + id + " does not exist")));
    }

    @Transactional
    public Mentor updateMentorById(Integer id, Mentor updatedMentor) {
        Mentor mentorToUpdate = mentorRepository.findById(id).orElseThrow();

        if (mentorRepository != null) {
            BeanUtils.copyProperties(updatedMentor, mentorToUpdate, "id");
            return mentorRepository.save(mentorToUpdate);
        }

        return null;
    }
}
