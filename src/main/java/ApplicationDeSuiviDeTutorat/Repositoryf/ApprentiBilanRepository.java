package ApplicationDeSuiviDeTutorat.Repository;

import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApprentiBilanRepository extends JpaRepository<Apprenti, Long> {

    boolean existsByAdresseElectronique(String adresseElectronique);

    boolean existsByTelephone(String telephone);

    List<Apprenti> findByTuteurPedagogique_Id(Long tuteurId);
    @Query("""
           select a from Apprenti a
           left join a.entreprise e
           left join a.mission m
           where (:annee is null or a.anneeAcademique = :annee)
             and (:programme is null or a.programme = :programme)
             and (:entrepriseId is null or e.id = :entrepriseId)
             and (:q is null or lower(a.nom) like lower(concat('%', :q, '%'))
                          or lower(a.prenom) like lower(concat('%', :q, '%')))
             and (:mission is null or lower(coalesce(m.motsCles, '')) like lower(concat('%', :mission, '%')))
           order by a.nom, a.prenom
           """)
    List<Apprenti> search(
            @Param("q") String q,
            @Param("entrepriseId") Long entrepriseId,
            @Param("mission") String mission,
            @Param("annee") String annee,
            @Param("programme") Programme programme,
            @Param("archived") Boolean archived
    );
}
