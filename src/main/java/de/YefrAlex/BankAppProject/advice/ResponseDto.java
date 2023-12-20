package de.YefrAlex.BankAppProject.advice;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseDto {
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String debugMessage;

    public ResponseDto(String message) {
        this.message = message;
    }

    public ResponseDto(String message, String debugMessage) {
        this.message = message;
        this.debugMessage = debugMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }
}
