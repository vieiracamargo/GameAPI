package com.store.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {
    private String path;
    private String message;

    public Message() {}
    public Message(String path, String message) {
        this.path = path;
        this.message = message;
    }

    public Message(String message){
        this.message = message;
    }
}
