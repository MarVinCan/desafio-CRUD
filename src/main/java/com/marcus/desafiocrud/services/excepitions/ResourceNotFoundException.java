package com.marcus.desafiocrud.services.excepitions;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String msg){
        super(msg);

    }


}
