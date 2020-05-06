package com.anangkur.beritaku.data

import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): com.anangkur.beritaku.data.model.BaseResult<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return com.anangkur.beritaku.data.model.BaseResult.success(body)
            }
            return com.anangkur.beritaku.data.model.BaseResult.error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return com.anangkur.beritaku.data.model.BaseResult.error(e.message ?: e.toString())
        }
    }
}