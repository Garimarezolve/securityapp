package com.dew.securityapp.exception;

import com.dew.securityapp.constant.ApplicationConstant;
import com.dew.securityapp.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<ErrorResponseDto> handleBadRequestException(ServiceException ex) {
        HttpStatus httpStatus = HttpStatus.OK;
        Integer errorCode = ApplicationConstant.HTTP_BAD_REQUEST_CODE;
        String errorMessage=ApplicationConstant.USER_ALREADY_EXIST;
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(errorCode,errorMessage,null);
        log.error("Service Exception | errorCode : {} | errorMessage : {} | httpStatus : {}", errorCode, errorMessage, httpStatus);
        return  ResponseEntity.status(HttpStatus.OK).body(errorResponseDto);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

        errorList.forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        Integer errorCode = ApplicationConstant.HTTP_BAD_REQUEST_CODE;
        String errorMessage=ApplicationConstant.BAD_Request;
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(errorCode,errorMessage,errors);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

}

