//package ApplicationDeSuiviDeTutorat.Controller.Api;
//
//import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiAnneeAlternanceDTO;
//import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
//import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
//import ApplicationDeSuiviDeTutorat.Service.ApprentiService;
//import ApplicationDeSuiviDeTutorat.Service.UtilisateurService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.security.Principal;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/apprentis")
//@Tag(name = "Apprentis", description = "Endpoints REST pour consulter et gérer les apprentis")
//public class ApprentiApiController {
//
//    private final ApprentiService apprentiService;
//    private final UtilisateurService utilisateurService;
//
//    public ApprentiApiController(ApprentiService apprentiService,
//                                 UtilisateurService utilisateurService) {
//        this.apprentiService = apprentiService;
//        this.utilisateurService = utilisateurService;
//    }
//
//    @GetMapping
//    @Operation(summary = "Lister les apprentis du tuteur connecté")
//    public List<ApprentiAnneeAlternanceDTO> getApprentisDuTuteur(Principal principal) {
//
//        if (principal == null) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Non authentifié");
//        }
//
//        String username = principal.getName();
//
//        Utilisateur tuteurConnecte = utilisateurService.trouverParUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
//
//        Long tuteurId = tuteurConnecte.getId();
//
//        return apprentiService.getApprentisPourTuteur(tuteurId);
//    }
//
//    @GetMapping("/{id}")
//    @Operation(summary = "Récupérer un apprenti par ID")
//    public ApprentiAnneeAlternanceDTO getApprenti(@PathVariable Long id) {
//        return apprentiService.getApprentiAnneeAlternanceDTOById(id)
//                .orElseThrow(() ->
//                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Apprenti introuvable")
//                );
//    }
//
//    @PostMapping
//    @Operation(summary = "Créer un nouvel apprenti")
//    public ApprentiAnneeAlternanceDTO createApprenti(@RequestBody ApprentiAnneeAlternanceDTO dto,
//                                      Principal principal) {
//
//        if (principal == null) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Non authentifié");
//        }
//
//        String username = principal.getName();
//
//        Utilisateur tuteurConnecte = utilisateurService.trouverParUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
//
//        Long tuteurId = tuteurConnecte.getId();
//
//        if (dto.adresseElectronique() != null &&
//                apprentiService.existeAdresse(dto.adresseElectronique())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "Impossible d'ajouter : cet e-mail est déjà utilisé.");
//        }
//
//        if (dto.telephone() != null &&
//                !dto.telephone().isBlank() &&
//                apprentiService.existeTelephone(dto.telephone())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "Impossible d'ajouter : ce numéro est déjà utilisé.");
//        }
//
//        Apprenti entity = new Apprenti();
//        entity.setNom(dto.nom());
//        entity.setPrenom(dto.prenom());
//        entity.setAdresseElectronique(dto.adresseElectronique());
//        entity.setTelephone(dto.telephone());
//
//        Apprenti saved = apprentiService.createApprenti(dto);
//
//        return apprentiService.getApprentiAnneeAlternanceDTOById(saved.getId())
//                .orElseThrow(() ->
//                        new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
//                                "Création effectuée mais échec de la récupération")
//                );
//    }
//
//    @PutMapping("/{id}")
//    @Operation(summary = "Mettre à jour un apprenti existant")
//    public ApprentiAnneeAlternanceDTO updateApprenti(@PathVariable Long id,
//                                      @RequestBody ApprentiAnneeAlternanceDTO dto,
//                                      Principal principal) {
//
//        if (principal == null) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Non authentifié");
//        }
//
//        Apprenti updated = new Apprenti();
//        updated.setNom(dto.nom());
//        updated.setPrenom(dto.prenom());
//        updated.setAdresseElectronique(dto.adresseElectronique());
//        updated.setTelephone(dto.telephone());
//
//        try {
//            apprentiService.updateApprentiBilanById(id, updated);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "Une erreur est survenue lors de la mise à jour");
//        }
//
//        return apprentiService.getApprentiAnneeAlternanceDTOById(id)
//                .orElseThrow(() ->
//                        new ResponseStatusException(HttpStatus.NOT_FOUND,
//                                "Apprenti introuvable après mise à jour")
//                );
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @Operation(summary = "Supprimer un apprenti")
//    public void deleteApprenti(@PathVariable Long id,
//                               Principal principal) {
//
//        if (principal == null) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Non authentifié");
//        }
//
//        try {
//            apprentiService.deleteApprentiById(id);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                    "Apprenti introuvable");
//        }
//    }
//}
