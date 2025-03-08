package com.shaul.product.response

data class ProductListResponse(
    val products: List<ProductResponse>,
    val cursorPaginate: CursorPaginate,
) {
    companion object {
        fun fromProducts(
            products: List<ProductResponse>,
            pageSize: Int,
        ): ProductListResponse {
            return ProductListResponse(
                products = products,
                cursorPaginate = CursorPaginate(
                    next = if (products.size >= pageSize) products.last().id else null,
                    pageSize = pageSize,
                )
            )
        }
    }
}
