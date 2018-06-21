package com.vfoexchange.restServer.controllerAdvice;

import com.vfoexchange.restServer.Constants.AppConstants;
import com.vfoexchange.restServer.exceptions.UserNotFoundException;
import com.vfoexchange.restServer.exceptions.UserRoleNotFoundException;
import com.vfoexchange.restServer.model.ErrorDetails;
import com.vfoexchange.restServer.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        LOGGER.debug("handling 404 error on a user entry");
        ErrorDetails errorDetails = new ErrorDetails(AppUtil.getDate(AppConstants.DATE_FORMAT), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserRoleNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserRoleNotFoundException(UserRoleNotFoundException ex, WebRequest request) {
        LOGGER.debug("handling 404 error on a role entry");
        ErrorDetails errorDetails = new ErrorDetails(AppUtil.getDate(AppConstants.DATE_FORMAT), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
