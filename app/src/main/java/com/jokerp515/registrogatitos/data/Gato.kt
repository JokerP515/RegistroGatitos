package com.jokerp515.registrogatitos.data

import com.jokerp515.registrogatitos.local.entities.GatoEntity

data class Gato(
    val id: Long? = null,
    val nombre: String = "",
    val genero: String = "",
    val edad: Int,
    val peso: Double,
    val color: String = ""
){
    fun toEntity() = GatoEntity(
        id = id,
        nombre = nombre,
        genero = genero,
        edad = edad.toString(),
        peso = peso.toString(),
        color = color
    )
}
