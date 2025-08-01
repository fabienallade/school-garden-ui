package com.fabien.africschool.data.repository

import com.fabien.africschool.data.model.User
import com.fabien.africschool.data.network.ApiService
import com.fabien.africschool.domain.state.ResponseState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Provided
import org.koin.core.annotation.Single

@Single
class AuthRepository(
    val apiService: ApiService,
) {
    fun getUser(id: String): Flow<ResponseState<User>> =
        flow {
            Napier.d { "Fetching user $id" }
            emit(ResponseState.Loading)
            val user = apiService.getUser(id)
            emit(ResponseState.Success(user))
        }.catch {
            emit(ResponseState.Error(it.message ?: "Unknown error"))
        }
}
