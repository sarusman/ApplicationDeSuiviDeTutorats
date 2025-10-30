package ApplicationDeSuiviDeTutorat.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApprentiNotFoundException extends RuntimeException {
    public ApprentiNotFoundException(String message) { super(message); }
}
