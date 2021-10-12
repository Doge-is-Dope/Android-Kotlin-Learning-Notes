package com.chunchiehliang.navigationsample.network

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Error<T>(val error: ErrorEntity, val message: String? = null) : Result<T>()
}

sealed class ErrorEntity {

    sealed class ApiError : ErrorEntity() {
        object Unauthenticated : ApiError()

        object NetworkError : ApiError()
    }

    sealed class FileError : ErrorEntity() {

//        object NotFound: FileError()
//
//        object ReadError:  FileError()
    }
}