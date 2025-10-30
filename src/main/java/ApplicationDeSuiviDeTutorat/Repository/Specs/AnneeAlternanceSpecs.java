package ApplicationDeSuiviDeTutorat.Repository.Specs;

import ApplicationDeSuiviDeTutorat.Models.Entities.AnneeAlternance;
import ApplicationDeSuiviDeTutorat.Models.Enums.EtatApprenti;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;

public class AnneeAlternanceSpecs {

    public static Specification<AnneeAlternance> hasAnnee(String annee) {
        return (root, query, cb) -> {
            if (annee == null || annee.isBlank()) return null;

            Join<Object, Object> anneeAcad = root.join("anneeAcademique");

            Expression<String> nameExpr = cb.concat(
                    cb.function("CAST", String.class, anneeAcad.get("date_debut")),
                    cb.concat(" : ",
                            cb.function("CAST", String.class, anneeAcad.get("date_fin")))
            );

            return cb.like(cb.lower(nameExpr), "%" + annee.toLowerCase() + "%");
        };
    }

    public static Specification<AnneeAlternance> hasProgramme(Programme programme) {
        return (root, query, cb) ->
                programme == null ? null : cb.equal(root.get("programme"), programme);
    }

    public static Specification<AnneeAlternance> hasEntreprise(Long entrepriseId) {
        return (root, query, cb) ->
                entrepriseId == null ? null : cb.equal(root.get("entreprise").get("id"), entrepriseId);
    }

    public static Specification<AnneeAlternance> hasSearchQuery(String q) {
        return (root, query, cb) -> {
            if (q == null || q.isBlank()) return null;
            String pattern = "%" + q.toLowerCase() + "%";
            Join<Object, Object> apprenti = root.join("apprenti");
            return cb.or(
                    cb.like(cb.lower(apprenti.get("nom")), pattern),
                    cb.like(cb.lower(apprenti.get("prenom")), pattern)
            );
        };
    }

    public static Specification<AnneeAlternance> hasMission(String mission) {
        return (root, query, cb) -> {
            if (mission == null || mission.isBlank()) return null;
            Join<Object, Object> m = root.join("mission", JoinType.LEFT);
            return cb.like(cb.lower(cb.coalesce(m.get("motsCles"), "")),
                    "%" + mission.toLowerCase() + "%");
        };
    }

    public static Specification<AnneeAlternance> isArchived(Boolean archived) {
        return (root, query, cb) -> {
            if (archived == null) return null;
            Join<Object, Object> apprenti = root.join("apprenti");

            if (Boolean.FALSE.equals(archived)) {
                return cb.equal(apprenti.get("etat"), EtatApprenti.ACTIF);
            } else {
                return apprenti.get("etat").in(
                        EtatApprenti.ABANDON,
                        EtatApprenti.DIPLOME
                );
            }
        };
    }
}

