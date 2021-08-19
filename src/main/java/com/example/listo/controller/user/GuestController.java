package com.example.listo.controller.user;

import com.example.listo.dto.user.guest.GuestOnlyResDto;
import com.example.listo.dto.user.guest.GuestResDto;
import com.example.listo.dto.user.register.GuestReqDto;
import com.example.listo.dto.user.register.UserRegisterResDto;
import com.example.listo.service.user.GuestService;
import com.example.listo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guest")
@RequiredArgsConstructor
public class GuestController {

    /**
     * guest + mycoupon
     * */

    private final UserService userService;
    private final GuestService guestService;

    @PostMapping("/register")
    public ResponseEntity<GuestOnlyResDto> register(@RequestBody GuestReqDto request){
        GuestOnlyResDto savedDto = userService.createGuest(request);
        return ResponseEntity.ok(savedDto);

    }
    @GetMapping("/{guestId}")
    public ResponseEntity<GuestResDto> getGuest(@PathVariable("guestId") Long guestId){
        GuestResDto guest = guestService.findGuest(guestId);
        return ResponseEntity.ok(guest);

    }
}
