package com.segundo_desafio.game.api.model.exception;



public class InvalidGameRequestException extends RuntimeException {
    public InvalidGameRequestException(String message) {
        super(message);
    }

    public InvalidGameRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
