package com.dh.clinicaOdontologicaProject.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException extends Exception{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> ResourceNotFoundErrorMgmt(ResourceNotFoundException rnfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR! --> Details: "+rnfe.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> BadRequestErrorMgmt(BadRequestException bre){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR! --> Details: "+ bre.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> IllegalArgumentErrorMgmt(IllegalArgumentException iae){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR! --> Details: "+iae.getMessage());
    }

}
