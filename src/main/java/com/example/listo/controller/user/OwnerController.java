package com.example.listo.controller.user;


import com.example.listo.dto.user.guest.GuestResDto;
import com.example.listo.dto.user.owner.OwnerOnlyResDto;
import com.example.listo.dto.user.owner.OwnerResDto;
import com.example.listo.dto.user.register.GuestReqDto;
import com.example.listo.dto.user.register.OwnerReqDto;
import com.example.listo.service.user.GuestService;
import com.example.listo.service.user.OwnerService;
import com.example.listo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<OwnerOnlyResDto> register(@RequestBody OwnerReqDto request) {
        OwnerOnlyResDto savedDto = userService.createOwner(request);
        return ResponseEntity.ok(savedDto);
    }
}
