package hospitaljpa.ui.error;

import com.hospitalcrud.domain.error.DUPLICATED_USERNAME;
import com.hospitalcrud.domain.error.DataBaseError;
import com.hospitalcrud.domain.error.FOREIGN_KEY_ERROR;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DUPLICATED_USERNAME.class)
    public ResponseEntity<DataBaseError> handleForeignKetException(DUPLICATED_USERNAME e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new DUPLICATED_USERNAME());
    }
    @ExceptionHandler(FOREIGN_KEY_ERROR.class)
    public ResponseEntity<DataBaseError> handleForeignKetException(FOREIGN_KEY_ERROR e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new FOREIGN_KEY_ERROR());
    }
}
