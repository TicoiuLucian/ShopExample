package ro.itschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.itschool.exceptions.CartNotFoundException;
import ro.itschool.exceptions.ProductNotFoundException;

@ControllerAdvice
public class ErrorController {

    private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler({CartNotFoundException.class})
    public ResponseEntity cartNotFound() {
        logger.error("Cart unavailable");
        return new ResponseEntity("Cart not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity productNotFound() {
        logger.error("Product unavailable");
        return new ResponseEntity("Product not found", HttpStatus.I_AM_A_TEAPOT);
    }

}
