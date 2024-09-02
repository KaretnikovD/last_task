package ru.kata.spring.boot_security.demo.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleException(NoSuchElementException exception) {
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo("There is no user with this ID");
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleException(NumberFormatException exception) {
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo("ID can only be in numeric format");
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
