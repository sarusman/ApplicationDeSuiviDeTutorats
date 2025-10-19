package ApplicationDeSuiviDeTutorat.service;

import ApplicationDeSuiviDeTutorat.model.Mentor;
import ApplicationDeSuiviDeTutorat.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    TraineeService traineeService;
    @Autowired
    MentorService mentorService;

    public DashboardService(TraineeService traineeService,
                            MentorService mentorService) {
        this.traineeService = traineeService;
        this.mentorService = mentorService;
    }

    public List<Trainee> getTrainees() {
        return traineeService.getAllTrainess();
    }

    public List<Mentor> getMentors() {return mentorService.getAllMentors();}
}
