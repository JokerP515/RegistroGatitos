package com.jokerp515.registrogatitos.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.jokerp515.registrogatitos.local.entities.GatoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GatoDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gato: GatoEntity)

    @Query("SELECT * FROM GatoEntity")
    fun getAll(): Flow<List<GatoEntity>>

    @Transaction
    @Query("DELETE FROM GatoEntity WHERE id = :id")
    suspend fun deleteById(id: Long)

}