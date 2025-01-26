package com.jokerp515.registrogatitos.di.modules

import com.jokerp515.registrogatitos.local.dao.GatoDao
import com.jokerp515.registrogatitos.local.db.GatoDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Provides
    @Singleton
    fun provideCatDao(db: GatoDb): GatoDao {
        return db.gatoDao
    }
}
