package com.dew.securityapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ServiceException extends  RuntimeException{
  //  private static final long serialVersionUID = 1L;
    private Integer errorCode;
    private HttpStatus httpStatus = HttpStatus.OK;
    private Exception ex;
    private String message;

    public ServiceException(Integer errorCode,HttpStatus httpStatus,String message ) {
        this.errorCode=errorCode;
        this.httpStatus=httpStatus;
        this.message=message;

    }
}
