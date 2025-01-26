package com.jokerp515.registrogatitos.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jokerp515.registrogatitos.local.dao.GatoDao
import com.jokerp515.registrogatitos.local.entities.GatoEntity

@Database(
    entities = [GatoEntity::class],
    version = 1
)
abstract class GatoDb : RoomDatabase() {
    abstract val gatoDao: GatoDao
}