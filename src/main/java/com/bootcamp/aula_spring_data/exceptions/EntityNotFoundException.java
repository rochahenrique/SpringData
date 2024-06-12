package com.bootcamp.aula_spring_data.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message) {
        super("Entidade não encontrada: " + message);
    }
}
