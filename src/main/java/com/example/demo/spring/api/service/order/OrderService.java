package com.example.demo.spring.api.service.order;

import com.example.demo.spring.api.controller.order.request.OrderCreateRequest;
import com.example.demo.spring.api.service.order.response.OrderResponse;
import com.example.demo.spring.domain.order.Order;
import com.example.demo.spring.domain.order.OrderRepository;
import com.example.demo.spring.domain.product.Product;
import com.example.demo.spring.domain.product.ProductRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredDateTime) {
        List<String> productNumbers = request.getProductNumbers();
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);

        Order order = Order.create(products, registeredDateTime);
        Order savedOrder = orderRepository.save(order);
        return OrderResponse.of(savedOrder);
    }

}