package com.store.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiError {
    private List<Message> errors;

    public ApiError() {}

    public ApiError(List<Message> errors) {
        this.errors = errors;
    }
}
