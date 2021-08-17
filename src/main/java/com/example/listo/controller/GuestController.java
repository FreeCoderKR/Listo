package com.example.listo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @GetMapping("/{guestId}")
    public ResponseEntity<> getOneGuest(@PathVariable("guestId") Long guestId){

    }
}
