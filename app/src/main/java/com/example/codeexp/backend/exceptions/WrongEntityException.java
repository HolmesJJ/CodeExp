package com.example.codeexp.backend.exceptions;

import com.example.codeexp.backend.model.Entity;

public class WrongEntityException extends Exception {
    public WrongEntityException(Entity entity) {
        super(String.format("Given entity %s but expected otherwise.", entity.toString()));
    }
}
