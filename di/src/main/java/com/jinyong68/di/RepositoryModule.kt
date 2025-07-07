package com.jinyong68.di

import com.jinyong68.network.account.AccountRepository
import com.jinyong68.network.account.AccountRepositoryImpl
import com.jinyong68.network.user.UserRepository
import com.jinyong68.network.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository =
        accountRepositoryImpl

    @Provides
    @Singleton
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository =
        userRepositoryImpl
}