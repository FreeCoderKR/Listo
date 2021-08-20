package com.example.listo.controller;

import com.example.listo.dto.order.*;
import com.example.listo.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    /**
     * order + review +payment
     * */
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

    @PostMapping("/{orderId}/payment")
    public ResponseEntity<PaymentResDto> payOrder(@RequestBody PaymentReqDto reqDto, @PathVariable("orderId") Long orderId){
        PaymentResDto resDto=orderService.payOrder(reqDto, orderId);
        return ResponseEntity.ok(resDto);
    }
}
