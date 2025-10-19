package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Repository.ApprentiBilanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApprentiService {
    private final ApprentiBilanRepository apprentiBilanRepository;

    public ApprentiService(ApprentiBilanRepository apprentiBilanRepository) {
        this.apprentiBilanRepository = apprentiBilanRepository;
    }

    public List<Apprenti> getAllApprentis() {
        return apprentiBilanRepository.findAll();
    }

    public Optional<Apprenti> getApprentiBilanById(Long id) {
        Optional<Apprenti> singleApprenti = apprentiBilanRepository.findById(id);

        return Optional.ofNullable(
                singleApprenti.orElseThrow(
                        () -> new IllegalStateException(
                                "Apprenti with " + id + " does not exist")));
    }

    @Transactional
    public Apprenti updateApprentiBilanById(Long id, Apprenti updatedApprenti) {
        Apprenti apprentiToUpdate = apprentiBilanRepository.findById(id).orElseThrow();

        if (apprentiToUpdate != null) {
            BeanUtils.copyProperties(updatedApprenti, apprentiToUpdate, "id");
            return apprentiBilanRepository.save(apprentiToUpdate);
        }

        return null;
    }
}