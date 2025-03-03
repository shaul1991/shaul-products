package com.shaul.product.service

import com.shaul.product.domain.Product
import com.shaul.product.entity.ProductEntity
import com.shaul.product.repository.ProductRepository
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val mongoTemplate: MongoTemplate,
) {
    fun saveProduct(product: Product) = productRepository.save(product.toEntity())

    fun show(id: String): Product {
        return productRepository.findById(id).get().toDomain()
    }

    fun getProductsAfterId(lastId: String?, limit: Int): List<Product> {
        val query = Query()
        if (lastId != null) {
            query.addCriteria(Criteria.where("id").lt(lastId))
        }

        query.with(Sort.by(Sort.Direction.DESC, "createdAt"))
        query.addCriteria(Criteria.where("deletedAt").isNull())
        query.limit(limit)

        return mongoTemplate.find(query, ProductEntity::class.java).map { it.toDomain() }
    }
}