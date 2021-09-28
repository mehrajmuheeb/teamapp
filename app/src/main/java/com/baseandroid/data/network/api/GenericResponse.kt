package com.baseandroid.data.network.api

data class GenericResponse<T>(
    var code: Int,
    var message: String,
    var result: T
)