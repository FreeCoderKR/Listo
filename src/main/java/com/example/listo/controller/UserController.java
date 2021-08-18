package com.example.listo.controller;

import com.example.listo.dto.GuestResDto;
import com.example.listo.dto.UserReqDto;
import com.example.listo.dto.UserResDto;
import com.example.listo.service.GuestService;
import com.example.listo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final GuestService guestService;
    @PostMapping("/register")
    public ResponseEntity<UserResDto> register(@RequestBody UserReqDto request){

        UserResDto savedDto = userService.createUser(request);
        return ResponseEntity.ok(savedDto);

    }

    @GetMapping("/guest/{guestId}")
    public ResponseEntity<GuestResDto> getGuest(@PathVariable("guestId") Long guestId){
        GuestResDto guest = guestService.findGuest(guestId);
        return ResponseEntity.ok(guest);

    }
}
