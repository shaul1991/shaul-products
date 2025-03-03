package com.shaul.product.domain

import com.shaul.product.entity.ProductEntity
import java.time.LocalDateTime

data class Product(
    val id: String? = null,
    val name: String = "",
    val price: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),
    var deletedAt: LocalDateTime? = null,
) {
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
}
