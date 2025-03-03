package com.shaul.product.response

data class ProductListResponse(
    val products: List<ProductResponse>,
    val searchId: String?,
    val lastId: String?,
    val limit: Int,
) {
    companion object {
        fun fromProducts(products: List<ProductResponse>, searchId: String?, limit: Int): ProductListResponse {
            return ProductListResponse(
                products = products,
                searchId = searchId,
                lastId = products.lastOrNull()?.id,
                limit = limit,
            )
        }
    }
}
