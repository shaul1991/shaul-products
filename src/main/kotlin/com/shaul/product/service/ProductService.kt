package com.shaul.product.service

import com.shaul.product.client.FileServerClient
import com.shaul.product.domain.Product
import com.shaul.product.entity.ProductEntity
import com.shaul.product.repository.ProductRepository
import com.shaul.product.request.CreateProductRequest
import com.shaul.product.request.UpdateProductRequest
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val mongoTemplate: MongoTemplate,
    private val fileServerClient: FileServerClient,
) {
    fun save(request: CreateProductRequest, images: List<MultipartFile>?) {
        val product = Product.fromCreateRequest(request = request)
        if (images != null) {
            product.images = fileServerClient.publicFileUpload(files = images).split(",")
        }

        productRepository.save(product.toEntity())
    }

    fun show(id: String): Product {
        val product = productRepository.findById(id).get().toDomain()

        if (product.isDeleted()) {
            throw Exception("Product not found")
        }

        return product
    }

    fun getProducts(next: String?, pageSize: Int): List<Product> {
        val query = Query()
        if (next != null) {
            query.addCriteria(Criteria.where("id").lt(next))
        }

        query.with(Sort.by(Sort.Direction.DESC, "id"))
        query.addCriteria(Criteria.where("deletedAt").isNull())
        query.limit(pageSize)

        return mongoTemplate.find(query, ProductEntity::class.java).map { it.toDomain() }
    }

    fun update(id: String, request: UpdateProductRequest) {
        val product = productRepository.findById(id).get().toDomain()
        product.update(request = request)
        productRepository.save(product.toEntity())
    }

    fun delete(id: String) {
        val product = productRepository.findById(id).get().toDomain()
        product.delete()
        productRepository.save(product.toEntity())
    }
}