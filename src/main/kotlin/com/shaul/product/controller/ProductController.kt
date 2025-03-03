package com.shaul.product.controller

import com.shaul.product.request.CreateProductRequest
import com.shaul.product.service.ProductService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/product")
class ProductController(
    private val productService: ProductService,
) {
    @PostMapping
    fun createProduct(
        @RequestBody request: CreateProductRequest,
    ) {
        productService.saveProduct(product = request.toDomain())
    }
}