package ApplicationDeSuiviDeTutorat.service;
import ApplicationDeSuiviDeTutorat.model.Trainee;
import ApplicationDeSuiviDeTutorat.repository.TraineeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import ApplicationDeSuiviDeTutorat.repository.ProgrammeurRepository;
import ApplicationDeSuiviDeTutorat.model.Programmeur;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class TraineeService {
    private final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public List<Trainee> getAllTrainess() {
        return traineeRepository.findAll();
    }

    public Optional<Trainee> getTraineeById(Integer id) {
        Optional<Trainee> singleTrainee = traineeRepository.findById(id);

        return Optional.ofNullable(
                singleTrainee.orElseThrow(
                        () -> new IllegalStateException(
                                "Trainee with " + id + " does not exist")));
    }

    @Transactional
    public Trainee updateTraineeById(Integer id, Trainee updatedTrainee) {
        Trainee traineeToUpdate = traineeRepository.findById(id).orElseThrow();

        if (traineeToUpdate != null) {
            BeanUtils.copyProperties(updatedTrainee, traineeToUpdate, "id");
            return traineeRepository.save(traineeToUpdate);
        }

        return null;
    }
}