package com.shaul.product.request

import com.shaul.product.domain.Product

data class CreateProductRequest(
    val name: String,
    val price: String = "",
    val description: String = "",
    val imageUrl: String = "",
) {
    fun toDomain(): Product {
        return Product(
            name = name,
            price = price,
            description = description,
            imageUrl = imageUrl,
        )
    }
}
