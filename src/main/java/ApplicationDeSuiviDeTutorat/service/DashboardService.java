package ApplicationDeSuiviDeTutorat.service;

import ApplicationDeSuiviDeTutorat.model.Trainee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    TraineeService traineeService;
    public DashboardService(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    public List<Trainee> getTrainees() {
        return traineeService.getAllTrainess();
    }
}
