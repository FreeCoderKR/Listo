package com.example.listo.controller.user;

import com.example.listo.dto.user.guest.GuestOnlyResDto;
import com.example.listo.dto.user.guest.GuestResDto;
import com.example.listo.dto.user.guest.GuestUpdateReqDto;
import com.example.listo.dto.user.register.GuestReqDto;
import com.example.listo.dto.user.register.UserRegisterResDto;
import com.example.listo.error.NoDataWithIdException;
import com.example.listo.service.user.GuestService;
import com.example.listo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<GuestOnlyResDto> guestRegister(@Valid @RequestBody GuestReqDto request){
        GuestOnlyResDto savedDto = userService.createGuest(request);
        return ResponseEntity.ok(savedDto);   }

    @GetMapping("/{guestId}")
    public ResponseEntity<GuestResDto> getOneGuest(@PathVariable("guestId") Long guestId){
        GuestResDto guest = guestService.findOneGuest(guestId);
        return ResponseEntity.ok(guest);
    }
    @PatchMapping("/{guestId}")
    public ResponseEntity<GuestOnlyResDto> updateGuestInfo(@PathVariable("guestId") Long guestId, @RequestBody GuestUpdateReqDto request){
        GuestOnlyResDto guest = guestService.updateGuest(guestId, request);
        return ResponseEntity.ok(guest);
    }
    @DeleteMapping("/{guestId}")
    public ResponseEntity<String> deleteGuest(@PathVariable("guestId") Long guestId){
        guestService.deleteGuest(guestId);
        return ResponseEntity.ok("Successfully Deleted");
    }



}
