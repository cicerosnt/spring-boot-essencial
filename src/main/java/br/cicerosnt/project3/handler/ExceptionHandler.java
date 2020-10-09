package br.cicerosnt.project3.handler;

import br.cicerosnt.project3.error.ErrorDetails;
import br.cicerosnt.project3.error.ResourceNotFoundException;
import br.cicerosnt.project3.error.ResourceNotFoundTatails;
import br.cicerosnt.project3.error.ValidatorErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException rfnExecption){
        ResourceNotFoundTatails rfnDetails = ResourceNotFoundTatails.Builder
                .newBuilder()
                .setTimestamp(new Date().getTime())
                .setStatus(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .setTitle("Resource not found")
                .setDetail(rfnExecption.getMessage())
                .setDeveloperMessage(rfnExecption.getClass().getName())
                .builder();

        return new ResponseEntity<>(rfnDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvExecption, HttpHeaders headers,
                                                          HttpStatus status, WebRequest request) {

        List<FieldError> list = manvExecption.getBindingResult().getFieldErrors();
        String fields = list.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldMsgs = list.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

        ValidatorErrorDetails vedDetails = ValidatorErrorDetails.Builder
                .newBuilder()
                .setTimestamp(new Date().getTime())
                .setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .setTitle("Field Validation Error")
                .setDetail("Field Validation Error")
                .setDeveloperMessage(manvExecption.getClass().getName())
                .setField(fields)
                .setFieldMsg(fieldMsgs)
                .builder();

        return new ResponseEntity<>(vedDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        ErrorDetails errordetails = ErrorDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .title("Resource not found")
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(errordetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        ErrorDetails errordetails = ErrorDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .title("Internal Exception")
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity(errordetails, headers, status);
    }
}
