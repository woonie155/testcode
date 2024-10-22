package com.example.demo.spring.api.controller.product;

import com.example.demo.spring.api.ApiResponse;
import com.example.demo.spring.api.controller.product.dto.request.ProductCreateRequest;
import com.example.demo.spring.api.service.product.ProductService;
import com.example.demo.spring.api.service.product.response.ProductResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api/v1/products/selling")
    public List<ProductResponse> getSellingProducts(){
        return productService.getSellingProducts();
    }

    @PostMapping("api/v1/products/new")
    public ApiResponse<ProductResponse> createProduct(@Valid @RequestBody ProductCreateRequest request){
        return ApiResponse.of(HttpStatus.OK, productService.createProduct(request));
    }
}
