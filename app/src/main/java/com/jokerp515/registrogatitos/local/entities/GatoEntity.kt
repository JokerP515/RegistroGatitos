package com.jokerp515.registrogatitos.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GatoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val nombre: String,
    val genero: String,
    val edad: String,
    val peso: String,
    val color: String
)
