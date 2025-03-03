package com.shaul.product.repository

import com.shaul.product.entity.ProductEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : MongoRepository<ProductEntity, String>