package com.example.listo.service.user;

import com.example.listo.dto.user.guest.GuestOnlyResDto;
import com.example.listo.dto.user.guest.GuestResDto;
import com.example.listo.dto.user.guest.GuestUpdateReqDto;

public interface GuestService {

    GuestResDto findOneGuest(Long guestId);

    GuestOnlyResDto updateGuest(Long guestId, GuestUpdateReqDto request);

    void deleteGuest(Long guestId);
}
