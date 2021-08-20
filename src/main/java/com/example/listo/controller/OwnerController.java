package com.example.listo.controller;


import com.example.listo.dto.user.guest.GuestOnlyResDto;
import com.example.listo.dto.user.guest.GuestResDto;
import com.example.listo.dto.user.guest.GuestUpdateReqDto;
import com.example.listo.dto.user.owner.OwnerOnlyResDto;
import com.example.listo.dto.user.owner.OwnerResDto;
import com.example.listo.dto.user.owner.OwnerUpdateReqDto;
import com.example.listo.dto.user.register.GuestReqDto;
import com.example.listo.dto.user.register.OwnerReqDto;
import com.example.listo.service.user.GuestService;
import com.example.listo.service.user.OwnerService;
import com.example.listo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerController {
    /**
     * owner
     * */
    private final UserService userService;
    private final OwnerService ownerService;

    @PostMapping("/register")
    public ResponseEntity<OwnerOnlyResDto> register(@Valid @RequestBody OwnerReqDto request) {
        OwnerOnlyResDto savedDto = userService.createOwner(request);
        return ResponseEntity.ok(savedDto);
    }
    @GetMapping("/{ownerId}")
    public ResponseEntity<OwnerResDto> getOneOwner(@PathVariable("ownerId") Long ownerId){
        OwnerResDto owner = ownerService.findOneOwner(ownerId);
        return ResponseEntity.ok(owner);
    }
    @PatchMapping("/{ownerId}")
    public ResponseEntity<OwnerOnlyResDto> updateOwnerInfo(@PathVariable("ownerId") Long ownerId, @RequestBody OwnerUpdateReqDto request){
        OwnerOnlyResDto owner = ownerService.updateOwner(ownerId, request);
        return ResponseEntity.ok(owner);
    }
    @DeleteMapping("/{ownerId}")
    public ResponseEntity<String> deleteOwner(@PathVariable("ownerId") Long ownerId){
        ownerService.deleteOwner(ownerId);
        return ResponseEntity.ok("Successfully Deleted");
    }
}
