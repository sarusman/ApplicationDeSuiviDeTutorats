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

@Service
public class TraineeService {
    private final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public List<Trainee> getAllTrainess() {
        return traineeRepository.findAll();
    }
}