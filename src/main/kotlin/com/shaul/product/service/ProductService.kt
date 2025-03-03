package com.shaul.product.service

import com.shaul.product.domain.Product
import com.shaul.product.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {
    fun saveProduct(product: Product) = productRepository.save(product.toEntity())
}