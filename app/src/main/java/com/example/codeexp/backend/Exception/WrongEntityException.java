package com.example.codeexp.backend.Exception;

import com.example.codeexp.backend.Model.Entity;

public class WrongEntityException extends Exception {
    public WrongEntityException(Entity entity) {
        super(String.format("Given entity %s but expected otherwise.", entity.toString()));
    }
}
