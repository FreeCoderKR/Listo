package com.example.listo.service.user;


import com.example.listo.dto.user.owner.OwnerOnlyResDto;
import com.example.listo.dto.user.owner.OwnerResDto;
import com.example.listo.dto.user.owner.OwnerUpdateReqDto;

public interface OwnerService {
    void deleteOwner(Long ownerId);

    OwnerOnlyResDto updateOwner(Long ownerId, OwnerUpdateReqDto request);

    OwnerResDto findOneOwner(Long ownerId);
}
