package ApplicationDeSuiviDeTutorat.Controller.Advice;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // Vérifier d'abord le paramètre de query (venant de Security)
        String statusParam = request.getParameter("status");
        int statusCode;

        if (statusParam != null) {
            statusCode = Integer.parseInt(statusParam);
        } else if (status != null) {
            statusCode = Integer.parseInt(status.toString());
        } else {
            statusCode = 500;
        }
        String errorMessage = getErrorMessage(statusCode);
        String errorTitle = getErrorTitle(statusCode);

        model.addAttribute("status", statusCode);
        model.addAttribute("errorTitle", errorTitle);
        model.addAttribute("errorMessage", errorMessage);

        return "error";
    }

    private String getErrorTitle(int statusCode) {
        return switch (statusCode) {
            case 400 -> "Mauvaise requête";
            case 401 -> "Non autorisé";
            case 403 -> "Accès refusé";
            case 404 -> "Page non trouvée";
            case 500 -> "Erreur serveur";
            default -> "Erreur";
        };
    }

    private String getErrorMessage(int statusCode) {
        return switch (statusCode) {
            case 400 -> "La requête envoyée est invalide ou mal formée.";
            case 401 -> "Vous devez être connecté pour accéder à cette ressource.";
            case 403 -> "Vous n'avez pas les permissions nécessaires pour accéder à cette ressource.";
            case 404 -> "La page ou la ressource que vous recherchez n'existe pas.";
            case 500 -> "Une erreur interne du serveur s'est produite. Veuillez réessayer plus tard.";
            default -> "Une erreur est survenue.";
        };
    }
}