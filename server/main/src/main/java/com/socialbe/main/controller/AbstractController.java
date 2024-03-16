package com.socialbe.main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class AbstractController {
    private Integer status;
    private Object body;
    private static String myStatus= "OK";
    public ResponseEntity<Object> buildResponse(HttpStatus status, Object body) {
        this.body = body;
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
