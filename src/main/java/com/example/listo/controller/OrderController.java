package com.example.listo.controller;

import com.example.listo.dto.OrderReqDto;
import com.example.listo.dto.OrderResDto;
import com.example.listo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<OrderResDto> makeOrder(@RequestBody OrderReqDto reqDto){
        OrderResDto resDto=orderService.makeOrder(reqDto);
        return ResponseEntity.ok(resDto);
    }
}
