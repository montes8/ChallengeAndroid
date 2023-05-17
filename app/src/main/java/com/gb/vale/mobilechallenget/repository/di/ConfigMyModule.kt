package com.gb.vale.mobilechallenget.repository.di

import com.gb.vale.mobilechallenget.repository.db.api.DataDB
import com.gb.vale.mobilechallenget.repository.network.api.DataNetwork
import com.gb.vale.mobilechallenget.usecases.network.IDataDB
import com.gb.vale.mobilechallenget.usecases.network.IDataNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConfigMyModule{

    @Singleton
    @Binds
    abstract fun provideIUserDataBase(
        dataDB: DataDB
    ): IDataDB

    @Singleton
    @Binds
    abstract fun provideIDataNetwork(
        dataNetwork: DataNetwork
    ): IDataNetwork
}
