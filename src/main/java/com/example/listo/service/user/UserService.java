package com.example.listo.service.user;

import com.example.listo.dto.user.guest.GuestOnlyResDto;
import com.example.listo.dto.user.guest.GuestResDto;
import com.example.listo.dto.user.owner.OwnerOnlyResDto;
import com.example.listo.dto.user.owner.OwnerResDto;
import com.example.listo.dto.user.register.GuestReqDto;
import com.example.listo.dto.user.register.OwnerReqDto;
import com.example.listo.dto.user.register.UserRegisterResDto;

public interface UserService {

    OwnerOnlyResDto createOwner(OwnerReqDto request);

    GuestOnlyResDto createGuest(GuestReqDto request);
}
