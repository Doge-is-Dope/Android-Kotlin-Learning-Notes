package com.chunchiehliang.koinsample.repository

import com.chunchiehliang.koinsample.modal.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class UserRepository {
    suspend fun getUserList(): List<User> {
        return withContext(Dispatchers.Default) {
            delay(3000L)
            listOf(
                User(0, "mike123", "Mike"),
                User(1, "doge_god", "Raphael"),
                User(2, "kitten_sweet", "Chrissy"),
                User(3, "koado1_1231", "John"),
                User(4, "ad2n13j12", "Tom"),
            )
        }
    }
}