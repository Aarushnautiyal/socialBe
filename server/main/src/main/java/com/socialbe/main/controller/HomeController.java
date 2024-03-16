package com.socialbe.main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HomeController extends AbstractController{

    @GetMapping(value = "/posts",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPosts() {
        Map<String,String> myMap = new HashMap<>();
         myMap.put("hey","res");
         return  buildResponse(HttpStatus.OK,myMap);
    }
}
