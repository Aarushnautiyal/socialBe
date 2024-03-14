package com.socialbe.main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class AbstractController {

    public static ResponseEntity<Object> buildResponse(HttpStatus status, Object body) {
        AbstractController.status = status;
        AbstractController.body = body;
        Map<String,Object> myResBody = new HashMap<>();
        myResBody.put("status",status);
        myResBody.put("data",body);
        return ResponseEntity.status(status).body(myResBody);
    }

    public static ResponseEntity<Object> buildError(HttpStatus status, Object body) {
        Map<String,Object> myResBody = new HashMap<>();
        myResBody.put("status",status);
        myResBody.put("Err",body);
        return ResponseEntity.status(status).body(myResBody);
    }
}
