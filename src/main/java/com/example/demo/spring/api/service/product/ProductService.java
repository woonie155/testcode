package com.example.demo.spring.api.service.product;

import com.example.demo.spring.api.controller.product.dto.request.ProductCreateRequest;
import com.example.demo.spring.api.service.product.response.ProductResponse;
import com.example.demo.spring.domain.product.Product;
import com.example.demo.spring.domain.product.ProductRepository;
import com.example.demo.spring.domain.product.ProductSellingStatus;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
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


    @Transactional
    public ProductResponse createProduct(ProductCreateRequest request) {
        String nextProductNumber = createNextProductNumber();

        Product product = request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }

    private String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProductNumber();
        if (latestProductNumber == null) {
            return "001";
        }

        int nextProductNumberInt = Integer.parseInt(latestProductNumber) + 1;
        return String.format("%03d", nextProductNumberInt);
    }

}
