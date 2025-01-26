package com.jokerp515.registrogatitos.data

import com.jokerp515.registrogatitos.local.entities.GatoEntity

data class Gato(
    val id: Long? = null,
    val nombre: String = "",
    val genero: String = "",
    var edad: Int = 0,
    var peso: Double = 0.0,
    val color: String = ""
){
    fun toEntity() = GatoEntity(
        id = id,
        nombre = nombre,
        genero = genero,
        edad = edad,
        peso = peso,
        color = color
    )
}
