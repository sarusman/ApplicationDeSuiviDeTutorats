package ApplicationDeSuiviDeTutorat.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateTelephoneException extends RuntimeException {
    public DuplicateTelephoneException(String message) { super(message); }
}
