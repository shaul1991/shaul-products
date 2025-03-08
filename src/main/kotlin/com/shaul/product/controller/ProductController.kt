package com.shaul.product.controller

import com.shaul.product.request.CreateProductRequest
import com.shaul.product.request.UpdateProductRequest
import com.shaul.product.response.BaseResponse
import com.shaul.product.response.ProductListResponse
import com.shaul.product.response.ProductResponse
import com.shaul.product.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/product")
class ProductController(
    private val productService: ProductService,
) {
    @GetMapping
    fun getProducts(
        @RequestParam(required = false) lastId: String?,
        @RequestParam(defaultValue = "10") pageSize: Int,
    ): BaseResponse<ProductListResponse> {
        return BaseResponse.success(
            ProductListResponse.fromProducts(
                products = productService.getProducts(lastId = lastId, pageSize = pageSize)
                    .map { ProductResponse.fromProduct(it) },
                pageSize = pageSize,
            )
        )
    }

    @PostMapping
    fun create(
        @RequestBody request: CreateProductRequest,
    ) {
        productService.save(request = request)
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