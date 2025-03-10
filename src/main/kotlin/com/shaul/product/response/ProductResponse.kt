package com.shaul.product.response

import com.shaul.product.domain.Product
import java.time.LocalDateTime

data class ProductResponse(
    val id: String,
    val name: String,
    val price: String,
    val description: String,
    val images: List<String>?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime?,
) {
    companion object {
        fun fromProduct(product: Product): ProductResponse {
            return ProductResponse(
                id = product.id!!,
                name = product.name,
                price = product.price,
                description = product.description,
                images = product.images,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt,
                deletedAt = product.deletedAt,
            )
        }
    }
}
