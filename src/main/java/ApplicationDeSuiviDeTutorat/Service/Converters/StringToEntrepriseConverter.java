package ApplicationDeSuiviDeTutorat.Service.Converters;

import ApplicationDeSuiviDeTutorat.Models.Entities.Entreprise;
import ApplicationDeSuiviDeTutorat.Repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToEntrepriseConverter implements Converter<String, Entreprise> {

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Override
    public Entreprise convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        Long id = Long.parseLong(source);
        return entrepriseRepository.findById(id).orElse(null);
    }
}