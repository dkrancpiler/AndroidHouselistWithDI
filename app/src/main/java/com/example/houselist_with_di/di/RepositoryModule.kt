package com.example.houselist_with_di.di

import com.example.houselist_with_di.db.HouseDao
import com.example.houselist_with_di.db.dbMapper
import com.example.houselist_with_di.network.HousesNetworkCall
import com.example.houselist_with_di.network.NetworkMapper
import com.example.houselist_with_di.repository.MainRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepo (
        houseDao: HouseDao,
        retrofit: HousesNetworkCall,
        dbMapper: dbMapper,
        networkMapper: NetworkMapper
    ): MainRepo {
        return MainRepo(houseDao,retrofit,networkMapper,dbMapper)
    }

}