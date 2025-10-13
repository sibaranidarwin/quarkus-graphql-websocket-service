package org.acme.exception;

import io.smallrye.graphql.api.ErrorCode;

@ErrorCode("DATA_NOT_FOUND")
public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(Exception e){
        super("Data: " + e + " Not Found");
    }
}
