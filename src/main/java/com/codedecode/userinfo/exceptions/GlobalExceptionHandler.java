package com.codedecode.userinfo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

 @ExceptionHandler(UserNotFoundException.class)
 public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
  ErrorResponse error = new ErrorResponse();
  error.setTimestamp(LocalDateTime.now());
  error.setMessage("Exception: " + ex.getMessage());
  error.setStatus(HttpStatus.NOT_FOUND.value());
  return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler(InvalidInputException.class)
 public ResponseEntity<ErrorResponse> handleInvalidInputException(InvalidInputException ex) {
  ErrorResponse error = new ErrorResponse();
  error.setTimestamp(LocalDateTime.now());
  error.setMessage("Exception: " + ex.getMessage());
  error.setStatus(HttpStatus.BAD_REQUEST.value());
  return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
 }

 //  When you annotate a parameter with @Valid, Spring performs validation checks on the object
 // (in your case, UserDTO). If the object fails any of the validation constraints, Spring will
 // automatically throw a MethodArgumentNotValidException.
  //Your @ExceptionHandler(MethodArgumentNotValidException.class) method then
 // catches this exception, allowing you to customize the response. Inside this method,
 // you access the BindingResult from the exception (ex.getBindingResult())
 // to get details of what fields in the DTO failed validation (getFieldErrors()).
 @ExceptionHandler(MethodArgumentNotValidException.class)
 public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
  Map<String, Object> body = new HashMap<>();
  body.put("timestamp", LocalDateTime.now());
  body.put("status", HttpStatus.BAD_REQUEST.value());

  Map<String, String> errors = new HashMap<>();
  ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

  body.put("errors", errors);
  return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
 }

 // @ExceptionHandler(MethodArgumentNotValidException.class) method in your GlobalExceptionHandler class
 // will be triggered for validation errors on any DTO object in your application, as long as that DTO
 // is annotated with @Valid in the corresponding controller method. This is because the
 // @ControllerAdvice annotation makes this exception handler globally available to all controllers
 // in your Spring application.
  //When you use @Valid on a DTO object parameter in a controller method,
 // Spring will automatically validate the object based on the annotations you've used on the
 // fields (like @NotBlank, @NotNull, etc.). If validation fails,
 // a MethodArgumentNotValidException will be thrown, which will be caught by the
 // handleMethodArgumentNotValid method in your GlobalExceptionHandler.

}
