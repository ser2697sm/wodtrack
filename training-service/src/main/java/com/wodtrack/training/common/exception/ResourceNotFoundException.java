package com.wodtrack.training.common.exception;
/*
  lo busca pero no existe
*/
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}