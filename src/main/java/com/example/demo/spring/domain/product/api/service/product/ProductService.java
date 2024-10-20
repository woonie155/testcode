package com.example.demo.spring.domain.product.api.service.product;

import com.example.demo.spring.domain.product.Product;
import com.example.demo.spring.domain.product.ProductRepository;
import com.example.demo.spring.domain.product.ProductSellingStatus;
import com.example.demo.spring.domain.product.api.service.product.response.ProductResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getSellingProducts(){
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());
        return products.stream()
                .map(product -> ProductResponse.of(product))
                .collect(Collectors.toList());
    }

}
