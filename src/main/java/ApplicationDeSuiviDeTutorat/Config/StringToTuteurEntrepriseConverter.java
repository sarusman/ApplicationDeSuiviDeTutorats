package ApplicationDeSuiviDeTutorat.Config;

import ApplicationDeSuiviDeTutorat.Models.Entities.TuteurEntreprise;
import ApplicationDeSuiviDeTutorat.repository.TuteurEntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToTuteurEntrepriseConverter implements Converter<String, TuteurEntreprise> {

    @Autowired
    private TuteurEntrepriseRepository tuteurEntrepriseRepository;

    @Override
    public TuteurEntreprise convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        Long id = Long.parseLong(source);
        return tuteurEntrepriseRepository.findById(id).orElse(null);
    }
}