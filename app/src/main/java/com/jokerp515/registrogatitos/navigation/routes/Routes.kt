package com.jokerp515.registrogatitos.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes (val id: String? = null) {
    @Serializable
    object Registro : Routes("registro")

    @Serializable
    object Lista : Routes("lista")

    @Serializable
    object VistaPrevia : Routes("vista previa")

    @Serializable
    data class GatoDetalle(val nombre: String = "", val genero : String = "", val edad : String = "", val peso : String = "", val color : String = "") : Routes() {
        fun toCatState() = CatState(nombre = nombre, genero = genero, edad = edad, peso = peso, color = color)
    }
}
