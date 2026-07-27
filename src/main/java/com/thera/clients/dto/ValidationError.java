package com.thera.clients.dto;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String path, String error) {
        super(timestamp, status, path, error);
    }

    public void addError(String field, String message){
        errors.add(new FieldMessage(field, message));
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }
}
