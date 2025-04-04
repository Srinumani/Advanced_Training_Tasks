package com.ust.Security_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
    public MessageResponse(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
