package com.example.listo.controller;

import com.example.listo.dto.OrderReqDto;
import com.example.listo.dto.OrderResDto;
import com.example.listo.dto.ReviewReqDto;
import com.example.listo.dto.ReviewResDto;
import com.example.listo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{orderId}/review")
    public ResponseEntity<ReviewResDto> writeReview(@RequestBody ReviewReqDto reqDto, @PathVariable("orderId") Long orderId){
        ReviewResDto resDto=orderService.writeReview(reqDto, orderId);
        return ResponseEntity.ok(resDto);
    }
}
