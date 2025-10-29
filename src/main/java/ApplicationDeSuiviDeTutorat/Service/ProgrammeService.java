package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProgrammeService {

    public Programme[] getProgrammesArray() {
        return Programme.values();
    }

    public List<Programme> getProgrammesList() {
        return Arrays.asList(Programme.values());
    }
}
