package ApplicationDeSuiviDeTutorat.Repository;

import ApplicationDeSuiviDeTutorat.Models.Entities.AnneeAlternance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnneeAlternanceRepository extends JpaRepository<AnneeAlternance, Long> {
    @Query("select a FROM AnneeAlternance a WHERE a.apprenti = :apprentiId ORDER BY a.anneeAcademique.date_debut DESC")
    List<AnneeAlternance>  findAllByApprenti(@Param("apprentiId") Long apprentiId);

    @Query(value = """
        SELECT aa.* FROM annee_alternance aa
        JOIN apprenti a ON aa.apprenti_id = a.id
        WHERE a.etat = 'ACTIF'
        AND (aa.apprenti_id, aa.annee_academique) IN (
            SELECT aa_sub.apprenti_id, MAX(aa_sub.annee_academique)
            FROM annee_alternance aa_sub
            JOIN apprenti a_sub ON aa_sub.apprenti_id = a_sub.id
            WHERE a_sub.etat = 'ACTIF'
            GROUP BY aa_sub.apprenti_id)""", nativeQuery = true)
    List<AnneeAlternance> findLastForAllActif();

    @Query(value = """
        SELECT aa.*
        FROM annee_alternance aa
        INNER JOIN (
            SELECT
                apprenti_id,
                MAX(annee_academique) AS max_annee
            FROM annee_alternance
            WHERE apprenti_id = :apprentiId
            GROUP BY apprenti_id
        ) AS latest_year ON aa.apprenti_id = latest_year.apprenti_id
                        AND aa.annee_academique = latest_year.max_annee
        WHERE aa.apprenti_id = :apprentiId;""", nativeQuery = true)
    AnneeAlternance findLastById(@Param("apprentiId") Long apprentiId);

    List<AnneeAlternance> findByApprentiId(Long apprentiId);

    List<AnneeAlternance> findByTuteurPedagogiqueId(Long tuteurPedagogiqueId);
}
