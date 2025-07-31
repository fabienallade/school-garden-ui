package com.fabien.africschool.di.modules

import com.fabien.africschool.data.network.ApiService
import com.fabien.africschool.data.repository.AuthRepository
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module()
@ComponentScan("com.fabien.africschool.data.repository")
class RepositoryModule
