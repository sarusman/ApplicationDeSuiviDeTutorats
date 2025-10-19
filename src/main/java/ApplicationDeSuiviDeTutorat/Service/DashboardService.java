package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Entities.Tuteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    ApprentiService traineeService;
    @Autowired
    MentorService mentorService;

    public List<Apprenti> getTrainees() {
        return traineeService.getAllApprentis();
    }

    public List<Tuteur> getMentors() {return mentorService.getAllMentors();}
}
