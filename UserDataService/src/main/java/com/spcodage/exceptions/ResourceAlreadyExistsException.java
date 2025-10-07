package com.spcodage.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends  RuntimeException {
    public  ResourceAlreadyExistsException (String message){
        super(message);
    }

    public  ResourceAlreadyExistsException (){
        super("Resource Already Exist");
    }
}
