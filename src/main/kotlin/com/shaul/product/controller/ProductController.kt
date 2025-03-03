package com.shaul.product.controller

import com.shaul.product.domain.Product
import com.shaul.product.request.CreateProductRequest
import com.shaul.product.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/product")
class ProductController(
    private val productService: ProductService,
) {
    @GetMapping
    fun getAllProducts(
        @RequestParam(required = false) lastId: String?,
        @RequestParam(defaultValue = "10") limit: Int,
    ): List<Product> {
        return productService.getProductsAfterId(lastId = lastId, limit = limit)
    }

    @PostMapping
    fun createProduct(
        @RequestBody request: CreateProductRequest,
    ) {
        productService.saveProduct(product = request.toDomain())
    }

    @GetMapping("/{id}")
    fun showProduct(
        @PathVariable id: String,
    ): Product {
        return productService.show(id = id)
    }
}