package com.shaul.product.request

data class UpdateProductRequest(
    val name: String,
    val price: String = "",
    val description: String = "",
    val images: List<String>? = null,
)
