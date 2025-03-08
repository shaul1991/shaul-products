package com.shaul.product.response

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class BaseResponse<T>(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val message: String = "",
    val data: T? = null,
) {
    companion object {
        // 성공 응답 생성
        fun <T> success(data: T): BaseResponse<T> = BaseResponse(
            status = HttpStatus.OK.value(),
            data = data
        )

        // 성공 응답 생성 (메시지 포함)
        fun <T> success(data: T, message: String): BaseResponse<T> = BaseResponse(
            status = HttpStatus.OK.value(),
            message = message,
            data = data
        )

        // 성공 응답 생성 (데이터 없음)
        fun success(): BaseResponse<Unit> = BaseResponse(
            status = HttpStatus.OK.value(),
        )

        // 에러 응답 생성
        fun error(
            status: HttpStatus,
            message: String,
        ): BaseResponse<Unit> = BaseResponse(
            status = status.value(),
            message = message,
        )
    }
}
