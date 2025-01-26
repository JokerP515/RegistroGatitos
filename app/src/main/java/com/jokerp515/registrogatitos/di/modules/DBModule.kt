package com.jokerp515.registrogatitos.di.modules

import android.app.Application
import androidx.room.Room
import com.jokerp515.registrogatitos.local.db.GatoDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {
    @Provides
    @Singleton
    fun provideCatDb(app: Application): GatoDb {
        return Room.databaseBuilder(
            app,
            GatoDb::class.java,
            "gatodb.db"
        ).build()
    }
}