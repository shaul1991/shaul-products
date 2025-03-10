package com.shaul.product.request

data class CreateProductRequest(
    val name: String,
    val price: String = "",
    val description: String = "",
)
