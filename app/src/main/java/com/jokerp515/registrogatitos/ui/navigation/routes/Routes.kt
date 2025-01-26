package com.jokerp515.registrogatitos.ui.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes (val id: String? = null) {
    @Serializable
    data object Registro : Routes("registro")

    @Serializable
    data object Lista : Routes("lista")

    @Serializable
    data object VistaPrevia : Routes("vista previa")
}
