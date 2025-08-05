package com.fabien.africschool.data.network

import com.fabien.africschool.data.model.User
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Single

interface ApiService {
    @GET("users/{id}/")
    suspend fun signUp(): String

    @GET("users/{id}")
    suspend fun getUser(
        @Path("id") id: String,
    ): User

    @GET("users/")
    suspend fun getUsers(): List<User>
}
