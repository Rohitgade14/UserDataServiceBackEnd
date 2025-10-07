package com.spcodage.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceAlreadyExistsException extends  Exception{
    public  ResourceAlreadyExistsException (String message){
        super(message);
    }

    public  ResourceAlreadyExistsException (){
        super("Resource Not Found");
    }
}
