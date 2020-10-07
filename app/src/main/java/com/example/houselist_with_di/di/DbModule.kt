package com.example.houselist_with_di.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.houselist_with_di.db.HouseDao
import com.example.houselist_with_di.db.HouseDao_Impl
import com.example.houselist_with_di.db.HouseDbEntity
import com.example.houselist_with_di.db.HousesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun provideHouseDb(@ApplicationContext context: Context): HousesDatabase {
        return Room.databaseBuilder(
            context,
            HousesDatabase::class.java,
            HousesDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideHouseDAO(housesDatabase: HousesDatabase): HouseDao {
        return housesDatabase.houseDao()
    }

}