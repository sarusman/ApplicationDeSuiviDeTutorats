package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import org.springframework.stereotype.Service;

@Service
public class ProgrammeService {

    public Programme[] getProgrammesArray() {
        return Programme.values();
    }
}
