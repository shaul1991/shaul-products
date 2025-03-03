package com.shaul.product.domain

import com.shaul.product.entity.ProductEntity
import com.shaul.product.request.CreateProductRequest
import com.shaul.product.request.UpdateProductRequest
import java.time.LocalDateTime

data class Product(
    val id: String? = null,
    var name: String = "",
    var price: String = "",
    var description: String = "",
    var imageUrl: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),
    var deletedAt: LocalDateTime? = null,
) {
    companion object {
        fun fromCreateRequest(request: CreateProductRequest): Product {
            return Product(
                name = request.name,
                price = request.price,
                description = request.description,
                imageUrl = request.imageUrl,
            )
        }
    }

    fun toEntity(): ProductEntity {
        return ProductEntity(
            id = id,
            name = name,
            price = price,
            description = description,
            imageUrl = imageUrl,
            createdAt = createdAt,
            updatedAt = updatedAt,
            deletedAt = deletedAt,
        )
    }

    fun update(request: UpdateProductRequest) {
        name = request.name
        price = request.price
        description = request.description
        imageUrl = request.imageUrl
        updatedAt = LocalDateTime.now()
    }
}
