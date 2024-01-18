package com.music_streaming_app.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String email) {
        super("User with email = " + email + " already exists");
    }
}
