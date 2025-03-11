package com.shaul.product.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile

@FeignClient(name = "image-service")
interface FileServerClient {
    @RequestMapping(method = [RequestMethod.POST], value = ["/files"], consumes = ["multipart/form-data"])
    fun publicFileUpload(@RequestPart("files") files: List<MultipartFile>): String
}