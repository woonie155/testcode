package com.example.demo.spring.api.controller.order;

import com.example.demo.spring.api.ApiResponse;
import com.example.demo.spring.api.controller.order.request.OrderCreateRequest;
import com.example.demo.spring.api.service.order.OrderService;
import com.example.demo.spring.api.service.order.response.OrderResponse;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public ApiResponse<OrderResponse> createOrder(@Valid @RequestBody OrderCreateRequest request) {
        LocalDateTime registeredDateTime = LocalDateTime.now();
        return ApiResponse.ok(orderService.createOrder(request.toServiceRequest(), registeredDateTime));
    }

}