package com.music_streaming_app.exception;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class FileUploadExceptionHandler extends ResponseEntityExceptionHandler {

    @ApiResponses(value = {
            @ApiResponse(code = 417, message = "Expectation Failed")
    })
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        String errorMessage = "The uploaded file is too large!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(errorMessage);
    }
}
