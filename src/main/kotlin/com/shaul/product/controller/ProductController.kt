package com.shaul.product.controller

import com.shaul.product.request.CreateProductRequest
import com.shaul.product.request.UpdateProductRequest
import com.shaul.product.response.BaseResponse
import com.shaul.product.response.ProductListResponse
import com.shaul.product.response.ProductResponse
import com.shaul.product.service.ProductService
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val productService: ProductService,
) {
    @GetMapping
    fun getProducts(
        @RequestParam(required = false) next: String?,
        @RequestParam(defaultValue = "10") pageSize: Int,
    ): BaseResponse<ProductListResponse> {
        return BaseResponse.success(
            ProductListResponse.fromProducts(
                products = productService.getProducts(next = next, pageSize = pageSize)
                    .map { ProductResponse.fromProduct(it) },
                pageSize = pageSize,
            )
        )
    }

    @PostMapping
    @CircuitBreaker(name = "productService", fallbackMethod = "productServiceFallback")
    fun create(
        @RequestPart("request") request: CreateProductRequest,
        @RequestPart(required = false) images: List<MultipartFile>?,
    ) {
        productService.save(request = request, images = images)
    }

    fun productServiceFallback(
        request: CreateProductRequest,
        images: List<MultipartFile>?,
        throwable: Throwable,
    ) {
        println("productServiceFallback: $throwable")
    }

    @GetMapping("/{id}")
    fun show(
        @PathVariable id: String,
    ): BaseResponse<ProductResponse> {
        return BaseResponse.success(ProductResponse.fromProduct(productService.show(id = id)))
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: String,
        @RequestBody request: UpdateProductRequest,
    ) {
        productService.update(id = id, request = request)
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: String,
    ) {
        productService.delete(id = id)
    }
}