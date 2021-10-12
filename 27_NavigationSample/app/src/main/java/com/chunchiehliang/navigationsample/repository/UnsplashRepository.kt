package com.chunchiehliang.navigationsample.repository

import com.chunchiehliang.navigationsample.modal.Photo
import com.chunchiehliang.navigationsample.network.ErrorEntity
import com.chunchiehliang.navigationsample.network.Result
import com.chunchiehliang.navigationsample.network.apiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UnsplashRepository() {
    suspend fun getARandomPhoto(): Result<Photo> {
        return withContext(Dispatchers.Default) {
            try {
                Result.Success(data = apiClient.unsplashService.getARandom())
            } catch (e: Exception) {
                Result.Error(ErrorEntity.ApiError.NetworkError)
            }
        }
    }
}