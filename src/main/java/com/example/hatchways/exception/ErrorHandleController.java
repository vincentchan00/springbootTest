package com.example.hatchways.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ErrorHandleController {
    @ExceptionHandler(value = TagsNotFoundException.class)
    public ResponseEntity<Object> tagsNotFoundException(TagsNotFoundException exception) {
        HashMap<String, String> errorJson = new HashMap<String, String>();
        errorJson.put("error","Tags parameter is required");
        return new ResponseEntity<>(errorJson, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SortNotFoundException.class)
    public ResponseEntity<Object> sortNotFoundException(SortNotFoundException exception) {
        HashMap<String, String> errorJson = new HashMap<String, String>();
        errorJson.put("error","sortBy parameter is required");
        return new ResponseEntity<>(errorJson, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = DirectionNotFoundException.class)
    public ResponseEntity<Object> directionNotFoundException(DirectionNotFoundException exception) {
        HashMap<String, String> errorJson = new HashMap<String, String>();
        errorJson.put("error","direction parameter is required");
        return new ResponseEntity<>(errorJson, HttpStatus.BAD_REQUEST);
    }
}
