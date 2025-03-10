package com.shaul.product.entity

import com.shaul.product.domain.Product
import jakarta.persistence.Id
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "products")
data class ProductEntity(
    @Id
    val id: String? = null,
    val name: String = "",
    val price: String = "",
    val description: String = "",
    val images: List<String>? = null,
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    var updatedAt: LocalDateTime = LocalDateTime.now(),
    var deletedAt: LocalDateTime? = null,
) {
    fun toDomain(): Product {
        return Product(
            id = id,
            name = name,
            price = price,
            description = description,
            images = images,
            createdAt = createdAt,
            updatedAt = updatedAt,
            deletedAt = deletedAt,
        )
    }
}