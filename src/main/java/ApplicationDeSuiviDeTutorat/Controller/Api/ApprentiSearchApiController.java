//package ApplicationDeSuiviDeTutorat.Controller.Api;
//
//import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiDto;
//import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
//import ApplicationDeSuiviDeTutorat.Service.ApprentiSearchService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.security.Principal;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/apprentis")
//@Tag(name = "Apprentis", description = "Recherche d'apprentis")
//public class ApprentiSearchApiController {
//
//    private final ApprentiSearchService searchService;
//
//    public ApprentiSearchApiController(ApprentiSearchService searchService) {
//        this.searchService = searchService;
//    }
//
//    @GetMapping("/search")
//    @Operation(summary = "Recherche multi-critères d'apprentis")
//    public List<ApprentiDto> search(
//            @RequestParam(required = false) String q,
//            @RequestParam(required = false) Long entrepriseId,
//            @RequestParam(required = false) String mission,
//            @RequestParam(required = false) String annee,
//            @RequestParam(required = false) Programme programme,
//            @RequestParam(required = false) Boolean archived,
//            Principal principal
//    ) {
//        if (principal == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Non authentifié");
//        return searchService.search(q, entrepriseId, mission, annee, programme, archived);
//    }
//}
