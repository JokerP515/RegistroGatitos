package com.jokerp515.registrogatitos.ui.navigation.routes

import com.jokerp515.registrogatitos.data.Gato
import kotlinx.serialization.Serializable

@Serializable
sealed class Routes (val id: String? = null) {
    @Serializable
    object Registro : Routes("registro")

    @Serializable
    object Lista : Routes("lista")

    @Serializable
    object VistaPrevia : Routes("vista previa")

//    @Serializable
//    data class GatoDetalle(val nombre: String = "", val genero : String = "", val edad : String = "", val peso : String = "", val color : String = "") : Routes() {
//        fun toCatState() = Gato(nombre = nombre, genero = genero, edad = edad.toInt(), peso = peso.toDouble(), color = color)
//    }
}
